package com.example.LibraryAPI.batch;

import com.example.LibraryAPI.Dto.BookReaderDto;
import com.example.LibraryAPI.model.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookWriter implements ItemWriter<BookReaderDto> {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final String queueName = "Book";

    @Override
    public void write(Chunk<? extends BookReaderDto> books) throws Exception {

        for (BookReaderDto book:books)
        {
            System.out.println("sending : "+book.getIsbn());
            rabbitTemplate.convertAndSend(queueName, book.toString());
        }

    }
}
