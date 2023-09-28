package ru.savelyev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.savelyev.library.exception.EntityNotFoundException;
import ru.savelyev.library.exception.NoSuchResourceException;
import ru.savelyev.library.model.Author;
import ru.savelyev.library.repository.AuthorRepository;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    private final MessageSource messageSource;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException
                                (messageSource.getMessage("error_not_found", new Object[0], Locale.getDefault()))
                );
    }


    public Author getAuthorByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(
                        () -> new EntityNotFoundException
                                (messageSource.getMessage("error_not_found", new Object[0], Locale.getDefault()))
                );
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author authorById = getAuthorById(id);
        copyUpdatedUser(updatedAuthor, authorById);
        return authorRepository.save(authorById);
    }

    public void deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new NoSuchResourceException(
                    messageSource.getMessage("error_not_found", new Object[0], Locale.getDefault()));
        }
    }

    private void copyUpdatedUser(Author source, Author destination) {
        destination.setBirthDate(source.getBirthDate());
        destination.setLastName(source.getLastName());
        destination.setFirstName(source.getFirstName());
        destination.setCountry(source.getCountry());
    }
}
