package com.elkei.gol.gui.actions

import com.elkei.gol.gui.modelpanels.BoardPanel
import com.elkei.gol.gui.modelpanels.UpdatingBoardPanel
import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class GenerationsMenuAction : ResourceAction("generations") {
    override fun actionPerformed(p0: ActionEvent?) {
        //nothing to do, just open the submenu
    }
}


class NextGenerationAction(private val boardPanel: BoardPanel) :
        ResourceAction("generations.step", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0)) {
    override fun actionPerformed(p0: ActionEvent?) {
        boardPanel.updateToNextGeneration()
    }
}

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
    override fun actionPerformed(p0: ActionEvent?) {
        boardPanel.startGenerationUpdates()
    }
    private fun updateEnabled() {
        this.isEnabled = !boardPanel.updatingBoard.running
    }
}

class StopGenerationUpdatesAction(private val boardPanel: UpdatingBoardPanel) :
        ResourceAction("generations.stop", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0)) {
    init {
        boardPanel.addListener(object : UpdatingBoardListener {
            override fun updatingBoardRunningChanged(board: UpdatingBoard, updatingFrequently: Boolean) {
                updateEnabled()
            }
        })
        updateEnabled()
    }
    override fun actionPerformed(p0: ActionEvent?) {
        boardPanel.stopGenerationUpdates()
    }
    private fun updateEnabled() {
        this.isEnabled = boardPanel.updatingBoard.running
    }
}