package org.example.application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.example.entity.Cart
import org.example.infrastructure.ReservationGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AddItemManager
    @Autowired
    constructor(
        private val reservationGateway: ReservationGateway,
    ) {
        suspend fun addItem(): String =
            withContext(Dispatchers.IO) {
                val reservation =
                    async {
                        reservationGateway.reserve()
                    }
                // here other http requests could be made in parallel

                if ("Reservation successful" == reservation.await()) {
                    Cart().addItem()
                    "Item added to cart"
                } else {
                    "Item not added to cart"
                }
            }
    }
