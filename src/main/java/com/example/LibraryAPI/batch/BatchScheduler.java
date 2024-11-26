package com.example.LibraryAPI.batch;


import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@EnableScheduling
public class BatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job bookJob;



    @Scheduled(cron = "*/5 * * * * *")
    public void runBatchJob() throws JobExecutionException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("run.id", String.valueOf(System.currentTimeMillis()))
                .addString("run.type", "Scheduled")
                .toJobParameters();

        jobLauncher.run(bookJob, jobParameters);
        System.out.println("Scheduled Batch Job Executed");

    }


}
