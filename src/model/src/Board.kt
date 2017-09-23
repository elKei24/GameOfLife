/** A class simulating a board of Connways Game Of Live */
class Board(size : Coordinate, coordinatesOfLivingCells : List<Coordinate> = List(0, {Coordinate(0, 0)}))
    : Table<Cell>(size, {coordinate -> Cell(coordinatesOfLivingCells.contains(coordinate)) }) {

    fun iterate() {
        TODO("Well, do the iteration stuff")
    }
}

