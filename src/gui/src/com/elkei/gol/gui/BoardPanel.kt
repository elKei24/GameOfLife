package com.elkei.gol.gui

import com.elkei.gol.model.Board
import com.elkei.gol.model.Coordinate
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JPanel

class BoardPanel(val board: Board) : JPanel(GridBagLayout(), true) {
    init {
        board.allCoordinates().forEach { coordinate ->
            addCellPanelAt(coordinate)
        }
    }

    private fun addCellPanelAt(coordinate: Coordinate) {
        val c = GridBagConstraints()
        c.fill = GridBagConstraints.BOTH
        c.gridx = coordinate.x
        c.gridy = coordinate.y
        c.weightx = 1.0
        c.weighty = 1.0
        add(getNewCellPanelForCoordinate(coordinate), c)
    }

    private fun getNewCellPanelForCoordinate(coordinate: Coordinate) = CellPanel(board.getItemForCoordinate(coordinate))
}