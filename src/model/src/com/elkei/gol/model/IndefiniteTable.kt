/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model

import java.util.*
import java.util.concurrent.ConcurrentHashMap

open class IndefiniteTable<T>(val size : Coordinate, init: (Coordinate) -> T) {
    private val cells = ConcurrentHashMap<Coordinate, T>(Math.abs(size.area()))

    init {
        allCoordinates().forEach { cells.put(it, init(it)) }
    }

    fun allCoordinates(): List<Coordinate> {
        val highestCoordinate = getMostFarCoordinate() ?: return LinkedList()
        return Coordinate(0, 0).getCoordinatesInRectangleWith(highestCoordinate)
    }

    fun getMostFarCoordinate(): Coordinate? {
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