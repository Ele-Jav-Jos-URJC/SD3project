package es.gestorincidencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import com.mysql.jdbc.log.LogFactory;

@EnableCaching
@SpringBootApplication
public class PruebaH2Application {
	

	public static void main(String[] args) {
	
		SpringApplication.run(PruebaH2Application.class, args);
	}
	

	@Bean
	public CacheManager cacheManager() {
		

		return new ConcurrentMapCacheManager("Incidencia");
	}
}
