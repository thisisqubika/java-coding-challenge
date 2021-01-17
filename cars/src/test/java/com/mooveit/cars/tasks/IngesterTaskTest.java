package com.mooveit.cars.tasks;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Ingestion;
import com.mooveit.cars.ingestion.BrandBuilder;
import com.mooveit.cars.ingestion.MergeBrandsIngestStrategy;
import com.mooveit.cars.repositories.IngestionDTO;
import com.mooveit.cars.repositories.IngestionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngesterTaskTest {

	@Autowired
	IngesterTask task;

	@MockBean
	MergeBrandsIngestStrategy strategy;

	@MockBean
	IngestionRepository ingestRepository;

	@Autowired
	List<BrandBuilder> brandBuilders;

	
	private static String testSource = "sample-catalog.xml";
	
	private static String testBrandName = "Test Brand";
	
	private static Brand brandMock;
	
	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public List<BrandBuilder> brandBuilders() {
			
			// Create Mock Builder
			Map<String, Brand> brands = new HashMap<String, Brand>();
			brands.put(testSource, brandMock);
			
			BrandBuilder bb = mock(BrandBuilder.class);
			when(bb.getBrandName()).thenReturn(testBrandName);
			when(bb.createBrands(any())).thenReturn(brands);
			
			return Arrays.asList(bb);
		}
	}

	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testIngestFiles() {

		// Empty source to omit
		when(ingestRepository.findAllByBrandName(testBrandName)).thenReturn(new HashSet<IngestionDTO>());
		when(strategy.ingest(testSource, brandMock)).thenReturn(mock(Ingestion.class));
		
		task.ingestBrands();

		verify(ingestRepository, times(1)).findAllByBrandName(eq(testBrandName));
		verify(strategy, times(1)).ingest(eq(testSource), eq(brandMock));
		verify(ingestRepository, times(1)).save(any(Ingestion.class));

	}

}

