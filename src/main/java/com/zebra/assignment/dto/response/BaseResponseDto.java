package com.zebra.assignment.dto.response;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponseDto {
    private int statusCode;
    private String message;
}
