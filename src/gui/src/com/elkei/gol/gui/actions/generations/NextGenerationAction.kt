package com.elkei.gol.gui.actions.generations

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.gui.modelpanels.BoardPanel
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class NextGenerationAction(private val boardPanel: BoardPanel) :
        ResourceAction("generations.step", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0)) {
    override fun actionPerformed(actionEvent: ActionEvent) {
        boardPanel.updateToNextGeneration()
    }
}