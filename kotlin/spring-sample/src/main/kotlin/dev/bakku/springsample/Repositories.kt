package dev.bakku.springsample

import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface AuthorRepository : CrudRepository<Author, Long>

interface ArticleRepository : CrudRepository<Article, Long> {
    fun findByIdAndAuthorId(id: Long, authorId: Long): Article?

    @Transactional
    fun deleteByIdAndAuthorId(id: Long, authorId: Long)
}