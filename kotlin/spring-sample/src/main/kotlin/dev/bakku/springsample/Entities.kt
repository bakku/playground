package dev.bakku.springsample

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Article(
    @Id @GeneratedValue var id: Long?,
    @Column(nullable = false) var title: String,
    @ManyToOne @JoinColumn(name = "author_id") var author: Author?,
    @Column(nullable = false) var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = false) var updatedAt: LocalDateTime = LocalDateTime.now()
)

@Entity
class Author(
    @Id @GeneratedValue var id: Long?,
    @Column(nullable = false) var name: String,
    @OneToMany(cascade = [CascadeType.ALL]) @JoinColumn(name = "author_id") var articles: List<Article>,
    @Column(nullable = false) var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = false) var updatedAt: LocalDateTime = LocalDateTime.now()
)