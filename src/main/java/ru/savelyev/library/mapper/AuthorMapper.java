package ru.savelyev.library.mapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.savelyev.library.dto.AuthorDTO;
import ru.savelyev.library.exception.InvalidDataException;
import ru.savelyev.library.model.Author;
import ru.savelyev.library.model.Country;
import ru.savelyev.library.service.CountryService;

import java.util.Locale;

@RequiredArgsConstructor
@Component
public class AuthorMapper {
    private final ModelMapper mapper;
    private final CountryService countryService;
    private final MessageSource messageSource;
    @PostConstruct
    public void setupMapper() {
        mapper.typeMap(AuthorDTO.class, Author.class)
                .addMappings(m -> m.skip(Author::setCountry))
                .setPostConverter(toEntityConverter());
        mapper.typeMap(Author.class, AuthorDTO.class)
                .addMappings(m -> m.map(src -> src.getCountry().getCountryName(), AuthorDTO::setCountry));
    }

    public AuthorDTO mapToDTO(Author author) {
        return mapper.map(author, AuthorDTO.class);
    }

    public Author mapToEntity(AuthorDTO dto) {
        return mapper.map(dto, Author.class);
    }

    void mapSpecificFields(AuthorDTO source, Author destination) {
        try {
            Country countryByName = countryService.findCountryByName(source.getCountry());
            destination.setCountry(countryByName);
        } catch (Exception e) {
            throw new InvalidDataException(
                    messageSource.getMessage("error_country_not_found", new Object[0], Locale.getDefault()));
        }
    }
    public Converter<AuthorDTO, Author> toEntityConverter() {
        return context -> {
            AuthorDTO source = context.getSource();
            Author destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

}
