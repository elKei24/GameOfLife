/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model

import java.util.stream.Collectors

/**
 * This data class represents a pair of [Int] values and can be used for example to describe a position on a [Board]
 * or its size.
 *
 * @property x value of this coordinate in the first direction
 * @property y value of this coordinate in the second direction
 */
data class Coordinate(val x : Int, val y : Int) : Comparable<Coordinate> {
    companion object {
        /**
         * Calculates all coordinates with an x value between `c1.x` and `c2.x` and a y value between `c1.y`
         * and `c2.y`. This is done by calling [getCoordinatesInRectangleWith] of [c1]
         *
         * @param c1 the one corner of the rectangle
         * @param c2 the other corner of the rectangle
         * @return all coordinates in the rectangle between [c1] and [c2]
         */
        fun getCoordinatesInRectangleWith(c1: Coordinate, c2: Coordinate): List<Coordinate> {
            return c1.getCoordinatesInRectangleWith(c2)
        }
    }

    /**
     * Returns the product of [x] and [y]
     */
    fun area() = x * y

    /**
     * Returns a new coordinate with a x value that is the sum of the x values of this and [movement], and a
     * y value that also is the sum of the y values of this and [movement]
     *
     * @param movement the [Coordinate] that the components are summed up with the components of this
     * @return a new [Coordinate] with the summed components of this and [movement]
     */
    operator fun plus(movement: Coordinate): Coordinate = Coordinate(x + movement.x, y + movement.y)

    /**
     * Returns a new coordinate with a x value that is the x values of [movement] subtracted from the x value of this,
     * and a y value that is the y value of [movement] subtracted from the y value of this
     *
     * @param movement the [Coordinate] that is subtracted from this
     * @return a new [Coordinate] with the subtracted components of this and [movement]
     */
    operator fun minus(movement: Coordinate): Coordinate = Coordinate(x - movement.x, y - movement.y)

    /**
     * Compares this Coordinate to an other.
     *
     * @param other the Coordinate that this is compared to
     * @return 0 if this and [other] have the same values, -1 if they have the same y value but `this.x` is smaller than
     * `other.x`, 1 if it the other way round. -2 if `this.y < other.y`, 2 if it is the other way round.
     */
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

    /**
     * Returns a new Coordinate with x and y values that are the non-negative remainders of the corresponding value
     * of this divided by the corresponding value of [by].
     *
     * The non-negative remainder of `a` and `b` is the smallest non-negative integer so that `b`
     * divides `a` minus this value.
     *
     * @param by the divisor that the remainders are calculated for
     * @return a new Coordinate with x and y values that are the non-negative remainders of the corresponding value
     * of this divided by the corresponding value of [by].
     */
    operator fun rem(by: Coordinate): Coordinate {
        return Coordinate(nonNegativeRem(this.x, by.x), nonNegativeRem(this.y, by.y))
    }

    /**
     * Returns the non-negative remainder of [dividend] divided by [divisor]. The non-negative remainder of `a` and
     * `b` is the smallest non-negative integer so that `b` divides `a` minus this value.
     *
     * @param dividend the dividend that the remainder is calculated for
     * @param divisor the divisor that the remainder is calculated for
     * @return the non-negative remainder of [dividend] divided by [divisor]
     */
    private fun nonNegativeRem(dividend: Int, divisor: Int): Int {
        return (dividend % divisor + divisor) % divisor
    }

    /**
     * Calculates all coordinates with an x value between `this.x` and `[other].x` and a y value between `this.y` and
     * `[other].y`
     *
     * @param other the other corner of the rectangle
     * @return all coordinates in the rectangle between this and [other]
     */
    fun getCoordinatesInRectangleWith(other: Coordinate): List<Coordinate> {
        val (minX, minY) = minimalCoordinateOfRectangleWith(other)
        val (maxX, maxY) = maximalCoordinateOfRectangleWith(other)
        return (minX..maxX).map { x ->
            (minY..maxY).map { y -> Coordinate(x, y) }
        }.stream().flatMap { column -> column.stream() }.collect(Collectors.toList())!!
    }
    /**
     * Returns a new coordinate with the maximum of `this.x` and `other.x` as x value and the maximum of
     * `this.y` and `other.y` as y value.
     *
     * @param other the [Coordinate] to calculate the maximums with
     * @return a new coordinate with the maximum of `this.x` and `other.x` as x value and the maximum of
     * `this.y` and `other.y` as y value.
     * @see [minimalCoordinateOfRectangleWith]
     */
    fun maximalCoordinateOfRectangleWith(other: Coordinate): Coordinate {
        val highestX = maxOf(this.x, other.x)
        val highestY = maxOf(this.y, other.y)
        return Coordinate(highestX, highestY)
    }

    /**
     * Returns a new coordinate with the minimum of `this.x` and `other.x` as x value and the minimum of
     * `this.y` and `other.y` as y value.
     *
     * @param other the [Coordinate] to calculate the minimums with
     * @return a new coordinate with the minimum of `this.x` and `other.x` as x value and the minimum of
     * `this.y` and `other.y` as y value.
     * @see [maximalCoordinateOfRectangleWith]
     */
    fun minimalCoordinateOfRectangleWith(other: Coordinate): Coordinate {
        val lowestX = minOf(this.x, other.x)
        val lowestY = minOf(this.y, other.y)
        return Coordinate(lowestX, lowestY)
    }

}