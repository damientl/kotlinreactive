package org.example.infrastructure

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartIntegrationTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun canAddItemToCart() {
        webTestClient
            .post()
            .uri("/cart/1/item")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(String::class.java)
            .consumeWith { response ->
                assertEquals("Item added to cart", response.responseBody)
            }
    }
}
