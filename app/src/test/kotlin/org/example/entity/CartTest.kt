
package org.example.entity

import kotlin.test.Test
import kotlin.test.assertNotNull

class CartTest {
    @Test fun canAddItem() {
        val classUnderTest = Cart()
        assertNotNull(classUnderTest.addItem(), "Item added to cart")
    }
}
