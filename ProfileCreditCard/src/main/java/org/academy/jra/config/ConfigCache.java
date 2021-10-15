package org.academy.jra.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para
 * la cache en la aplicación.
 * 
 * @author joel
 *
 */
@Configuration
public class ConfigCache {

	@Bean
	public CacheManager cacheManager() {
		
		return new ConcurrentMapCacheManager();
	}
}
