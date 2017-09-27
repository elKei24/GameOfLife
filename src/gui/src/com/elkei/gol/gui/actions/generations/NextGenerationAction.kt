/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.actions.generations

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.model.BoardHolder
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class NextGenerationAction(private val boardHolder: BoardHolder) :
        ResourceAction("generations.step", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0)) {
    override fun actionPerformed(actionEvent: ActionEvent) {
        boardHolder.currentBoard.updateToNextGeneration()
    }
}