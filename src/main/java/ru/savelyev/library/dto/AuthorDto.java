package ru.savelyev.library.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String country;
}
