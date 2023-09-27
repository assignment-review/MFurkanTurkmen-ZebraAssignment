package com.zebra.assignment.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnBookRequestDto {
    private Long memberId;
    private String isbn;
}
