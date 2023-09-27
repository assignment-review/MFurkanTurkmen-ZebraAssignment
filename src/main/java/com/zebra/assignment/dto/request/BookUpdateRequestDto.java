package com.zebra.assignment.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequestDto {
    private Long id;
    private String title;
    private String isbn;
    private int copies;
}
