import java.util.*
import java.util.concurrent.ConcurrentHashMap

open class Table<T>(val size : Coordinate, init: (Coordinate) -> T) {
    private val cells = ConcurrentHashMap<Coordinate, T>(Math.abs(size.area()))

    init {
        allCoordinates().forEach { cells.put(it, init(it)) }
    }

    fun allCoordinates(): List<Coordinate> {
        val highestCoordinate = getFarestCoordinate() ?: return LinkedList()
        return Coordinate(0, 0).getCoordinatesInRectangleWith(highestCoordinate)
    }

    fun getFarestCoordinate(): Coordinate? {
        if (size.area() == 0) return null
        return Coordinate(decreaseAbs(size.x), decreaseAbs(size.y))
    }

    private fun decreaseAbs(value: Int): Int {
        return when {
            value < 0 -> value + 1
            value > 0 -> value - 1
            else -> value
        }
    }

    fun getCoordinateForItem(cell: T) : Coordinate {
        return cells.search(3, { key, value -> if (value == cell) key else null }) ?: throw NoSuchElementException()
    }

    fun getItemForCoordinate(coordinate: Coordinate) : T {
        return cells[mapCoordinateIntoRange(coordinate)]!!
    }

    private fun mapCoordinateIntoRange(coordinate: Coordinate): Coordinate {
        return coordinate % size
    }

}