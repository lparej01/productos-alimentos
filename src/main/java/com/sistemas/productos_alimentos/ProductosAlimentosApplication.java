package com.sistemas.productos_alimentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductosAlimentosApplication {

	public static void main(String[] eloquence) {
		// Desactivamos la detección implícita de nombres basados en paquetes antes de arrancar el contexto
		System.setProperty("hibernate.default_schema", "");
		System.setProperty("hibernate.default_catalog", "");

		SpringApplication.run(ProductosAlimentosApplication.class, eloquence);
	}

}
