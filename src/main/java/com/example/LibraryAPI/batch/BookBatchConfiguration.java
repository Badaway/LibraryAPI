package com.example.LibraryAPI.batch;


import com.example.LibraryAPI.Dto.BookReaderDto;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.service.BookService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BookBatchConfiguration {
    public static final String queueName = "BookCreation";


    @Autowired
    private BookService bookService;




    @Bean
    public Job bookJob(JobRepository jobRepository, Step bookStep) {
        return new JobBuilder("bookJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(bookStep)
                .end()
                .build();
    }

    @Bean
    public Step bookStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager) {
        return new StepBuilder("bookStep", jobRepository)
                .<Book, BookReaderDto>chunk(1, transactionManager)
                .reader(bookReader())
                .processor(bookProcessor())
                .writer(bookWriter())
                .allowStartIfComplete(true)
                .build();
    }


    @Bean
    public ItemProcessor<Book,BookReaderDto> bookProcessor() {
        return new BookProcessor();
    }

    @Bean
    public ItemReader<Book> bookReader() {

        return new BookReader(bookService.getBooksShouldReturn());
    }

    @Bean
    public ItemWriter<BookReaderDto> bookWriter() {
        return new BookWriter();
    }

//
//
//    @Bean
//    public Job bookJob(JobRepository jobRepository, Step bookStep) {
//        return new JobBuilder("bookJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .flow(bookStep)
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step bookStep(JobRepository jobRepository,
//                         PlatformTransactionManager transactionManager) {
//        return new StepBuilder("bookStep", jobRepository)
//                .<Book, BookReaderDto>chunk(1, transactionManager)
//                .reader(bookReader())
//                .processor(bookProcessor())
//                .writer(bookWriter())
//                .build();
//    }




}
