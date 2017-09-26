package com.elkei.gol.gui.actions.generations

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.model.BoardHolder
import com.elkei.gol.model.BoardHolderListener
import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import java.awt.event.ActionEvent
import javax.swing.KeyStroke

abstract class ChangeGenerationUpdateSpeedAction(
        protected val boardHolder: BoardHolder,
        key: String,
        accelerator: KeyStroke? = null
) : ResourceAction(key, accelerator = accelerator) {

    init {
        val speedChangedListener = object : UpdatingBoardListener {
            override fun updatingBoardSpeedChanged(board: UpdatingBoard, msBetweenUpdates: Long) {
                updateEnabled()
            }
        }
        boardHolder.currentBoard.addListener(speedChangedListener)
        boardHolder.addBoardHolderListener(object : BoardHolderListener {
            override fun heldInstanceChanged(holder: BoardHolder, oldBoard: UpdatingBoard, newBoard: UpdatingBoard) {
                oldBoard.removeListener(speedChangedListener)
                newBoard.addListener(speedChangedListener)
                updateEnabled()
            }
        })

        updateEnabled()
    }

    private fun updateEnabled() {
        this.isEnabled = checkEnabled()
    }

    protected abstract fun checkEnabled(): Boolean

    override fun actionPerformed(actionEvent: ActionEvent) {
        boardHolder.currentBoard.msBetweenGenerationUpdates = calculateNewDelay()
    }

    protected abstract fun calculateNewDelay(): Long
}