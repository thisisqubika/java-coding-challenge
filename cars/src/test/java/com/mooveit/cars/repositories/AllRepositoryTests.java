package com.mooveit.cars.repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EngineRepositoryTest.class, 
	SpecificationRepositoryTest.class, 
	WheelRepositoryTest.class,
	IngestionRepositoryTest.class})
public class AllRepositoryTests {

}
