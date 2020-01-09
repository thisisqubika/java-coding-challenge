package com.mooveit.cars.shedules;

import com.mooveit.cars.configurations.ConfigurationProperties;
import com.mooveit.cars.tasks.FordIngesterTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class SchedulerTaskServices {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private FordIngesterTask fordIngesterTask;

    @Autowired
    private ConfigurationProperties configurationFileProperties;

    public void initTask() {
        CronTrigger fordIngesterTaskCronTrigger = new CronTrigger(configurationFileProperties.getFordIngesterCronTrigger());
        taskScheduler.schedule(fordIngesterTask, fordIngesterTaskCronTrigger);

        // another task
    }

}
