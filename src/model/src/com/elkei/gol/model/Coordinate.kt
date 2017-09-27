/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model

import java.util.stream.Collectors

data class Coordinate(val x : Int, val y : Int) : Comparable<Coordinate> {
    fun area() = x * y

    operator fun plus(movement: Coordinate): Coordinate = Coordinate(x + movement.x, y + movement.y)
    operator fun minus(movement: Coordinate): Coordinate = Coordinate(x - movement.x, y - movement.y)
    override operator fun compareTo(other: Coordinate) : Int {
        return when {
            this.y < other.y -> -2
            this.y > other.y -> 2
            else -> when {
                this.x < other.x -> -1
                this.x > other.x -> 1
                else -> 0
            }
        }
    }
    operator fun rem(by: Coordinate): Coordinate {
        return Coordinate(nonNegativeRem(this.x, by.x), nonNegativeRem(this.y, by.y))
    }

    private fun nonNegativeRem(left: Int, right: Int): Int {
        return (left % right + right) % right
    }

    fun getCoordinatesInRectangleWith(other: Coordinate): List<Coordinate> {
        val (minX, minY) = maximalCoordinateOfRectangle(other)
        val (maxX, maxY) = maximalCoordinateOfRectangleWith(other)
        return (minX..maxX).map { x ->
            (minY..maxY).map { y -> Coordinate(x, y) }
        }.stream().flatMap { column -> column.stream() }.collect(Collectors.toList())!!
    }

    fun maximalCoordinateOfRectangleWith(other: Coordinate): Coordinate {
        val highestX = maxOf(this.x, other.x)
        val highestY = maxOf(this.y, other.y)
        return Coordinate(highestX, highestY)
    }
    fun maximalCoordinateOfRectangle(other: Coordinate): Coordinate {
        val lowestX = minOf(this.x, other.x)
        val lowestY = minOf(this.y, other.y)
        return Coordinate(lowestX, lowestY)
    }

    companion object {
        fun getCoordinatesInRectangleWith(c1: Coordinate, c2: Coordinate): List<Coordinate> {
            return c1.getCoordinatesInRectangleWith(c2)
        }
    }

}