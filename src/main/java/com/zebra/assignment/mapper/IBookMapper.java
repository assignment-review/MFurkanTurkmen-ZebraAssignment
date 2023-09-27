package com.zebra.assignment.mapper;

import com.zebra.assignment.dto.request.BookSaveRequestDto;
import com.zebra.assignment.dto.request.BookUpdateRequestDto;
import com.zebra.assignment.dto.response.BookResponseDto;
import com.zebra.assignment.repository.entity.Book;
import org.springframework.stereotype.Component;

public interface IBookMapper {
    Book toBook(BookSaveRequestDto dto);
    BookResponseDto toDto(Book book);
    Book toBook(BookUpdateRequestDto dto);
}
