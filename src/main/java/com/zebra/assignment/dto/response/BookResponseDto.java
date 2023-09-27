package com.zebra.assignment.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveResponseDto {
    private String title;
    private String author;
    private String isbn;
    private int copies;
}
