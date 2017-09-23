import java.util.concurrent.ConcurrentHashMap

open class Table<T>(val size : Coordinate, init: (Coordinate) -> T) {
    private val cells = ConcurrentHashMap<Coordinate, T>(size.area())

    init {
        (0 until size.x).forEach { x ->
            (0 until size.y)
                    .map { y -> Coordinate(x, y) }
                    .forEach { cells.put(it, init(it)) }
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