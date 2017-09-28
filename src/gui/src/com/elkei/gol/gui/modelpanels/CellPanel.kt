/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.modelpanels

import com.elkei.gol.model.Cell
import com.elkei.gol.model.CellListener
import com.elkei.gol.model.util.ListenersManager
import java.awt.Color
import javax.swing.JPanel

/**
 * A Panel that displays and manipulates a [Cell].
 *
 * @property cell the displayed [Cell]
 */
class CellPanel(val cell: Cell): JPanel() {
    private val listeners = ListenersManager<CellManipulationListener>()

    /**
     * This value describes if the user is currently switching the state of the cell by pressing the mouse button
     * over this component. When changed, the display is updated.
     */
    private var mouseClicking = false
    private set(value) {
        if (field != value) {
            field = value
            if (livingAfterMouseClick != cell.living) manipulateCell()
        }
        updateDisplayedCellState()
    }

    /**
     * This value describes which state the user wants the cell to have after releasing the mouse button.
     * This value is set by [mousePressed] because it depends on the state of the cell where the mouse was originally
     * pressed and so is managed by [UpdatingBoardPanel].
     */
    private var livingAfterMouseClick = false
    private set(value) {
        field = value
        updateDisplayedCellState()
    }

    init {
        updateDisplayedCellState()

        //change displayed color whenever the cell changes
        cell.addCellListener(object : CellListener {
            override fun cellLivingChanged(cell: Cell) {
                updateDisplayedCellState()
            }
        })
    }

    fun mousePressed(livingAfterMouseClick: Boolean) {
        mouseClicking = true
        this.livingAfterMouseClick = livingAfterMouseClick
    }

    fun mouseReleased() {
        mouseClicking = false
    }

    /**
     * Switches if [cell] is living and notifies listeners about this manipulation.
     */
    private fun manipulateCell() {
        cell.living = !cell.living
        notifyManipulation()
    }

    /**
     * Changes the background color of this panel depending on the state of [cell] and if the user is currently
     * pressing the mouse button.
     */
    private fun updateDisplayedCellState() {
        background = if (cell.living) {
            if (!checkIfLivingChangesSoon())
                Color.BLACK
            else
                Color.DARK_GRAY
        } else {
            if (!checkIfLivingChangesSoon()) {
                Color.WHITE
            } else
                Color.LIGHT_GRAY
        }
        repaint()
    }

    /**
     * @return true if the user currently presses the mouse button over this panel and wants the cell to change its
     * state after releasing the button
     */
    private fun checkIfLivingChangesSoon() = mouseClicking && (cell.living != livingAfterMouseClick)

    //region listeners
    fun addListener(listener: CellManipulationListener) = listeners.addListener(listener)
    fun removeListener(listener: CellManipulationListener) = listeners.removeListener(listener)
    private fun notifyManipulation() = listeners.forEach { it.cellPanelManipulated(this) }
    //endregion
}

interface CellManipulationListener {
    fun cellPanelManipulated(cellPane: CellPanel)
}