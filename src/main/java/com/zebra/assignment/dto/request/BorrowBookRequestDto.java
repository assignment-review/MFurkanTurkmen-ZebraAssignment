package com.zebra.assignment.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBookRequestDto {
    private Long memberId;
    private String isbn;
}
