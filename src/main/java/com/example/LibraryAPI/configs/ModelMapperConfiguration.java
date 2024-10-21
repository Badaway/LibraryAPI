package com.example.LibraryAPI.configs;

import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.model.Book;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Book, BookResponseDto> typeMap = modelMapper.createTypeMap(Book.class, BookResponseDto.class)
                .addMapping(src-> src.getAuthor().getName(),BookResponseDto::setAuthor)
                .addMapping(src-> src.getMember().getName(),BookResponseDto::setMember);
        return modelMapper;
    }


}
