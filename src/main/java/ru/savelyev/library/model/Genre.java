package ru.savelyev.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "genre")
public class Genre extends BaseEntity {
    @Column(name = "genre_name")
    private String genreName;
}
