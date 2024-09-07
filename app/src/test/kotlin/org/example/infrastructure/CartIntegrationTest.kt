package org.example.infrastructure

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockserver.integration.ClientAndServer
import org.mockserver.junit.jupiter.MockServerExtension
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(MockServerExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartIntegrationTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var mockServerClient: ClientAndServer

    @BeforeEach
    fun setup() {
        mockServerClient
            .`when`(
                HttpRequest
                    .request()
                    .withMethod("POST")
                    .withPath("/remote-reservation-system/reserve"),
            ).respond(
                HttpResponse
                    .response()
                    .withStatusCode(200)
                    .withBody("Reservation successful"),
            )
    }

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
