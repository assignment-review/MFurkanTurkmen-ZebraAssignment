package com.zebra.assignment.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMemberResponseDto extends BaseResponseDto{
    List<MemberResponseDto> dtos;
}
