/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.actions.generations

import com.elkei.gol.model.BoardHolder
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class StartGenerationUpdatesAction(boardHolder: BoardHolder) :
        StartStopGenerationUpdatesAction(
                boardHolder,
                "generations.start",
                accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0)
        ) {

    override fun actionPerformed(actionEvent: ActionEvent) {
        boardHolder.currentBoard.startGenerationUpdates()
    }

    override fun checkEnabled() = !boardHolder.currentBoard.running
}