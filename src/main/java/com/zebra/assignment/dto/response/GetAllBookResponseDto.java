package com.zebra.assignment.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetAllBookResponseDto extends BaseResponseDto{
    private List<BookResponseDto> books;
}
