import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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

    @Test
    fun getHighestCoordinateNormal() {
        assertEquals(Coordinate(1, 1), table.getFarestCoordinate())
    }

    @Test
    fun getHighestCoordinateNegative() {
        val negativeTable = Table(Coordinate(-3, -1), {it -> it})
        assertEquals(Coordinate(-2, 0), negativeTable.getFarestCoordinate())
    }

    @Test
    fun getHighestCoordinateEmpty() {
        val emptyTable = Table(Coordinate(0, 3), {it -> it})
        assertEquals(null, emptyTable.getFarestCoordinate())
    }


}