package com.mooveit.cars.configurations;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.Submodel;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.listener.JobListener;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public EntityManagerFactory emf;

	@Bean
	public StaxEventItemReader<Model> xmlFileReader() {
		StaxEventItemReader<Model> staxEventItemReader = new StaxEventItemReader<>();
		staxEventItemReader.setResource(new ClassPathResource("ford-example.xml"));
		staxEventItemReader.setFragmentRootElementNames(new String[] { "MODEL", "WHEELS", "ENGINE", "SUBMODELS" });
		Jaxb2Marshaller unMarshaller = new Jaxb2Marshaller();
		unMarshaller.setClassesToBeBound(Model.class, Engine.class, Wheel.class, Submodel.class);
		staxEventItemReader.setUnmarshaller(unMarshaller);
		return staxEventItemReader;
	}

	@Bean
	public JpaItemWriter<Model> writeToDb() {
		JpaItemWriter<Model> writer = new JpaItemWriter<Model>();
		writer.setEntityManagerFactory(emf);
		return writer;
	}

	@Bean
	public Step step1(JpaItemWriter<Model> writer) {
		return stepBuilderFactory.get("step1").<Model, Model>chunk(10).reader(xmlFileReader()).writer(writer).build();
	}

	@Bean
	public Job importCarsModelsJob(JobListener listener, Step step1) {
		return jobBuilderFactory.get("importCarsModelsJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1).end().build();
	}
}