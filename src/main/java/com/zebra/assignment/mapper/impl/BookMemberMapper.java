package com.zebra.assignment.mapper.impl;

import com.zebra.assignment.dto.response.BookMemberResponseDto;
import com.zebra.assignment.mapper.IBookMemberMapper;
import com.zebra.assignment.repository.entity.Book_Member;
import org.springframework.stereotype.Component;

@Component
public class BookBarrowerMapper implements IBookMemberMapper {

    @Override
    public BookMemberResponseDto toDto(Book_Member bookBarrower) {
        if (bookBarrower==null){
            return null;
        }else {
            BookMemberResponseDto dto= BookMemberResponseDto.builder()
                    .memberId(bookBarrower.getMemberId())
                    .bookId(bookBarrower.getBookId())
                    .build();
            return dto;
        }
    }
}
