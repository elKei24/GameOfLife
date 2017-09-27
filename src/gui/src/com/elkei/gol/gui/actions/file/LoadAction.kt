/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

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