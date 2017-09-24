package com.elkei.gol.gui

import com.elkei.gol.gui.actions.ExitAction
import com.elkei.gol.gui.actions.NextGenerationAction
import com.elkei.gol.model.Board
import com.elkei.gol.model.Coordinate
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame

class MainFrame : JFrame(GuiResources.default.getMainFrameTitle()) {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            MainFrame().isVisible = true
        }
    }

    val exitAction = ExitAction(this)
    private val board = Board(Coordinate(4, 5), listOf(Coordinate(0, 0), Coordinate(1, 0), Coordinate(0, 1),
            Coordinate(1, 1), Coordinate(2, 1), Coordinate(1, 3), Coordinate(2, 3)))
    val nextGenerationAction = NextGenerationAction(board)

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jMenuBar = MainFrameMenuBar(this)
        layout = BorderLayout()

        add(BoardPanel(board), BorderLayout.CENTER)

        size = Dimension(500, 500)
    }
}