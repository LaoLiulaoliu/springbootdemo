package com.learn.springbootdemo;

import com.learn.springbootdemo.dao.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class SpringbootdemoApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test() {
		long productId = 1;
		Product product = restTemplate.getForObject("http://localhost:" + port + "/product/" + productId, Product.class);
		assertThat(product.getPrice()).isEqualTo(200);

		Product newProduct = new Product();
		long newPrice = new Random().nextLong();
		newProduct.setName("newly name");
		newProduct.setPrice(newPrice);
		restTemplate.put("http://localhost:" + port + "/product/" + productId, newProduct);

		Product testProduct = restTemplate.getForObject("http://localhost:" + port + "/product/" + productId, Product.class);
		assertThat(testProduct.getPrice()).isEqualTo(newPrice);
	}

}
