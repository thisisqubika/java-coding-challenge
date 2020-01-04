package com.mooveit.cars.configurations;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
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

	private static final Logger LOG = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public StaxEventItemReader<Wheel> reader() {
		StaxEventItemReader<Wheel> staxEventItemReader = new StaxEventItemReader<>();
		staxEventItemReader.setResource(new ClassPathResource("ford-example.xml"));
		staxEventItemReader.setFragmentRootElementName("WHEELS");
		Jaxb2Marshaller unMarshaller = new Jaxb2Marshaller();
		unMarshaller.setClassesToBeBound(Wheel.class);
		staxEventItemReader.setUnmarshaller(unMarshaller);
		return staxEventItemReader;
	}

	@Bean
	public StaxEventItemReader<Engine> reader2() {
		StaxEventItemReader<Engine> staxEventItemReader = new StaxEventItemReader<>();
		staxEventItemReader.setResource(new ClassPathResource("ford-example.xml"));
		staxEventItemReader.setFragmentRootElementName("ENGINE");
		Jaxb2Marshaller unMarshaller = new Jaxb2Marshaller();
		unMarshaller.setClassesToBeBound(Engine.class);
		staxEventItemReader.setUnmarshaller(unMarshaller);
		return staxEventItemReader;
	}

	@Bean
	public StaxEventItemReader<Model> reader3() {
		StaxEventItemReader<Model> staxEventItemReader = new StaxEventItemReader<>();
		staxEventItemReader.setResource(new ClassPathResource("ford-example.xml"));
		staxEventItemReader.setFragmentRootElementName("MODEL");
		Jaxb2Marshaller unMarshaller = new Jaxb2Marshaller();
		unMarshaller.setClassesToBeBound(Model.class);
		staxEventItemReader.setUnmarshaller(unMarshaller);
		return staxEventItemReader;
	}

	@Bean
	public StaxEventItemReader<Submodel> reader4() {
		StaxEventItemReader<Submodel> staxEventItemReader = new StaxEventItemReader<>();
		staxEventItemReader.setResource(new ClassPathResource("ford-example.xml"));
		staxEventItemReader.setFragmentRootElementName("SUBMODELS");
		Jaxb2Marshaller unMarshaller = new Jaxb2Marshaller();
		unMarshaller.setClassesToBeBound(Submodel.class);
		staxEventItemReader.setUnmarshaller(unMarshaller);
		return staxEventItemReader;
	}


	@Bean
	public JdbcBatchItemWriter<Wheel> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Wheel>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO WHEELS (wheel_size, wheel_type) VALUES (:wheel_size, :wheel_type)")
				.dataSource(dataSource).build();
	}

	@Bean
	public JdbcBatchItemWriter<Engine> writer2(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Engine>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO ENGINES (engine_power, engine_type) VALUES (:engine_power, :engine_type)")
				.dataSource(dataSource).build();
	}

	@Bean
	public JdbcBatchItemWriter<Model> writer3(DataSource dataSource) {
		LOG.info("creando insert modelos");
		return new JdbcBatchItemWriterBuilder<Model>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO MODELS (model_name, model_from, model_to, model_type) VALUES (:model_name, :model_from, :model_to, :model_type)")
				.dataSource(dataSource).build();
	}

	@Bean
	public JdbcBatchItemWriter<Submodel> writer4(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Submodel>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO SUBMODELS (submodel_line, submodel_name) VALUES (:submodel_line, :submodel_name)")
				.dataSource(dataSource).build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Wheel> writer) {
		return stepBuilderFactory.get("step1").<Wheel, Wheel>chunk(10).reader(reader()).writer(writer).build();
	}

	@Bean
	public Step step2(JdbcBatchItemWriter<Engine> writer2) {
		return stepBuilderFactory.get("step2").<Engine, Engine>chunk(10).reader(reader2()).writer(writer2).build();
	}

	@Bean
	public Step step3(JdbcBatchItemWriter<Model> writer3) {
		return stepBuilderFactory.get("step3").<Model, Model>chunk(10).reader(reader3()).writer(writer3).build();
	}

	@Bean
	public Step step4(JdbcBatchItemWriter<Submodel> writer4) {
		return stepBuilderFactory.get("step4").<Submodel, Submodel>chunk(10).reader(reader4()).writer(writer4).build();
	}

	@Bean
	public Job importWheelJob(JobListener listener, Step step1, Step step2, Step step3, Step step4) {
		return jobBuilderFactory.get("importCarsModelsJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1).next(step2).next(step3).next(step4).end().build();
	}
}