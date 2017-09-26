package com.elkei.gol.gui.modelpanels

import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import com.elkei.gol.model.util.Observable
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JPanel

class UpdatingBoardPanel(var board: UpdatingBoard) :
        JPanel(GridBagLayout(), true), Observable<UpdatingBoardListener> by board {

    init {
        val cellPanelListener = object : CellManipulationListener {
            override fun cellPanelManipulated(cellPane: CellPanel) {
                board.resetGenerationCounter()
            }
        }
        board.allCoordinates().forEach { coordinate ->
            val newCellPanel = addNewCellPanelAt(coordinate)
            newCellPanel.addListener(cellPanelListener)
        }
    }

    private fun addNewCellPanelAt(coordinate: Coordinate): CellPanel {
        val c = GridBagConstraints()
        c.fill = GridBagConstraints.BOTH
        c.gridx = coordinate.x
        c.gridy = coordinate.y
        c.weightx = 1.0
        c.weighty = 1.0
        val newCellPanel = getNewCellPanelForCoordinate(coordinate)
        add(newCellPanel, c)
        return newCellPanel
    }

    private fun getNewCellPanelForCoordinate(coordinate: Coordinate) = CellPanel(board.getItemForCoordinate(coordinate))
}