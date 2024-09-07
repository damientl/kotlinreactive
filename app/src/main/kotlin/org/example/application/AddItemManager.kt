package org.example.application

import org.example.entity.Cart

class AddItemManager {
    fun addItem(): String = Cart().addItem()
}
