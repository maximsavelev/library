package ru.savelyev.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.savelyev.library.model.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre,Long> {
    Optional<Genre> findByGenreName(String name);
}
