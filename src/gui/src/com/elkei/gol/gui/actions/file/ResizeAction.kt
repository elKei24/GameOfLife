/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.actions.file

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.gui.frames.main.MainFrame
import com.elkei.gol.gui.frames.newboard.ResizeBoardDialog
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class ResizeAction(private val mainFrame: MainFrame) :
        ResourceAction("file.resize", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK)) {

    override fun actionPerformed(actionEvent: ActionEvent) {
        val dialog = ResizeBoardDialog(mainFrame, mainFrame.boardHolder.currentBoard)
        dialog.isVisible = true

        val result = dialog.generatedBoard
        if (result != null) {
            mainFrame.boardHolder.currentBoard.stopGenerationUpdates()
            mainFrame.boardHolder.currentBoard = result
        }
    }
}