package com.zebra.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBorrowerResponseDto extends BaseResponseDto {
    List<MemberResponseDto> dtos;

}
