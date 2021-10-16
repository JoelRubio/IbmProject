package org.academy.jra.service;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

import org.academy.jra.client.BanamexFeignClient;
import org.academy.jra.domain.Location;
import org.academy.jra.model.BankEntityDTO;
import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.ValidationConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase que representa la lógica para la búsqueda
 * de cajeros automáticos o sucursales de acuerdo
 * una locación dada.
 * 
 * @author Joel Rubio
 *
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

	private String strJsonFile;
	
	@Value("${feignClient.api.methodGET.hostname}${feignClient.api.methodGET.uri}")
	private String URLDestination;

	
	private ValidationService validationService;
	private BanamexFeignClient feignClient;
	
	/**
	 * Inyección de dependencias por argumento.
	 * 
	 * @param validationService servicio para validar los campos del cliente y del banco
	 * @param feignClient       cliente para realizar la petición a la URL
	 */
	public LocationServiceImpl(ValidationService validationService, BanamexFeignClient feignClient) {
		
		this.validationService = validationService;
		this.feignClient       = feignClient;
	}
	
	/**
	 * Descarga el archivo JSON y lo filtra,
	 * esto después de inicializar el bean.
	 * 
	 */
	@PostConstruct
	public void initFile() {
		
		String jsonFile = downloadFile();
		
		filterFile(jsonFile);
	}
	
	/**
	 * Se realiza la petición a URLDestination
	 * por medio de FeignClient y los datos son 
	 * transformados a un String con codificación
	 * UTF-8.
	 * 
	 * 
	 * @return String  archivo JSON descargado de URLDestination
	 */
	private String downloadFile() {
		
		log.info("Downloading JSON file from: " + URLDestination);
		
		byte[] byteArrayJsonFile = feignClient.getJsonFile();
		
		String jsonFile = new String(byteArrayJsonFile, StandardCharsets.UTF_8);
		
		return jsonFile;
	}
	
	
	/**
	 * Filtra el archivo quitando caracteres,
	 * con la finalidad de ser usado en las peticiones
	 * posteriores del cliente.
	 * 
	 * 
	 * @param jsonFile  archivo JSON proveniente de URLDestination
	 */
	private void filterFile(String jsonFile) {
		
		final String BEGIN_STR   = "jsonCallback({\"Servicios\":";
		final String END_STR     = "});";
		final String EMPTY_VALUE = "";
		
		log.info("Filtering JSON file...");
		
		this.strJsonFile = jsonFile.replace(BEGIN_STR, EMPTY_VALUE).replace(END_STR, EMPTY_VALUE);
	}
	
	
	/**
	 * Busca a través de un archivo JSON de la URL:
	 * la ubicación de uno o varios cajeros automáticos y/o sucursales
	 * cercarnos a la ubicación solicitada.
	 * 
	 * @param location  locación un cajero automático o sucursal
	 * 
	 * @return conjunto de ATMs o sucursales cercanos
	 */
	@Cacheable("bank-entities-location")
	@Override
	public Set<BankEntityDTO> getBankEntitiesLocations(Location location) {
		
		Set<BankEntityDTO> locations = new HashSet<>();
		
		readJson(location, locations);
		
		if (locations.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITIES_NOT_FOUND);
		}
		
		return locations;
	}
	
	
	/**
	 * Lee la variable que contiene la datos en formato JSON
	 * para después leer línea por línea.
	 * 
	 * 
	 * @param location  locación para encontrar un ATM o una sucursal
	 * @param locations locaciones encontradas de ATMs o una sucursal
	 */
	private void readJson(Location location, Set<BankEntityDTO> locations) {
		
		try {
	
			//permite llaves duplicadas en el archivo json
			net.sf.json.JSONObject jsonWithDuplicateKeys = net.sf.json.JSONObject.fromObject(this.strJsonFile);
			
			//obtiene el objeto JSON con ayuda de otra bilbioteca a partir del paso anterior con llaves duplicadas
			JSONObject jsonObject = new JSONObject(jsonWithDuplicateKeys);
			
			
			log.info("reading JSON file...");
			
			readLines(jsonObject, location, locations);
			
		} catch (Exception exception) {	
			
			log.error(exception.getMessage());
			
			exception.printStackTrace();
		}
	}
	
	/**
	 * Lee el conjunto de llaves y valores del objeto JSON
	 * para obtener los arreglos que contienen la información
	 * para saber la ubicación de una entidad bancaria.
	 * 
	 * 
	 * @param jsonObject objeto que contiene los datos en formato JSON
	 * @param location   locación para encontrar un ATM o una sucursal
	 * @param locations  locaciones encontradas de ATMs o una sucursal
	 */
	private void readLines(JSONObject jsonObject, Location location, Set<BankEntityDTO> locations) {
		
		Iterator<String> firstLevelKeys  = jsonObject.keys();
		Iterator<String> secondLevelKeys = null;
		Iterator<String> lastLevelKeys  = null;
		String firstKey = null;
		String secondKey = null;
		String lastKey = null;
		JSONArray jsonArray = null;
		
		
		while (firstLevelKeys.hasNext()) {
			
			firstKey = firstLevelKeys.next();
			
			secondLevelKeys = jsonObject.getJSONObject(firstKey).keys();
			
			while (secondLevelKeys.hasNext()) {
				
				secondKey = secondLevelKeys.next();
				
				lastLevelKeys = jsonObject.getJSONObject(firstKey).getJSONObject(secondKey).keys();
				
				//Itera cada llave que contiene un arreglo
				while (lastLevelKeys.hasNext()) {
					
					lastKey = lastLevelKeys.next();
					
					jsonArray = jsonObject.getJSONObject(firstKey).getJSONObject(secondKey).getJSONArray(lastKey);
					
					setValuesOfArray(jsonArray, location, locations);
				}
			}
		}
	}
	
	
	/**
	 * Verifica que la entidad bancaria sea un ATM o una sucursal
	 * para después agregarla al conjunto de locaciones.
	 * 
	 * 
	 * @param jsonArray arreglo que contiene los valores de la ubicación
	 * 				    de una entidad bancaria
	 * @param location  locación para encontrar un ATM o una sucursal
	 * @param locations locaciones encontradas de ATMs o una sucursal
	 */
	private void setValuesOfArray(JSONArray jsonArray, Location location, Set<BankEntityDTO> locations) {
		
		//si no es un cajero automático o una sucursal regresa el control
		if (!validationService.verifyTypeOfPlace(jsonArray.getString(ValidationConstants.POSITION_FOR_TYPE_OF_PLACE))) {
			
			return;	
		}
		
		getValidatedBankEntity(jsonArray, location)
			.ifPresent(bankEntity -> locations.add(bankEntity));
	}
	
	
	/**
	 * Valida que los campos del cliente concuerden con los
	 * del arreglo.
	 * 
	 * @param jsonArray arreglo que contiene los valores de la ubicación
	 * 				    de una entidad bancaria
	 * @param location  locación para encontrar un ATM o una sucursal
	 * 
	 * @return opcional sobre una entidad bancaria
	 */
	private Optional<BankEntityDTO> getValidatedBankEntity(JSONArray jsonArray, Location location) {
		
		final String LATITUDE  = jsonArray.getString(ValidationConstants.POSITION_FOR_LATITUDE);
		final String LONGITUDE = jsonArray.getString(ValidationConstants.POSITION_FOR_LONGITUDE);
		final String ADDRESS   = jsonArray.getString(ValidationConstants.POSITION_FOR_ADDRESS);
		final String STATE     = jsonArray.getString(ValidationConstants.POSITION_FOR_STATE);
		final String TYPE      = jsonArray.getString(ValidationConstants.POSITION_FOR_TYPE_OF_PLACE);
		
		BankEntityDTO bankEntity = new BankEntityDTO();
		
		//verify all
		if (!location.containsAll() && !validationService.areAllValid(location, jsonArray)) {
			
			return Optional.empty();
		}
		
		//postalCode and latitude
		if (!location.getPostalCode().isEmpty() &&
			!location.getGps().isLatitudeEmpty() && 
			!validationService.isPostalCodeAndLatitudeValid(location, ADDRESS, LATITUDE)) {
		
			return Optional.empty();
		}
		
		//postalCode and longitude
		if (!location.getPostalCode().isEmpty() &&
			!location.getGps().isLongitudeEmpty() &&
			!validationService.isPostalCodeAndLongitudeValid(location, ADDRESS, LONGITUDE)) {
		
			return Optional.empty();
		}
		
		//postalCode, latitude, and longitude
		if (!location.getPostalCode().isEmpty() &&
			!location.getGps().isLatitudeEmpty() &&
			!location.getGps().isLongitudeEmpty() &&
			!validationService.isPostalCodeAndLatitudeAndLongitudeValid(location, ADDRESS, LATITUDE, LONGITUDE)) {
		
			return Optional.empty();
		}
		
		//postalCode, latitude, and place
		if (!location.getPostalCode().isEmpty() &&
			!location.getGps().isLatitudeEmpty() &&
			!location.getPlace().isEmpty() &&
			!validationService.isPostalCodeAndLongitudeAndPlaceValid(location, LATITUDE, ADDRESS, STATE)) {
			
			return Optional.empty();
		}
		
		//postalCode, longitude, and place
		if (!location.getPostalCode().isEmpty() &&
			!location.getGps().isLongitudeEmpty() &&
			!location.getPlace().isEmpty() &&
			!validationService.isPostalCodeAndLongitudeAndPlaceValid(location, LONGITUDE, ADDRESS, STATE)) {
			
			return Optional.empty();
		}
		
		//latitude and longitude
		if (!location.getGps().isLatitudeEmpty() &&
			!location.getGps().isLongitudeEmpty() &&
			!validationService.isLatitudeAndLongitudeValid(location, LATITUDE, LONGITUDE)) {
		
			return Optional.empty();
		}
		
		//latitude and place
		if (!location.getGps().isLatitudeEmpty() &&
			!location.getPlace().getName().isEmpty() &&
			!validationService.isLatitudeAndPlaceValid(location, LATITUDE, ADDRESS, STATE)) {
		
			return Optional.empty();
		}
		
		//longitude and place
		if (!location.getGps().isLongitudeEmpty() &&
			!location.getPlace().getName().isEmpty() &&
			!validationService.isLongitudeAndPlaceValid(location, LONGITUDE, ADDRESS, STATE)) {
		
			return Optional.empty();
		}
		
		//latitude, longitude, and place
		if (!location.getGps().isLatitudeEmpty() &&
			!location.getGps().isLongitudeEmpty() &&
			!location.getPlace().getName().isEmpty() &&
			!validationService.isLatitudeAndLongitudeAndPlaceValid(location, ADDRESS, LONGITUDE, ADDRESS, STATE)) {
		
			return Optional.empty();
		}
		
		bankEntity.setLatitude(LATITUDE);
		bankEntity.setLongitude(LONGITUDE);
		bankEntity.setAddress(ADDRESS);
		bankEntity.setState(STATE);
		bankEntity.setType(TYPE);
		
		log.info("Found entity: " + bankEntity.toString());
		
		return Optional.of(bankEntity);
	}
}
