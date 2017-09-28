/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

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
        //cell panel listener resets generation counter when the user clicks a cell
        val cellPanelListener = object : CellManipulationListener {
            override fun cellPanelManipulated(cellPane: CellPanel) {
                board.resetGenerationCounter()
            }
        }
        //cell mouse listener lets user switch cell states by clicking/dragging
        val cellMouseListener = CellMouseListener()

        //add the cell panels and attach the listeners to them
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

    /**
     * This class implements a MouseListener that can be added to [CellPanel]s, allowing the user to switch the state
     * of many cells at once by pressing the mouse button and moving the pointer over different cells.
     */
    private class CellMouseListener : MouseAdapter() {
        var mousePressed = false
        /**
         * Determines the new state of cells hit by the mouse pointer while the mouse is pressed.
         * True if the hit cells should come alive, false if they should be dead.
         */
        var drawingLivingCells = true

        //workaround for mouseRelease called by component where mouse was pressed and not where it was released
        var currentMouseComponent: CellPanel? = null

        override fun mousePressed(event: MouseEvent) {
            val sender = event.component as? CellPanel ?: return
            mousePressed = true
            currentMouseComponent = sender
            drawingLivingCells = !sender.cell.living
            sender.mousePressed(drawingLivingCells)
        }

        override fun mouseReleased(event: MouseEvent) {
            if (event.component !is CellPanel) return
            if (mousePressed) {
                currentMouseComponent?.mouseReleased()
                mousePressed = false
            }
        }

        override fun mouseEntered(event: MouseEvent) {
            val sender = event.component as? CellPanel ?: return
            if (mousePressed) {
                currentMouseComponent = sender
                sender.mousePressed(drawingLivingCells)
            }
        }

        override fun mouseExited(event: MouseEvent) {
            val sender = event.component as? CellPanel ?: return
            if (mousePressed) sender.mouseReleased()
        }


    }
}