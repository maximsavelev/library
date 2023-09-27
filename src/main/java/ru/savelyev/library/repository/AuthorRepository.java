package ru.savelyev.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.savelyev.library.model.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName,String lastName);

}
