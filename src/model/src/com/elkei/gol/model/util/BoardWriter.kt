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

class BoardWriter private constructor(private val board: Board, private val stream: OutputStream) {
    companion object {
        fun write(stream: OutputStream, board: Board) = BoardWriter(board, stream).write()
    }

    fun write() {
        stream.use { stream ->
            val stream = DataOutputStream(stream)
            stream.writeInt(board.size.x)
            stream.writeInt(board.size.y)
            board.allCoordinates()
                    .sorted()
                    .map(board::getItemForCoordinate)
                    .map { it.living }
                    .forEach { stream.writeBoolean(it) }
        }
    }
}