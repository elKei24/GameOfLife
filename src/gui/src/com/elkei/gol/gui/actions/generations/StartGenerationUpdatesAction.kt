package com.elkei.gol.gui.actions.generations

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.gui.modelpanels.UpdatingBoardPanel
import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class StartGenerationUpdatesAction(private val boardPanel: UpdatingBoardPanel) :
        ResourceAction("generations.start", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0)) {
    init {
        boardPanel.addListener(object : UpdatingBoardListener {
            override fun updatingBoardRunningChanged(board: UpdatingBoard, updatingFrequently: Boolean) {
                updateEnabled()
            }
        })
        updateEnabled()
    }
    override fun actionPerformed(actionEvent: ActionEvent) {
        boardPanel.startGenerationUpdates()
    }
    private fun updateEnabled() {
        this.isEnabled = !boardPanel.board.running
    }
}