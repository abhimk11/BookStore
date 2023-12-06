package com.bookstore.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;

@Table("book_details")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    private int bookId;
    private String name;
    private String description;
    private String publisher;
    private String author;
}
