package com.zebra.assignment.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_book")
public class Book extends BaseEntity{
    private String title;
    private String author;
    @Column(unique = true)
    private String isbn;
    private int copies;

}
