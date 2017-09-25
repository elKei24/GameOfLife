package com.elkei.gol.gui.actions.file

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.gui.frames.main.MainFrame
import com.elkei.gol.gui.frames.newboard.NewBoardDialog
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class NewBoardAction(private val mainFrame: MainFrame) :
        ResourceAction("file.new", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK)) {
    override fun actionPerformed(p0: ActionEvent?) {
        NewBoardDialog(mainFrame).isVisible = true
    }
}