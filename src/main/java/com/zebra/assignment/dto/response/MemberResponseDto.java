package com.zebra.assignment.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto extends BaseResponseDto{
    private String name;
    private String email;
    private String phoneNumber;
}
