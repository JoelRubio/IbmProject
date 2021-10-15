package org.academy.jra.service;

import java.math.BigDecimal;
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
import org.academy.jra.model.BankEntityPlace;
import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.GPSUtils;
import org.academy.jra.utils.ValidationConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase que contiene la lógica para la búsqueda
 * de cajeros automáticos o sucursales de acuerdo
 * una locación dada.
 * 
 * @author joel
 *
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

	private byte[] byteArrayJsonFile;
	private String strJsonFile;
	
	@Value("${feignClient.api.methodGET.hostname}${feignClient.api.methodGET.uri}")
	private String URLDestination;

	private BanamexFeignClient feignClient;
	
	/**
	 * Inyección de dependencias por argumento.
	 * 
	 * @param feignClient cliente para realizar la petición a la URL
	 */
	public LocationServiceImpl(BanamexFeignClient feignClient) {
		
		this.feignClient = feignClient;
	}
	
	/**
	 * Descarga el archivo JSON y lo filtra
	 * después de inicializar el bean.
	 */
	@PostConstruct
	public void initFile() {
		
		downloadFile();
		
		filterFile();
	}
	
	
	/**
	 * Busca a través de un archivo JSON de la URL:
	 * la ubicación de uno o varios cajeros automáticos y/o sucursales
	 * cercarnos a la ubicación solicitada.
	 * 
	 * @param atm  locación cercana de un cajero automático o sucursal
	 * 
	 * @return conjunto de cajeros automáticos o sucursales cercanos
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
	 * Descarga un archivo JSON de una URL.
	 * 
	 */
	private void downloadFile() {
		
		log.info("Downloading JSON file from: " + URLDestination);
		
		byteArrayJsonFile = feignClient.getJsonFile();
		
		this.strJsonFile = new String(this.byteArrayJsonFile, StandardCharsets.UTF_8);	
	}
	
	/**
	 * Filtra el archivo quitando caracteres 
	 * para poder ser usado después.
	 */
	private void filterFile() {
		
		final String BEGIN_STR   = "jsonCallback({\"Servicios\":";
		final String END_STR     = "});";
		final String EMPTY_VALUE = "";
		
		log.info("Filtering JSON file...");
		
		this.strJsonFile = this.strJsonFile.replace(BEGIN_STR, EMPTY_VALUE).replace(END_STR, EMPTY_VALUE);
	}
	
	/**
	 * 
	 * @param location
	 * @param locations
	 */
	private void readJson(Location location, Set<BankEntityDTO> locations) {
		
		try {
	
			//permite las llaves duplicadas en el archivo json
			net.sf.json.JSONObject jsonWithDuplicateKeys = net.sf.json.JSONObject.fromObject(this.strJsonFile);
			
			JSONObject jsonObject = new JSONObject(jsonWithDuplicateKeys);
			
			
			log.info("reading JSON file...");
			
			readLines(jsonObject, location, locations);
			
			
		} catch (Exception exception) {	
			
			log.error(exception.getMessage());
			
			exception.printStackTrace();
		}
	}
	
	/**
	 * Lee el conjunto de llaves y valores para el archivo JSON.
	 * 
	 * @param jsonObject
	 * @param location
	 * @param locations
	 */
	private void readLines(JSONObject jsonObject, Location location, Set<BankEntityDTO> locations) {
		
		Iterator<String> firstLevelKeys  = jsonObject.keys();
		Iterator<String> secondLevelKeys = null;
		Iterator<String> thirdLevelKeys  = null;
		String firstKey = null;
		String secondKey = null;
		String lastKey = null;
		JSONArray jsonArray = null;
		
		
		while (firstLevelKeys.hasNext()) {
			
			firstKey = firstLevelKeys.next();
			
			secondLevelKeys = jsonObject.getJSONObject(firstKey).keys();
			
			while (secondLevelKeys.hasNext()) {
				
				secondKey = secondLevelKeys.next();
				
				thirdLevelKeys = jsonObject.getJSONObject(firstKey).getJSONObject(secondKey).keys();
				
				while (thirdLevelKeys.hasNext()) {
					
					lastKey = thirdLevelKeys.next();
					
					jsonArray = jsonObject.getJSONObject(firstKey).getJSONObject(secondKey).getJSONArray(lastKey);
					
					setValuesOfArray(jsonArray, location, locations);
				}
			}
		}
	}
	
	
	/**
	 * Obtiene la locación de un cajero automático o una sucursal
	 * 
	 * @param jsonArray jsonArray arreglo que contiene los valores para encontrar
	 * 				    la ubicación de un cajero automático o una sucursal
	 * @param location  locación proporcionada por el cliente
	 * @param locations conjunto de locaciones encontradadas de acuerdo al cliente
	 */
	private void setValuesOfArray(JSONArray jsonArray, Location location, Set<BankEntityDTO> locations) {
		
		//si no es un cajero automático o una sucursal regresa el control
		if (!verifyTypeOfPlace(jsonArray.getString(ValidationConstants.POSITION_FOR_TYPE_OF_PLACE))) {
			
			return;	
		}
		
		getValidatedBankEntity(jsonArray, location)
			.ifPresent(bankEntity -> locations.add(bankEntity));
	}
	
	
	/**
	 * Valida que los campos del cliente concuerden con los
	 * del arreglo.
	 * 
	 * @param jsonArray
	 * @param location
	 * @return
	 */
	private Optional<BankEntityDTO> getValidatedBankEntity(JSONArray jsonArray, Location location) {
		
		final String LATITUDE  = jsonArray.getString(ValidationConstants.POSITION_FOR_LATITUDE);
		final String LONGITUDE = jsonArray.getString(ValidationConstants.POSITION_FOR_LONGITUDE);
		final String ADDRESS   = jsonArray.getString(ValidationConstants.POSITION_FOR_ADDRESS);
		final String STATE     = jsonArray.getString(ValidationConstants.POSITION_FOR_STATE);
		final String TYPE      = jsonArray.getString(ValidationConstants.POSITION_FOR_TYPE_OF_PLACE);
		
		int counter = 0;
		
		BankEntityDTO bankEntity = new BankEntityDTO();
				
		
		//postalCode
		if (!location.getPostalCode().isEmpty() && 
			 ADDRESS.contains(location.getPostalCode().getNumber())) {
			
			counter++;
		} 
		
		//address or state
		if (!location.getPlace().isEmpty() &&
			(ADDRESS.contains(location.getPlace().getName()) ||
		     STATE.equals(location.getPlace().getName()))) {
			
			counter++;
		}

		
		//lattitude
		if (!ValidationConstants.DEFAULT_VALUE.equals(location.getGps().getLatitude()) &&
			(LATITUDE.equals(location.getGps().latitudeToString()) ||
			(GPSUtils.isEqualInIntegerPart(new BigDecimal(LATITUDE), location.getGps().getLatitude()) && 
			 GPSUtils.areFirst4DecimalsEquals(new BigDecimal(LATITUDE), location.getGps().getLatitude())))) {
			
				bankEntity.setLongitude(LATITUDE);
			
				counter++;
		}
		
		//longitude
		if (!ValidationConstants.DEFAULT_VALUE.equals(location.getGps().getLongitude()) &&
			 (LONGITUDE.equals(location.getGps().longitudeToString()) ||
			 (GPSUtils.isEqualInIntegerPart(new BigDecimal(LONGITUDE), location.getGps().getLongitude()) && 
			 GPSUtils.areFirst4DecimalsEquals(new BigDecimal(LONGITUDE), location.getGps().getLongitude()))) ) {
			
				bankEntity.setLongitude(LONGITUDE);
				
				counter++;
		}
	
		
		//si no hay ningún valor coincidente en el arrelgo,
		//entonces se regresa un opcional vacío.
		if (counter == 0 || (location.containsAll() && counter != 4)) {
		
			return Optional.empty();
		}
		
		bankEntity.setLatitude(LATITUDE);
		bankEntity.setLongitude(LONGITUDE);
		bankEntity.setAddress(ADDRESS);
		bankEntity.setState(STATE);
		bankEntity.setType(TYPE);
		
		log.info("Found entity of type: " + bankEntity.getType());
		
		return Optional.of(bankEntity);
	}
	
	
	/**
	 * Verifica si el lugar a buscar es un cajero automático
	 * o una sucursal de cualquier tipo.
	 * 
	 * 
	 * @param place si el lugar es un cajero automático o una sucursal
	 * 
	 * @return      true si es una cajero automático o una sucursal, 
	 * 				false de lo contrario 
	 */
	private boolean verifyTypeOfPlace(String place) {
		
		if (BankEntityPlace.ATM.toString().contains(place)) {
			return true;
		}
		
		if (BankEntityPlace.SUCURSAL.toString().contains(place.toUpperCase())) {
			return true;
		}
		
		return false;
	}
}
