package com.elkei.gol.gui.actions.generations

import com.elkei.gol.gui.SIMULATION_DELAY_FASTER_FACTOR
import com.elkei.gol.gui.SIMULATION_DELAY_MINIMUM
import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.gui.modelpanels.UpdatingBoardPanel
import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class FasterGenerationUpdatesAction(private val boardPanel: UpdatingBoardPanel) :
        ResourceAction("generations.speed.faster", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0)) {

    init {
        boardPanel.addListener(object : UpdatingBoardListener {
            override fun updatingBoardSpeedChanged(board: UpdatingBoard, msBetweenUpdates: Long) {
                updateEnabled()
            }
        })

        updateEnabled()
    }

    private fun updateEnabled() {
        this.isEnabled = calculateNewDelay() >= SIMULATION_DELAY_MINIMUM
    }

    override fun actionPerformed(actionEvent: ActionEvent) {
        boardPanel.updatingBoard.msBetweenUpdates = calculateNewDelay()
    }

    private fun calculateNewDelay(): Long {
        return (boardPanel.updatingBoard.msBetweenUpdates / SIMULATION_DELAY_FASTER_FACTOR).toLong()
    }
}