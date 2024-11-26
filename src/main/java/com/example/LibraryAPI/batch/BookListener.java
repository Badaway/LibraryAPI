package com.example.LibraryAPI.batch;

import org.jetbrains.annotations.NotNull;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.StepExecution;

public class BookListener implements StepExecutionListener {


    @Override
    public void beforeStep(@NotNull StepExecution stepExecution) {




    }

    @Override
    public ExitStatus afterStep(@NotNull StepExecution stepExecution) {


        return null;
    }
}
