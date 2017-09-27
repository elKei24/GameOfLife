/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model.util

import com.elkei.gol.model.Board
import com.elkei.gol.model.Coordinate
import java.io.DataInputStream
import java.io.EOFException
import java.io.InputStream

class BoardReader private constructor(private val stream: InputStream) {
    companion object {
        const val MAXIMUM_BOARD_SIZE = 250*250

        fun read(stream: InputStream) = BoardReader(stream).read()
    }

    fun read(): Board {
        val stream = DataInputStream(stream)

        val sizeX: Int
        val sizeY: Int
        val livings: Array<Boolean>
        try {
            sizeX = Math.abs(stream.readInt())
            sizeY = Math.abs(stream.readInt())
            val size = Coordinate(sizeX, sizeY)
            if (!checkValidSize(size)) throw FormatException("Stream contains empty or too big board")
            livings = Array(sizeX * sizeY, {false})

            (0 until sizeY).forEach { y ->
                (0 until sizeX).forEach { x ->
                    livings[y * sizeX + x] = stream.readBoolean()
                }
            }

            return Board(Coordinate(sizeX, sizeY), {livings[it.y * sizeX + it.x]})
        } catch (e: EOFException) {
            throw FormatException("Stream contains less data than expected")
        } finally {
            stream.close()
        }
    }

    private fun checkValidSize(size: Coordinate): Boolean {
        return size.area() in 1..MAXIMUM_BOARD_SIZE
    }

    class FormatException(msg: String) : Exception(msg)
}
