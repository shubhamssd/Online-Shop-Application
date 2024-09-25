package com.shubhamssd.eCommerce_MicroServices.productservice;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductserviceApplicationTests {
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
					"name": "iphone 16pro",
					"description": "iphone 16pro description",
					"price": 1000
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/products")
				.then()
				.statusCode(201)
				.body("id", (Matcher<?>) Matchers.notNullValue())
				.body("name", (Matcher<?>) Matchers.equalTo("iphone 16pro"))
				.body("description", (Matcher<?>) Matchers.equalTo("iphone 16pro description"))
				.body("price", (Matcher<?>) Matchers.equalTo(1000));
	}

}
