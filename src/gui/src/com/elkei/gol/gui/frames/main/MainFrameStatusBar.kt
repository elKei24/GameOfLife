/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

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
            //udpate the counter when there is a new generation
            val boardGenerationListener = object : UpdatingBoardListener {
                override fun updatingBoardGenerationChanged(board: UpdatingBoard, generation: Int) {
                    updateText()
                }
            }
            boardHolder.currentBoard.addListener(boardGenerationListener)

            //observe the new board if user loads a board, resizes it or whatever
            boardHolder.addBoardHolderListener(object : BoardHolderListener {
                override fun heldInstanceChanged(holder: BoardHolder, oldBoard: UpdatingBoard, newBoard: UpdatingBoard) {
                    oldBoard.removeListener(boardGenerationListener)
                    newBoard.addListener(boardGenerationListener)
                    updateText()
                }
            })

            //show initial text
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