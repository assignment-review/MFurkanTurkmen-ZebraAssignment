package com.zebra.assignment.mapper.impl;

import com.zebra.assignment.dto.request.BookSaveRequestDto;
import com.zebra.assignment.dto.request.BookUpdateRequestDto;
import com.zebra.assignment.dto.response.BookResponseDto;
import com.zebra.assignment.repository.entity.Book;
import com.zebra.assignment.mapper.IBookMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements IBookMapper {
    @Override
    public Book toBook(BookSaveRequestDto dto) {
        if (dto == null) {
            return null;
        } else {
            Book book=Book.builder()
                    .title(dto.getTitle())
                    .isbn(dto.getIsbn())
                    .author(dto.getAuthor())
                    .copies(dto.getCopies())
                    .build();
            return book;
        }
    }

    @Override
    public BookResponseDto toDto(Book book) {
        if (book == null) {
            return null;
        } else {
            BookResponseDto dto= BookResponseDto.builder()
                    .title(book.getTitle())
                    .isbn(book.getIsbn())
                    .author(book.getAuthor())
                    .copies(book.getCopies())
                    .build();
            return dto;
        }
    }

    @Override
    public Book toBook(BookUpdateRequestDto dto) {
        if (dto==null){
            return null;
        }else {
            Book book= Book.builder()

                    .title(dto.getTitle())
                    .copies(dto.getCopies())
                    .build();
            return book;
        }
    }


}
