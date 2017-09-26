package com.elkei.gol.gui.actions.file

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.gui.frames.main.MainFrame
import com.elkei.gol.model.UpdatingBoard
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class LoadAction(private val mainFrame: MainFrame) :
        ResourceAction("file.open", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK)) {

    override fun actionPerformed(actionEvent: ActionEvent) {
        val loaded = mainFrame.safeLoadDialog.loadBoard() ?: return
        mainFrame.boardHolder.currentBoard = UpdatingBoard(loaded)
    }
}