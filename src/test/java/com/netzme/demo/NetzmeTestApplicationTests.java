package com.netzme.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetzmeTestApplicationTests {

	@Test
	public void contextLoads() {
		int i = 1;
		assertEquals(6, i + 4);
	}

}