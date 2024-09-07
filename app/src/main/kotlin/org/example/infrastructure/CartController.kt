package org.example.infrastructure

import org.example.application.AddItemManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CartController
    @Autowired
    constructor(
        private val addItemManager: AddItemManager,
    ) {
        @PostMapping("/cart/1/item")
        suspend fun addItemToCart(): String = addItemManager.addItem()
    }
