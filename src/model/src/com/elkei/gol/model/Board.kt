/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model

/**
 * A class simulating a board of Conway's Game Of Live
 *
 * @param size Size of the new board
 * @param init a function describing which cells should be initialized as living
 * */
open class Board(size: Coordinate, init: (Coordinate) -> Boolean = {false})
    : IndefiniteTable<Cell>(size, { coordinate -> Cell(init(coordinate)) }) {

    /**
     * Updates every cell of this board to the next generation. To do so, the number of living neighbours is calculated
     * for every cell. If this is done, [Cell.updateToNextGeneration] is called for every cell.
     */
    @Synchronized
    open fun updateToNextGeneration() {
        val neighbours = IndefiniteTable(size, this::getNumberOfLivingNeighbours)
        allCoordinates()
                .parallelStream()
                .forEach { coordinate ->
                    getItemForCoordinate(coordinate).updateToNextGeneration(neighbours.getItemForCoordinate(coordinate)
            )
        }
    }

    /**
     * Calculates the number of living neighbours of the cell at [coordinate]. Neighbours are the eight cells that
     * have a common edge or corner with the cell in the middle.
     *
     * @param coordinate the coordinate of the cell that the neighbours should be counted of
     * @return the number of living neighbours of the cell at [coordinate]
     */
    fun getNumberOfLivingNeighbours(coordinate: Coordinate): Int =
            getNeighbourCoordinatesOf(coordinate)
                    .parallelStream()
                    .map(this::getItemForCoordinate)
                    .mapToInt { if (it.living) 1 else 0 }
                    .sum()

    /**
     * Returns the coordinates of the eight neighbours of [coordinate]. Neighbours are the eight cells that
     * have a common edge or corner with the cell in the middle.
     *
     * @param coordinate the coordinate to find the neighbours of
     * @return a list of the coordinates that have a common edge or corner with [coordinate]
     */
    private fun getNeighbourCoordinatesOf(coordinate: Coordinate): List<Coordinate> {
        val diagonalStep = Coordinate(1, 1)
        return Coordinate.getCoordinatesInRectangleWith(coordinate - diagonalStep, coordinate + diagonalStep)
                .filterNot { coordinate == it }
    }
}

