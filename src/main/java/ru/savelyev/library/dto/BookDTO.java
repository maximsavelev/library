package ru.savelyev.library.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private String publicationYear;
    private String isbn;
    private int countOfPages;
    private String genre;
    private String author;
}
