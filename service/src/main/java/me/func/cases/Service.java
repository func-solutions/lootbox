package me.func.cases;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.cristalix.core.microservice.MicroServicePlatform;
import ru.cristalix.core.microservice.MicroserviceBootstrap;

/**
 * @author func 05.09.2022
 * @project cases
 */
public class Service {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		// Начинаем работу микросервиса
		//MicroserviceBootstrap.bootstrap(new MicroServicePlatform(2));
	}

}
