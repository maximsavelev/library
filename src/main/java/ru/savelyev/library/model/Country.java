package ru.savelyev.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Country extends BaseEntity {
    @Column(name = "country_name")
    private String countryName;
}
