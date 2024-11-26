package com.example.LibraryAPI.controller;


import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(BatchController.baseControllerUri)
@RestController
public class BatchController {
    public static final String baseControllerUri = "/batch";
    public static final String sendNotificationOverDueBookBatchUri ="/overdue-notifications";
    public static final String startJobBody="Completed";


    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job bookJob;

    @PostMapping(sendNotificationOverDueBookBatchUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<String> startJob()  throws JobExecutionException {


        JobParameters jobParameters = new JobParametersBuilder()
                .addString("run.id", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(bookJob, jobParameters);

        return ResponseEntity.ok(startJobBody);

    }

}
