package com.elkei.gol.gui.modelpanels

import com.elkei.gol.model.Cell
import com.elkei.gol.model.CellListener
import com.elkei.gol.model.util.ListenersManager
import java.awt.Color
import javax.swing.JPanel

class CellPanel(val cell: Cell): JPanel() {
    private val listeners = ListenersManager<CellManipulationListener>()

    private var mouseClicking = false
    private set(value) {
        if (field != value) {
            field = value
            if (livingAfterMouseClick != cell.living) manipulateCell()
        }
        updateCellState()
    }
    private var livingAfterMouseClick = false
    private set(value) {
        field = value
        updateCellState()
    }

    init {
        updateCellState()

        cell.addListener(object : CellListener {
            override fun cellLivingChanged(cell: Cell) {
                updateCellState()
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

    private fun manipulateCell() {
        cell.living = !cell.living
        notifyManipulation()
    }

    private fun updateCellState() {
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

    private fun checkIfLivingChangesSoon() = mouseClicking && (cell.living != livingAfterMouseClick)

    fun addListener(listener: CellManipulationListener) = listeners.addListener(listener)
    fun removeListener(listener: CellManipulationListener) = listeners.removeListener(listener)
    private fun notifyManipulation() = listeners.forEach { it.cellPanelManipulated(this) }
}

interface CellManipulationListener {
    fun cellPanelManipulated(cellPane: CellPanel)
}