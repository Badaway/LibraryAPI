package com.example.LibraryAPI.mapping;

import com.example.LibraryAPI.Dto.BookDto;
import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.model.Book;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper extends ConfigurableMapper {


    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Book.class, BookResponseDto.class)
                .field("author.name", "author")
                .field("member.name", "member").byDefault()
                .register();
        factory.classMap(Book.class, BookDto.class)
                .field("author.id", "authorId")
                .field("member.id", "memberId").byDefault()
                .register();
    }




}
