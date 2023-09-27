package com.zebra.assignment.mapper;

import com.zebra.assignment.dto.request.MemberSaveRequestDto;
import com.zebra.assignment.dto.response.MemberResponseDto;
import com.zebra.assignment.repository.entity.Member;

public interface IMemberMapper {
    Member toMember(MemberSaveRequestDto dto);
    MemberResponseDto toDto(Member member);
}
