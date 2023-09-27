package com.zebra.assignment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequestDto {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String author;
    @NotNull
    @NotBlank
    private String isbn;
    @NotNull
    @NotBlank
    private int copies;
}
