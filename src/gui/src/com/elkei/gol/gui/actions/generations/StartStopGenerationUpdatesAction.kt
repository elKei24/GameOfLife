package com.elkei.gol.gui.actions.generations

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.model.BoardHolder
import com.elkei.gol.model.BoardHolderListener
import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import javax.swing.KeyStroke

abstract class StartStopGenerationUpdatesAction(
        protected val boardHolder: BoardHolder,
        key: String,
        accelerator: KeyStroke? = null
) : ResourceAction(key, accelerator = accelerator) {
    init {
        //disable action if updates are already started/stopped
        val boardRunningListener = object : UpdatingBoardListener {
            override fun updatingBoardRunningChanged(board: UpdatingBoard, updatingFrequently: Boolean) {
                updateEnabled()
            }
        }
        boardHolder.currentBoard.addListener(boardRunningListener)

        //always observe the board that the user sees (changes e.g. on resizing)
        boardHolder.addBoardHolderListener(object : BoardHolderListener {
            override fun heldInstanceChanged(holder: BoardHolder, oldBoard: UpdatingBoard, newBoard: UpdatingBoard) {
                oldBoard.removeListener(boardRunningListener)
                newBoard.addListener(boardRunningListener)
                updateEnabled()
            }
        })

        updateEnabled()
    }

    protected fun updateEnabled() {
        this.isEnabled = checkEnabled()
    }

    protected abstract fun checkEnabled(): Boolean
}