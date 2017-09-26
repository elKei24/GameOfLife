package com.elkei.gol.model

/** A class simulating a board of Game Of Live */
open class Board(size: Coordinate, init: (Coordinate) -> Boolean = {false})
    : Table<Cell>(size, {coordinate -> Cell(init(coordinate)) }) {

    @Synchronized
    open fun updateToNextGeneration() {
        val neighbours = Table(size, this::getNumberOfLivingNeighbours)
        allCoordinates()
                .parallelStream()
                .forEach { coordinate ->
                    getItemForCoordinate(coordinate).updateToNextGeneration(neighbours.getItemForCoordinate(coordinate)
            )
        }
    }

    fun getNumberOfLivingNeighbours(coordinate: Coordinate): Int =
            getNeighbourCoordinatesOf(coordinate)
                    .parallelStream()
                    .map(this::getItemForCoordinate)
                    .mapToInt { if (it.living) 1 else 0 }
                    .sum()

    private fun getNeighbourCoordinatesOf(coordinate: Coordinate): List<Coordinate> {
        val diagonalStep = Coordinate(1, 1)
        return Coordinate.getCoordinatesInRectangleWith(coordinate - diagonalStep, coordinate + diagonalStep)
                .filterNot { coordinate == it }
    }
}

