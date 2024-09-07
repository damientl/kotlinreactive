package org.example.infrastructure

import kotlinx.coroutines.delay
import org.example.application.AddItemManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CartController {
    @PostMapping("/cart/1/item")
    suspend fun addItemToCart(): String {
        delay(500)
        return AddItemManager().addItem()
    }
}
