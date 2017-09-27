/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.modelpanels

import com.elkei.gol.model.BoardHolder
import com.elkei.gol.model.BoardHolderListener
import com.elkei.gol.model.UpdatingBoard
import java.awt.BorderLayout
import javax.swing.JPanel

class BoardHolderPanel(holder: BoardHolder) : JPanel(BorderLayout()) {
    private var boardPanel = UpdatingBoardPanel(holder.currentBoard)
    private set(value) {
        remove(field)
        field = value
        add(field)
    }

    init {
        add(boardPanel)

        holder.addBoardHolderListener(object : BoardHolderListener {
            override fun heldInstanceChanged(holder: BoardHolder, oldBoard: UpdatingBoard, newBoard: UpdatingBoard) {
                boardPanel = UpdatingBoardPanel(newBoard)
                revalidate()
            }
        })
    }
}