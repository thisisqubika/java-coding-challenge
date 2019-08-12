package com.mooveit.cars.ingestion;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Engine;

public class FordBrandBuilderTest {

	@Test
	public void testCreateBrandFromXmlFile() {

		FordBrandBuilder builder = new FordBrandBuilder();

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ford-test-example.xml");

		Brand brand = builder.createBrandFromXml(inputStream);
		assertNotNull(brand);

		brand.getSpecifications().forEach(x -> assertNotNull(x.getEngine()));
		brand.getSpecifications().forEach(x -> assertNotNull(x.getWheel()));
	}

	@Test
	public void testCreateBrandFromXmlString() {

		FordBrandBuilder builder = new FordBrandBuilder();

		String xml = String.join("/n",
				"<CATALOGUE>" + "<MODEL name=\"Aspire\" from=\"1994\" to=\"1997\" type=\"subcompact\">", "<ENGINE />",
				"</MODEL>", "</CATALOGUE>");

		InputStream inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));

		Brand brand = builder.createBrandFromXml(inputStream);
		assertNotNull(brand);

		Engine eng = brand.getSpecifications().findFirst().get().getEngine();

		// Empty Engine
		assertNotNull(eng);
		assertNull(eng.getType());
		assertNull(eng.getPower());

		// Null Wheel
		assertNull(brand.getSpecifications().findFirst().get().getWheel());

	}

	@Test
	public void testGetSourceXMLFiles() {

		FordBrandBuilder builder = new FordBrandBuilder();

		assertTrue(Pattern.compile(String.format("^(.+)%sford-(.+).xml$", File.separator))
				.matcher("/User/nicolas/ford-anytexthere.xml").matches());

		List<File> testFiles = builder.getSourceXMLFiles(new HashSet<>());
		assertTrue(testFiles.size() == 1);

	}

}
