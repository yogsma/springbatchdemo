package com.betterjavacode.springbatchdemo.configurations.processor;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobCompletionListener extends JobExecutionListenerSupport
{
    @Override
    public void afterJob(JobExecution jobExecution)
    {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED)
        {
            // Log statement
            System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
        }
    }
}
