/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model

import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * An [IndefiniteTable] holds items of a certain type and links them with a two dimensional [Coordinate].
 * When accessing items of the table using coordinates greater than the size of the table, those coordinates are
 * brought into the area of the table using the remainder of those coordinates and the size of the table, so that it
 * seems like the table repeats itself indefinitely often.
 *
 * @property size the size of this table.
 * @param init the initializer for the items of this table. It is called for every coordinate in this table and the
 * returned item stored at that coordinate.
 */
open class IndefiniteTable<T>(val size : Coordinate, init: (Coordinate) -> T) {
    private val cells = ConcurrentHashMap<Coordinate, T>(Math.abs(size.area()))

    init {
        allCoordinates().forEach { cells.put(it, init(it)) }
    }

    /**
     * Returns all coordinates in this table. These are all coordinates with an x value in the range from 0 until the
     * x value of size and with an y value in the range from 0 until the y value of size.
     *
     * @return a list of all coordinates in this table
     */
    fun allCoordinates(): List<Coordinate> {
        val highestCoordinate = getMostFarCoordinate() ?: return LinkedList()
        return Coordinate(0, 0).getCoordinatesInRectangleWith(highestCoordinate)
    }

    /**
     * Returns the coordinate of this table that still is part of this table but most far away from the origin.
     * If this table does not contain any coordinates, null is returned.
     *
     * @return the coordinate that still is part of this table but most far away from the origin
     */
    fun getMostFarCoordinate(): Coordinate? {
        if (size.area() == 0) return null
        return Coordinate(decreaseAbs(size.x), decreaseAbs(size.y))
    }

    /**
     * Returns a value with the same sign as [value] but an absolute value that is by 1 smaller than that of [value],
     * or 0 if [value] is 0, too.
     *
     * @param value the value that the absolute value will be decreased by one of
     * @return a value with the same sign as [value] but an absolute value that is by 1 smaller than that of [value],
     * or 0 if [value] is 0, too.
     */
    private fun decreaseAbs(value: Int): Int {
        return when {
            value < 0 -> value + 1
            value > 0 -> value - 1
            else -> value
        }
    }

    /**
     * Returns the coordinate between the origin and [size] where [item] is located at.
     *
     * @param item the item to search the coordinate for
     * @return the coordinate between the origin and [size] where [item] is located at
     * @throws NoSuchElementException if [item] is not located at any of the coordinates of this table
     */
    fun getCoordinateForItem(item: T) : Coordinate {
        return cells.search(3, { key, value -> if (value == item) key else null }) ?: throw NoSuchElementException()
    }

    /**
     * Returns the item that is located at [coordinate]. If [coordinate] is more far away from origin than the
     * coordinate most far away of this table, [coordinate] is brought into the range of this table by using the
     * remainder of [coordinate] divided by [size]
     *
     * @param coordinate the coordinate that describes which item to return
     * @return the item located at [coordinate]
     */
    fun getItemForCoordinate(coordinate: Coordinate) : T {
        return cells[mapCoordinateIntoRange(coordinate)]!!
    }

    /**
     * Returns a new Coordinate in the range of this table that represents the same item as [coordinate]
     *
     * @param coordinate the coordinate that should be mapped into the range of this table
     * @return a new Coordinate in the range of this table that represents the same item as [coordinate]
     */
    private fun mapCoordinateIntoRange(coordinate: Coordinate): Coordinate {
        return coordinate % size
    }

}