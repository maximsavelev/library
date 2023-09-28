package ru.savelyev.library.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDTO {
    @Size(min = 2, max = 30, message = "Should be more than 2 and less than 30 symbols")
    private String firstName;
    @Size(min = 2, max = 30, message = "Should be more than 2 and less than 30 symbols")
    private String lastName;
    private LocalDate birthDate;
    private String country;
}
