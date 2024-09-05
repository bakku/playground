package dev.bakku.springsample

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/authors/{authorId}/articles")
class ArticlesController(private val authorRepository: AuthorRepository, private val articleRepository: ArticleRepository) {
    @GetMapping
    fun getArticles(@PathVariable authorId: Long): List<ArticleDto> {
        return authorRepository.findByIdOrNull(authorId)
            ?.articles
            ?.map(Article::toDto)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/{id}")
    fun getArticle(@PathVariable authorId: Long, @PathVariable id: Long): ArticleDto {
        return articleRepository.findByIdAndAuthorId(id, authorId)
            ?.toDto()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createArticle(@PathVariable authorId: Long, @RequestBody articleDto: ArticleDto) {
        authorRepository.findByIdOrNull(authorId)
            ?.apply { articles = articles + articleDto.toArticle() }
            ?.also { authorRepository.save(it) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateArticle(@PathVariable authorId: Long, @PathVariable id: Long, @RequestBody articleDto: ArticleDto) {
        articleRepository.findByIdAndAuthorId(id, authorId)
            ?.apply { updateWithDto(articleDto) }
            ?.also { articleRepository.save(it) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteArticle(@PathVariable authorId: Long, @PathVariable id: Long) {
        articleRepository.deleteByIdAndAuthorId(id, authorId)
    }
}