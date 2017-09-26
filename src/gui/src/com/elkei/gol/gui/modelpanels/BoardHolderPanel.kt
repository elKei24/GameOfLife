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
            }
        })
    }
}