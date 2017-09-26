package com.elkei.gol.gui.actions.file

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.gui.frames.main.MainFrame
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class SafeAction(private val mainFrame: MainFrame) :
        ResourceAction("file.save", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK)) {

    override fun actionPerformed(actionEvent: ActionEvent) {
        mainFrame.safeLoadDialog.saveBoard(mainFrame.boardHolder.currentBoard)
    }
}