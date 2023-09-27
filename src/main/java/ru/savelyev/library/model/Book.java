package ru.savelyev.library.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "book")
public class Book extends BaseEntity {
    private String title;

    @Column(name = "publication_year")
    private String publicationYear;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "count_of_pages")
    private int countOfPages;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
