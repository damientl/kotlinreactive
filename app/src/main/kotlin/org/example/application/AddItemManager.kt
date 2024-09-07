package org.example.application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.slf4j.MDCContext
import kotlinx.coroutines.withContext
import org.example.entity.Cart
import org.example.infrastructure.ReservationGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AddItemManager
    @Autowired
    constructor(
        private val reservationGateway: ReservationGateway,
    ) {
        private val logger: Logger = LoggerFactory.getLogger(AddItemManager::class.java)

        suspend fun addItem(): String =
            withContext(Dispatchers.IO + MDCContext()) {
                val reservation =
                    async {
                        reservationGateway.reserve()
                    }
                // here other http requests could be made in parallel

                if ("Reservation successful" == reservation.await()) {
                    Cart().addItem()
                    logger.info("Item added to cart successfully")
                    "Item added to cart"
                } else {
                    logger.warn("Failed to add item to cart")
                    "Item not added to cart"
                }
            }
    }
