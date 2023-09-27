package com.zebra.assignment.mapper.impl;

import com.zebra.assignment.dto.request.MemberSaveRequestDto;
import com.zebra.assignment.dto.response.MemberResponseDto;
import com.zebra.assignment.mapper.IMemberMapper;
import com.zebra.assignment.repository.entity.Book;
import com.zebra.assignment.repository.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper implements IMemberMapper {
    @Override
    public Member toMember(MemberSaveRequestDto dto) {
        if (dto==null){
            return null;
        }else {
            Member member= Member.builder()
                    .email(dto.getEmail())
                    .name(dto.getName())
                    .phoneNumber(dto.getPhoneNumber())
                    .build();
            return member;
        }
    }

    @Override
    public MemberResponseDto toDto(Member member) {
        if (member==null){
            return null;
        }else {
            MemberResponseDto dto=MemberResponseDto.builder()
                    .email(member.getEmail())
                    .phoneNumber(member.getPhoneNumber())
                    .name(member.getName())
                    .build();
            return dto;
        }
    }
}
