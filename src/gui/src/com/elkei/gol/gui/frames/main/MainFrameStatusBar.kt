package com.elkei.gol.gui.frames.main

import com.elkei.gol.gui.res.GuiResources
import com.elkei.gol.model.BoardHolder
import com.elkei.gol.model.BoardHolderListener
import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import java.util.*
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel

internal class MainFrameStatusBar(mainFrame: MainFrame) : JPanel() {
    init {
        layout = BoxLayout(this, BoxLayout.LINE_AXIS)
        border = BorderFactory.createLoweredSoftBevelBorder()

        add(GenerationCounterLabel(mainFrame.boardHolder))
    }

    private class GenerationCounterLabel(val boardHolder: BoardHolder) : JLabel() {
        init {
            val boardGenerationListener = object : UpdatingBoardListener {
                override fun updatingBoardGenerationChanged(board: UpdatingBoard, generation: Int) {
                    updateText()
                }
            }
            boardHolder.currentBoard.addListener(boardGenerationListener)

            boardHolder.addBoardHolderListener(object : BoardHolderListener {
                override fun heldInstanceChanged(holder: BoardHolder, oldBoard: UpdatingBoard, newBoard: UpdatingBoard) {
                    oldBoard.removeListener(boardGenerationListener)
                    newBoard.addListener(boardGenerationListener)
                    updateText()
                }
            })

            updateText()
        }

        private fun updateText() {
            val formatter = GuiResources.default.getStringOrKey(GuiResources.GENERATION_LABEL)
            text = try {
                String.format(formatter, boardHolder.currentBoard.generationCounter)
            } catch (ife: IllegalFormatException) {
                formatter //seems like there is no placeholder for generation
            }
        }
    }

}