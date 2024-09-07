package org.example.infrastructure

import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class ReservationGateway
    @Autowired
    constructor(
        private val webClient: WebClient,
    ) {
        suspend fun reserve(): String {
            delay(500)
            return webClient
                .post()
                .uri("/remote-reservation-system/reserve")
                .retrieve()
                .awaitBody()
        }
    }
