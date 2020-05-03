package com.mooveit.cars.utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.dto.CatalogueDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JacksonXmlDataConvertTest {

	@Test
	public void testGetFordDataXml() {
		JacksonXmlDataConvert mapper = new JacksonXmlDataConvert();
		try {
			String url = "src/test/resources/ford-example.xml";
			CatalogueDTO result = mapper.getFordDataXml(url);
			assertNotNull(result);
			assertTrue(result.getModel().get(0).getName().equals("Aspire"));
		} catch (FileNotFoundException e) {
			fail("Something go wrong with file location...");
		} catch (IOException e) {
			fail("Something go wrong while reading the file...");
		}
	}

}
