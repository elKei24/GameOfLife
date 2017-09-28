/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model.util

import com.elkei.gol.model.Board
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.util.BoardReader.BoardFileFormatException
import java.io.DataInputStream
import java.io.EOFException
import java.io.InputStream

/**
 * Helper class to read a [Board] data from a stream.
 *
 * @param stream the stream to read from
 * @param maximumBoardSize If this value is not null and it is tried to read a board bigger than [maximumBoardSize],
 * an [BoardFileFormatException] will be thrown.
 * @see BoardWriter
 */
class BoardReader private constructor(
        private val stream: InputStream,
        private val maximumBoardSize: Coordinate? = null
) {
    companion object {
        /**
         * Tries to read a board from the stream.
         *
         * @param stream the stream to read from
         * @param maximumBoardSize If this value is not null and it is tried to read a board bigger than [maximumBoardSize],
         * an [BoardFileFormatException] will be thrown.
         * @return the board read from [stream]
         * @throws BoardFileFormatException thrown if the size of the board described by [stream] is invalid, or if the
         * stream does not contain enough data to describe a board with the read size
         * @see [BoardWriter.write]
         */
        fun read(stream: InputStream, maximumBoardSize: Coordinate? = null) = BoardReader(stream, maximumBoardSize).read()
    }

    /**
     * Tries to read a board from [stream]
     *
     * @return the board read from [stream]
     * @throws BoardFileFormatException thrown if the size of the board described by [stream] is invalid, or if the
     * stream does not contain enough data to describe a board with the read size
     */
    fun read(): Board {
        val stream = DataInputStream(stream)

        val sizeX: Int
        val sizeY: Int
        val livings: Array<Boolean>
        try {
            sizeX = Math.abs(stream.readInt())
            sizeY = Math.abs(stream.readInt())
            val size = Coordinate(sizeX, sizeY)
            if (!checkValidSize(size)) throw BoardFileFormatException("Stream contains empty or too big board")
            livings = Array(sizeX * sizeY, {false})

            (0 until sizeY).forEach { y ->
                (0 until sizeX).forEach { x ->
                    livings[y * sizeX + x] = stream.readBoolean()
                }
            }

            return Board(Coordinate(sizeX, sizeY), {livings[it.y * sizeX + it.x]})
        } catch (e: EOFException) {
            throw BoardFileFormatException("Stream contains less data than expected")
        } finally {
            stream.close()
        }
    }

    /**
     * Function that checks if the given board size is valid. A board size is valid if the board has at least one
     * row and one column and is not bigger than [maximumBoardSize].
     *
     * @param size the size that will be checked
     * @return True if the board has at least one cell, but is not bigger than [maximumBoardSize]. False otherwise.
     */
    private fun checkValidSize(size: Coordinate): Boolean {
        return size.area() > 0 && (maximumBoardSize == null ||
                (Math.abs(size.x) <= Math.abs(maximumBoardSize.x) && Math.abs(size.y) <= Math.abs(maximumBoardSize.y)))
    }

    /**
     * [BoardFileFormatException] is used by [BoardReader] to indicate that it could read a board from an input stream.
     *
     * @param msg text that describes why this exception was thrown
     */
    class BoardFileFormatException(msg: String) : Exception(msg)
}
