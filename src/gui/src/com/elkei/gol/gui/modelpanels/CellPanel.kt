package com.elkei.gol.gui.modelpanels

import com.elkei.gol.model.Cell
import com.elkei.gol.model.CellListener
import com.elkei.gol.model.util.ListenersManager
import java.awt.Color
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JPanel
import javax.swing.SwingUtilities

class CellPanel(val cell: Cell): JPanel() {
    private var mouseClicking = false
    set(value) {
        if (value != field) {
            field = value
            if (!value) manipulateCell()
            updateCellState()
        }
    }

    private val listeners = ListenersManager<CellManipulationListener>()

    init {
        updateCellState()

        addMouseListener(object : MouseAdapter() {
            override fun mousePressed(p0: MouseEvent?) {
                mouseClicking = true
            }

            override fun mouseReleased(p0: MouseEvent?) {
                mouseClicking = false
            }

            override fun mouseEntered(p0: MouseEvent?) {
                mouseClicking = (SwingUtilities.isLeftMouseButton(p0))
            }

            override fun mouseExited(p0: MouseEvent?) {
                mouseClicking = false
            }
        })

        cell.addListener(object : CellListener {
            override fun cellLivingChanged(cell: Cell) {
                updateCellState()
            }
        })
    }

    private fun manipulateCell() {
        cell.living = !cell.living
        notifyManipulation()
    }

    private fun updateCellState() {
        background = when {
            cell.living && !mouseClicking -> Color.BLACK
            cell.living && mouseClicking -> Color.DARK_GRAY
            !cell.living && mouseClicking -> Color.LIGHT_GRAY
            else -> Color.WHITE
        }
        repaint()
    }

    fun addListener(listener: CellManipulationListener) = listeners.addListener(listener)
    fun removeListener(listener: CellManipulationListener) = listeners.removeListener(listener)
    private fun notifyManipulation() = listeners.forEach { it.cellPanelManipulated(this) }
}

interface CellManipulationListener {
    fun cellPanelManipulated(cellPane: CellPanel)
}