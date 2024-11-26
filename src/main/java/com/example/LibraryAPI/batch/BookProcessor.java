package com.example.LibraryAPI.batch;

import com.example.LibraryAPI.Dto.BookReaderDto;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookProcessor implements ItemProcessor<Book,BookReaderDto> {

    @Autowired
    private Mapper mapper;

    @Override
    public BookReaderDto process(@NotNull Book book) throws Exception {
        return mapper.map(book,BookReaderDto.class);
    }

//    @Override
//    public List<BookReaderDto> process(List<Book> books) throws Exception {
//
//        var bookReader =new ArrayList<BookReaderDto>();
//
//        for (Book book:books)
//        {
//            var bookReaderDto =mapper.map(book, BookReaderDto.class);
//            bookReader.add(bookReaderDto);
//        }
//
//        return bookReader;
//    }
}
