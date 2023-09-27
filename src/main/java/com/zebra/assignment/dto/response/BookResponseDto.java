package com.zebra.assignment.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto extends BaseResponseDto {
    private String title;
    private String author;
    private String isbn;
    private int copies;
}
