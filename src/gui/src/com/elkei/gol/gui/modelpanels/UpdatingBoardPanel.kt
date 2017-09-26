package com.elkei.gol.gui.modelpanels

import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import com.elkei.gol.model.util.Observable
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JPanel

class UpdatingBoardPanel(var board: UpdatingBoard) :
        JPanel(GridBagLayout(), true), Observable<UpdatingBoardListener> by board {

    init {
        val cellPanelListener = object : CellManipulationListener {
            override fun cellPanelManipulated(cellPane: CellPanel) {
                board.resetGenerationCounter()
            }
        }
        val cellMouseListener = CellMouseListener()
        board.allCoordinates().forEach { coordinate ->
            val newCellPanel = addNewCellPanelAt(coordinate)
            newCellPanel.addListener(cellPanelListener)
            newCellPanel.addMouseListener(cellMouseListener)
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

    private class CellMouseListener : MouseAdapter() {
        var mouseClicking = false
        var newLiving = true
        var currentMouseComponent: CellPanel? = null //workaround for mouseRelease called by component where mouse was pressed

        override fun mousePressed(event: MouseEvent) {
            val sender = event.component
            if (sender is CellPanel) {
                currentMouseComponent = sender
                mouseClicking = true
                newLiving = !sender.cell.living
                sender.mousePressed(newLiving)
            }
        }

        override fun mouseReleased(event: MouseEvent) {
            val sender = event.component
            if (sender is CellPanel && mouseClicking) {
                currentMouseComponent?.mouseReleased()
                mouseClicking = false
            }
        }

        override fun mouseEntered(event: MouseEvent) {
            val sender = event.component
            if (sender is CellPanel && mouseClicking) {
                currentMouseComponent = sender
                sender.mousePressed(newLiving)
            }
        }

        override fun mouseExited(event: MouseEvent) {
            val sender = event.component
            if (sender is CellPanel && mouseClicking) {
                sender.mouseReleased()
            }
        }


    }
}