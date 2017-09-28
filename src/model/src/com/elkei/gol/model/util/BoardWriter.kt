/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model.util

import com.elkei.gol.model.Board
import java.io.DataOutputStream
import java.io.OutputStream

/**
 * Helper class to write board data into a stream.
 *
 * @param stream the stream to write into
 * @param board the board that will be written into stream
 * @see BoardReader
 */
class BoardWriter private constructor(
        private val board: Board,
        private val stream: OutputStream
) {
    companion object {
        /**
         * Writes the data of [board] into [stream]. At first, the size of the board is stored using eight bytes,
         * then the cell states of the first row, then of the second, and so on.
         * The stream is closed before this function returns.
         *
         * @param stream the stream to write into
         * @param board the board that will be written into stream
         * @see [BoardReader.read]
         */
        fun write(stream: OutputStream, board: Board) = BoardWriter(board, stream).write()
    }

    /**
     * Writes the data of [board] into [stream]. At first, the size of the board is stored using eight bytes,
     * then the cell states of the first row, then of the second, and so on.
     * The stream is closed before this function returns.
     */
    fun write() {
        stream.use { stream ->
            val dataStream = DataOutputStream(stream)

            //size
            dataStream.writeInt(board.size.x)
            dataStream.writeInt(board.size.y)

            //cells
            board.allCoordinates()
                    .sorted()
                    .map(board::getItemForCoordinate)
                    .map { it.living }
                    .forEach { dataStream.writeBoolean(it) }
        }
    }
}