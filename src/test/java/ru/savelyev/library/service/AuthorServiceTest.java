package ru.savelyev.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.savelyev.library.exception.EntityNotFoundException;
import ru.savelyev.library.exception.NoSuchResourceException;
import ru.savelyev.library.model.Author;
import ru.savelyev.library.repository.AuthorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    private final Long id = 100_000L;

    @Test
    public void getAuthorById_returnSuccessful() {
        Author mock = Mockito.mock(Author.class);
        Optional<Author> author = Optional.of(mock);
        Mockito.when(authorRepository.findById(id)).thenReturn(author);
        Author authorById = authorService.getAuthorById(id);
        Assertions.assertEquals(authorById, mock);
        Mockito.verify(authorRepository, Mockito.times(1)).findById(id);
        Mockito.verifyNoMoreInteractions(authorRepository);
    }


    @Test
    public void getAuthorById_throwException() {
        ;
        Mockito.when(authorRepository.findById(id)).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class,
                () -> this.authorService.getAuthorById(id)
        );
        Mockito.verify(authorRepository, Mockito.times(1)).findById(id);
        Mockito.verifyNoMoreInteractions(authorRepository);
    }


    @Test
    public void getAuthorByFirstNameAndLastName() {
        var firstName = "Don";
        var lastName = "Juan";
        ;
        var author = Mockito.mock(Author.class);
        Optional<Author> optional = Optional.of(author);
        Mockito.when(authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName))
                .thenReturn(optional);
        var response = this.authorService.getAuthorByFirstNameAndLastName(firstName, lastName);
        Assertions.assertEquals(response, author);
        Mockito.verify(authorRepository, Mockito.times(1))
                .findAuthorByFirstNameAndLastName(firstName, lastName);
        Mockito.verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void updateAuthor_successful() {
        var author = Mockito.mock(Author.class);
        Optional<Author> optional = Optional.of(author);
        Author updatedUser = Mockito.mock(Author.class);
        Mockito.when(authorRepository.findById(id))
                .thenReturn(optional);
        Author savedUser = authorService.updateAuthor(id, updatedUser);
        Mockito.verify(authorRepository, Mockito.times(1)).findById(id);
        Mockito.verify(authorRepository, Mockito.times(1)).save(author);
        Mockito.verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void createAuthor_successful() {
        var author = Mockito.mock(Author.class);
        Author savedAuthor = authorService.createAuthor(author);
        Mockito.verify(authorRepository, Mockito.times(1)).save(author);
        Mockito.verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void deleteAuthorById() {
        Mockito.when(authorRepository.existsById(id)).thenReturn(true);
        this.authorService.deleteAuthorById(id);
        Mockito.verify(authorRepository, Mockito.times(1)).existsById(id);
        Mockito.verify(authorRepository, Mockito.times(1)).deleteById(id);
        Mockito.verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void deleteAuthorById_NoResource() {
        Mockito.when(authorRepository.existsById(id)).thenReturn(false);
        assertThrows(NoSuchResourceException.class,
                () -> this.authorService.deleteAuthorById(id)
        );
        Mockito.verify(authorRepository, Mockito.times(1)).existsById(id);
    }


}
