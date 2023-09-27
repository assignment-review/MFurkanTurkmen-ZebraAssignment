package com.zebra.assignment.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BookMemberResponseDto extends BaseResponseDto {
    private Long memberId;
    private Long bookId;
}
