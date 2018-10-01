package com.example.demo.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step importCustomerAndTransactionData() {
        return stepBuilderFactory.get("importCustomerAndTransactionData")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("importCustomerAndTransactionData step");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Job  statementJob() {
        return jobBuilderFactory.get("statementJob")
                .start(importCustomerAndTransactionData())//reader, writer and processor
                .build();
    }

    @Bean //Change according to videos to read from csv files
    public FlatFileItemReader reader() {
        return new FlatFileItemReader();
    }
}
