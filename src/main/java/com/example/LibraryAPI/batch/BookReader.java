package com.example.LibraryAPI.batch;

import com.example.LibraryAPI.Dto.BookReaderDto;
import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.service.BookService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookReader  implements ItemReader<Book> {

    @Autowired
    private BookService bookService;

    private  List<Book> books;



    private int currentIndex=0 ;

    public BookReader(List<Book> books) {
        this.books = books;
    }


    @Override
    public Book read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {




        if (currentIndex < books.size()) {
            return books.get(currentIndex++);
        } else {
            books=bookService.getBooksShouldReturn();
            return null;
        }


    }
}
