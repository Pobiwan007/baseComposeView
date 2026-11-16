package com.basecomposeview.list

import org.junit.Assert.assertEquals
import org.junit.Test

class NavigationActionTest {

    @Test
    fun `next moves forward and wraps to the first item`() {
        assertEquals(1, NavigationAction.Next.nextIndex(current = 0, size = 3))
        assertEquals(0, NavigationAction.Next.nextIndex(current = 2, size = 3))
    }

    @Test
    fun `previous moves back and wraps to the last item`() {
        assertEquals(1, NavigationAction.Previous.nextIndex(current = 2, size = 3))
        assertEquals(2, NavigationAction.Previous.nextIndex(current = 0, size = 3))
    }

    @Test
    fun `single and empty lists stay at zero`() {
        assertEquals(0, NavigationAction.Next.nextIndex(current = 0, size = 1))
        assertEquals(0, NavigationAction.Previous.nextIndex(current = 0, size = 1))
        assertEquals(0, NavigationAction.Next.nextIndex(current = 0, size = 0))
    }
}
