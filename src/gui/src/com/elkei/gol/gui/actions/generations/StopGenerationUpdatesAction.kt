package com.elkei.gol.gui.actions.generations

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.model.BoardHolder
import com.elkei.gol.model.BoardHolderListener
import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class StopGenerationUpdatesAction(private val boardHolder: BoardHolder) :
        ResourceAction("generations.stop", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0)) {
    init {
        val boardRunningListener = object : UpdatingBoardListener {
            override fun updatingBoardRunningChanged(board: UpdatingBoard, updatingFrequently: Boolean) {
                updateEnabled()
            }
        }
        boardHolder.currentBoard.addListener(boardRunningListener)

        boardHolder.addBoardHolderListener(object : BoardHolderListener {
            override fun heldInstanceChanged(holder: BoardHolder, oldBoard: UpdatingBoard, newBoard: UpdatingBoard) {
                oldBoard.removeListener(boardRunningListener)
                newBoard.addListener(boardRunningListener)
            }
        })

        updateEnabled()
    }
    override fun actionPerformed(actionEvent: ActionEvent) {
        boardHolder.currentBoard.stopGenerationUpdates()
    }
    private fun updateEnabled() {
        this.isEnabled = checkEnabled()
    }

    private fun checkEnabled() = boardHolder.currentBoard.running
}