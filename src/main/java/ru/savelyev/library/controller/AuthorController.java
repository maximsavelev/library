package ru.savelyev.library.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.savelyev.library.dto.AuthorDTO;
import ru.savelyev.library.mapper.AuthorMapper;
import ru.savelyev.library.model.Author;
import ru.savelyev.library.service.AuthorService;



@RequestMapping("/api/authors")
@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(authorMapper.mapToDTO(authorService.getAuthorById(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<AuthorDTO> getAuthorById(@RequestParam("firstname") String firstName,
                                                   @RequestParam("lastname") String lastname) {
        return new ResponseEntity<>(
                authorMapper.mapToDTO(authorService.getAuthorByFirstNameAndLastName(firstName, lastname)),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        Author createdAuthor = authorService.createAuthor(authorMapper.mapToEntity(authorDTO));
        return new ResponseEntity<>(authorMapper.mapToDTO(createdAuthor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id,@Valid @RequestBody AuthorDTO updatedAuthorDTO) {
        Author author = authorService.updateAuthor(id, authorMapper.mapToEntity(updatedAuthorDTO));
        AuthorDTO authorDTO = authorMapper.mapToDTO(author);
        return new ResponseEntity<>(authorDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
