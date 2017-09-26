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