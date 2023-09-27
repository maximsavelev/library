package ru.savelyev.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.savelyev.library.model.Book;

import java.util.Optional;
@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
     Optional<Book> findBookByTitle(String title);
}
