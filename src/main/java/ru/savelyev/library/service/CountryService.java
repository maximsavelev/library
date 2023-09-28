package ru.savelyev.library.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.savelyev.library.model.Country;
import ru.savelyev.library.repository.CountryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryService {
    private final CountryRepository countryRepository;

    public Country findCountryByName(String name) {
        log.info(String.format("Fetching country by name '%s' ", name));
        return countryRepository.findCountryByCountryName(name)
                .orElseThrow(EntityNotFoundException::new);
    }
}
