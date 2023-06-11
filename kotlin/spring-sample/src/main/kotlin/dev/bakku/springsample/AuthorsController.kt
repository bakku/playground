package dev.bakku.springsample

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/authors")
class AuthorsController(private val authorRepository: AuthorRepository) {
    @GetMapping
    fun getAuthors(): List<AuthorDto> {
        return authorRepository.findAll().map(Author::toDto)
    }

    @GetMapping("/{id}")
    fun getAuthor(@PathVariable id: Long): AuthorDto {
        return authorRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAuthor(@RequestBody authorDto: AuthorDto) {
        authorRepository.save(authorDto.toAuthor())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateAuthor(@PathVariable id: Long, @RequestBody authorDto: AuthorDto) {
        authorRepository.findByIdOrNull(id)
            ?.apply { updateWithDto(authorDto) }
            ?.also { authorRepository.save(it) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAuthor(@PathVariable id: Long) {
        authorRepository.deleteById(id)
    }
}