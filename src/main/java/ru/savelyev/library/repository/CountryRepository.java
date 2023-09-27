package ru.savelyev.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.savelyev.library.model.Country;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country,Long> {
    Optional<Country> findCountryByCountryName(String name);
}
