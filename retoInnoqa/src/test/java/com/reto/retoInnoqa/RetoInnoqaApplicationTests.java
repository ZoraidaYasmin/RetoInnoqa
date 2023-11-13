package com.reto.retoInnoqa;

import com.reto.retoInnoqa.Controller.PriceController;
import com.reto.retoInnoqa.Entity.Prices;
import com.reto.retoInnoqa.Service.PriceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.AssertionErrors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class RetoInnoqaApplicationTests {

	@Mock
	private PriceService priceService;

	@InjectMocks
	private PriceController priceController;

	@Test
	void test1() {

		LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
		Long productId = 35455L;
		Long brandId = 1L;


		List<Prices> expectedPrices = Collections.singletonList(
				Prices.builder()
						.id(1L)
						.brandId(1L)
						.startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
						.endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
						.priceList(1L)
						.productId(35455L)
						.priority(0)
						.price(35.50)
						.curr("EUR")
						.build()
		);

		Mockito.when(priceService.priceParameters(date, productId, brandId)).thenReturn(expectedPrices);
		ResponseEntity<List<Prices>> responseEntity = priceController.priceParameters(date, productId, brandId);

		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}


	@Test
	void test2() {

		LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
		Long productId = 35455L;
		Long brandId = 1L;

		List<Prices> expectedPrices = Collections.singletonList(
				Prices.builder()
						.id(2L)
						.brandId(1L)
						.startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
						.endDate(LocalDateTime.parse("2020-06-14T18:30:00"))
						.priceList(2L)
						.productId(35455L)
						.priority(1)
						.price(25.45)
						.curr("EUR")
						.build()
		);

		Mockito.when(priceService.priceParameters(date, productId, brandId)).thenReturn(expectedPrices);
		ResponseEntity<List<Prices>> responseEntity = priceController.priceParameters(date, productId, brandId);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void test3() {

		LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");
		Long productId = 35455L;
		Long brandId = 1L;

		List<Prices> expectedPrices = Collections.singletonList(
				Prices.builder()
						.id(1L)
						.brandId(1L)
						.startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
						.endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
						.priceList(1L)
						.productId(35455L)
						.priority(0)
						.price(35.5)
						.curr("EUR")
						.build()
		);
		Mockito.when(priceService.priceParameters(date, productId, brandId)).thenReturn(expectedPrices);
		ResponseEntity<List<Prices>> responseEntity = priceController.priceParameters(date, productId, brandId);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void test4() {

		LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");
		Long productId = 35455L;
		Long brandId = 1L;


		List<Prices> expectedPrices = Collections.singletonList(
				Prices.builder()
						.id(3L)
						.brandId(1L)
						.startDate(LocalDateTime.parse("2020-06-15T00:00:00"))
						.endDate(LocalDateTime.parse("2020-06-15T00:00:00"))
						.priceList(3L)
						.productId(35455L)
						.priority(1)
						.price(30.5)
						.curr("EUR")
						.build()
		);
		Mockito.when(priceService.priceParameters(date, productId, brandId)).thenReturn(expectedPrices);
		ResponseEntity<List<Prices>> responseEntity = priceController.priceParameters(date, productId, brandId);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	void test5() {

		LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
		Long productId = 35455L;
		Long brandId = 1L;

		List<Prices> expectedPrices = Collections.singletonList(
				Prices.builder()
						.id(4L)
						.brandId(1L)
						.startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
						.endDate(LocalDateTime.parse("2020-06-15T16:00:00"))
						.priceList(4L)
						.productId(35455L)
						.priority(1)
						.price(38.95)
						.curr("EUR")
						.build()
		);

		Mockito.when(priceService.priceParameters(date, productId, brandId)).thenReturn(expectedPrices);
		ResponseEntity<List<Prices>> responseEntity = priceController.priceParameters(date, productId, brandId);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
