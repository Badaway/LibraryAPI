//package com.example.LibraryAPI.mapping;
//
//import com.example.LibraryAPI.Dto.BookDto;
//import com.example.LibraryAPI.Dto.BookResponseDto;
//import com.example.LibraryAPI.model.Book;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeMap;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class ModelMapperConfiguration {
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        TypeMap<Book, BookResponseDto> typeMapBook = modelMapper.createTypeMap(Book.class, BookResponseDto.class)
//                .addMapping(src-> src.getAuthor().getName(),BookResponseDto::setAuthor)
//                .addMapping(src-> src.getMember().getName(),BookResponseDto::setMember);
//        TypeMap<Book, BookDto> typeMapBorrowBook = modelMapper.createTypeMap(Book.class, BookDto.class)
//                .addMapping(src-> src.getAuthor().getId(),BookDto::setAuthorId)
//                .addMapping(src-> src.getMember().getId(),BookDto::setMemberId);
//        return modelMapper;
//    }
//
//
//}
