package ru.savelyev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.savelyev.library.exception.EntityNotFoundException;
import ru.savelyev.library.exception.NoSuchResourceException;
import ru.savelyev.library.model.Author;
import ru.savelyev.library.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    public Author getAuthorByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author authorById = getAuthorById(id);
        copyUpdatedUser(updatedAuthor, authorById);
        return authorRepository.save(authorById);
    }

    public void deleteAuthorById(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new NoSuchResourceException();
        }
    }

    private void copyUpdatedUser(Author source, Author destination) {
        destination.setBirthDate(source.getBirthDate());
        destination.setLastName(source.getLastName());
        destination.setFirstName(source.getFirstName());
        destination.setCountry(source.getCountry());
    }
}
