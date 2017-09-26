package com.elkei.gol.model

/** A class simulating a board of Game Of Live */
open class Board(size: Coordinate, init: (Coordinate) -> Boolean = {false})
    : Table<Cell>(size, {coordinate -> Cell(init(coordinate)) }) {

    @Synchronized
    open fun updateToNextGeneration() {
        val neighbours = Table(size, this::getNumberOfLivingNeighbours)
        allCoordinates().forEach { coordinate ->
            getItemForCoordinate(coordinate).updateToNextGeneration(neighbours.getItemForCoordinate(coordinate))
        }
    }

    fun getNumberOfLivingNeighbours(coordinate: Coordinate): Int =
            getNeighbourCoordinatesOf(coordinate).map(this::getItemForCoordinate).sumBy { if (it.living) 1 else 0 }

    private fun getNeighbourCoordinatesOf(coordinate: Coordinate): List<Coordinate> {
        val diagonalStep = Coordinate(1, 1)
        return Coordinate.getCoordinatesInRectangleWith(coordinate - diagonalStep, coordinate + diagonalStep)
                .filterNot { coordinate == it }
    }
}

