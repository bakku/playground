package dev.bakku.springsample

data class ArticleDto(val id: Long?, val title: String) {
    fun toArticle(): Article {
        return Article(null, title, null)
    }
}

fun Article.toDto(): ArticleDto {
    return ArticleDto(id, title)
}

fun Article.updateWithDto(dto: ArticleDto) {
    title = dto.title
}

data class AuthorDto(val id: Long?, val name: String) {
    fun toAuthor(): Author {
        return Author(null, name, emptyList())
    }
}

fun Author.toDto(): AuthorDto {
    return AuthorDto(id, name)
}

fun Author.updateWithDto(dto: AuthorDto) {
    name = dto.name
}