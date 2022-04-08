package com.gm.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val bob = User("bob123", "bob", "bobson")
        entityManager.persist(bob)
        val article = Article("Bob bobson is called bob", "Bob bob bob", "bobby bobby bobson", bob)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val bob = User("bob123", "bob", "bobson")
        entityManager.persist(bob)
        entityManager.flush()
        val user = userRepository.findByLogin(bob.login)
        assertThat(user).isEqualTo(bob)
    }
}