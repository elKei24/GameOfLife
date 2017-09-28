/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

import com.elkei.gol.model.Coordinate
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CoordinateTest {
    @Test
    fun testArea() {
        val c = Coordinate(2, 4)
        assertEquals(8, c.area())
    }

    @Test
    fun testPlus() {
        val c1 = Coordinate(-3, 1)
        val c2 = Coordinate(4, -2)
        assertEquals(Coordinate(1, -1), c1 + c2)
    }

    @Test
    fun testMinus() {
        val c1 = Coordinate(-3, 1)
        val c2 = Coordinate(4, -2)
        assertEquals(Coordinate(-7, 3), c1 - c2)
    }

    @Test
    fun testCompareTo() {
        val origin = Coordinate(0, 0)
        assert(origin > Coordinate(-1, -2))
        assert(origin > Coordinate(0, -4))
        assert(origin > Coordinate(7, -2))
        assert(origin > Coordinate(-3, 0))
        assert(origin.compareTo(Coordinate(0, 0)) == 0)
        assert(origin < Coordinate(3, 0))
        assert(origin < Coordinate(-4, 8))
        assert(origin < Coordinate(0, 3))
        assert(origin < Coordinate(3, 4))
    }

    @Test
    fun testRemNormal() {
        val left = Coordinate(10, 4)
        val right = Coordinate(4, 2)
        assertEquals(Coordinate(2, 0), left % right)
    }

    @Test
    fun testRemWithNegativeLeft() {
        val left = Coordinate(-10, -4)
        val right = Coordinate(4, 2)
        assertEquals(Coordinate(2, 0), left % right)
    }

    @Test
    fun testRemWithRightNegative() {
        val left = Coordinate(10, 4)
        val right = Coordinate(-4, -2)
        assertEquals(Coordinate(-2, 0), left % right)
    }

    @Test
    internal fun testMaximalCoordinateOfRectangle() {
        val c1 = Coordinate(5, -3)
        val c2 = Coordinate(2, 2)
        assertEquals(Coordinate(5, 2), c1.maximalCoordinateOfRectangleWith(c2))
    }

    @Test
    internal fun testMinimalCoordinateOfRectangle() {
        val c1 = Coordinate(5, -3)
        val c2 = Coordinate(2, 2)
        assertEquals(Coordinate(2, -3), c1.minimalCoordinateOfRectangleWith(c2))
    }

    @Test
    internal fun testGetCoordinatesInRectangleWith() {
        val c1 = Coordinate(1, -3)
        val c2 = Coordinate(2, 2)
        val rectangle = c1.getCoordinatesInRectangleWith(c2)
        assert(rectangle.contains(c1))
        assert(rectangle.contains(c2))
        assert(rectangle.contains(Coordinate(2, 0)))
        assert(!rectangle.contains(Coordinate(1, -4)))
    }
}