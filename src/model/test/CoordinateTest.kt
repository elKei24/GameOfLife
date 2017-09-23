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
        assertEquals(Coordinate(1, -1), c1 +c2)
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
}