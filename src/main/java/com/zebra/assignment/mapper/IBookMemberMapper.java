package com.zebra.assignment.mapper;

import com.zebra.assignment.dto.response.BookMemberResponseDto;
import com.zebra.assignment.repository.entity.Book_Member;

public interface IBookMemberMapper {
    BookMemberResponseDto toDto(Book_Member bookBarrower);
}
