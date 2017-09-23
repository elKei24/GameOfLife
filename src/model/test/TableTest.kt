import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class TableTest {
    private lateinit var table : Table<Coordinate>

    @BeforeEach
    fun initTable() {
        table = Table(Coordinate(2, 2), {it -> it})
    }

    @Test
    fun getCoordinateForExistingItem() {
        assertEquals(Coordinate(1, 1), table.getCoordinateForItem(Coordinate(1, 1)))
    }

    @Test
    fun getCoordinateForNonExistingItem() {
        assertThrows(NoSuchElementException::class.java, {table.getCoordinateForItem(Coordinate(2, 2))})
    }

    @Test
    fun getItemForCoordinateInRange() {
        assertEquals(Coordinate(1, 0), table.getItemForCoordinate(Coordinate(1, 0)))
    }

    @Test
    fun getItemForCoordinateOutOfRange() {
        assertEquals(Coordinate(1, 0), table.getItemForCoordinate(Coordinate(-3, 4)))
    }


}