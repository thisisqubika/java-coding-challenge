package com.mooveit.cars.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.domain.Submodel;

@Component
public class JobListener extends JobExecutionListenerSupport {

	private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JobListener(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info("Job Finished!!  Verifying results");
			jdbcTemplate
					.query("SELECT wheel_size,wheel_type FROM WHEELS",
							(rs, row) -> new Wheel(rs.getString(1), rs.getString(2)))
					.forEach(wheel -> LOG.info("Wheels Added <" + wheel + ">"));
			
			jdbcTemplate.query("SELECT engine_power,engine_type FROM ENGINES",
					(rs, row) -> new Engine(rs.getInt(1), rs.getString(2)))
			.forEach(engine -> LOG.info("Engines Added <" + engine + ">"));
			
			jdbcTemplate.query("SELECT model_name, model_from, model_to, model_type FROM MODELS",
					(rs, row) -> new Model(rs.getString(1), rs.getInt(2), rs.getInt(3),rs.getString(4)))
			.forEach(model -> LOG.info("Models Added <" + model + ">"));
			
			jdbcTemplate.query("SELECT submodel_name, submodel_line FROM SUBMODELS",
					(rs, row) -> new Submodel(rs.getString(1), rs.getString(2)))
			.forEach(submodel -> LOG.info("Submodels Added <" + submodel + ">"));
		}
	}
}
