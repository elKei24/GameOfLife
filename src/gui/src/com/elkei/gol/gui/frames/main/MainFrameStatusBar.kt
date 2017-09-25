package com.elkei.gol.gui.frames.main

import com.elkei.gol.gui.res.GuiResources
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

        add(GenerationCounterLabel(mainFrame.boardPanel.board))
    }

    private class GenerationCounterLabel(val updatingBoard: UpdatingBoard) : JLabel() {
        init {
            updatingBoard.addListener(object : UpdatingBoardListener {
                override fun updatingBoardGenerationChanged(board: UpdatingBoard, generation: Int) {
                    updateText()
                }
            })

            updateText()
        }

        private fun updateText() {
            val formatter = GuiResources.default.getStringOrKey(GuiResources.GENERATION_LABEL)
            text = try {
                String.format(formatter, updatingBoard.generationCounter)
            } catch (ife: IllegalFormatException) {
                formatter //seems like there is no placeholder for generation
            }
        }
    }

}