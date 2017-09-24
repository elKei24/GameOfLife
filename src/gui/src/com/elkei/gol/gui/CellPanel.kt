package com.elkei.gol.gui

import com.elkei.gol.model.Cell
import com.elkei.gol.model.CellListener
import java.awt.Color
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JPanel

class CellPanel(val cell: Cell): JPanel() {
    init {
        updateCellState()

        addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(p0: MouseEvent?) {
                cell.living = !cell.living
            }
        })

        cell.addListener(object : CellListener {
            override fun cellLivingChanged(cell: Cell) {
                updateCellState()
            }
        })
    }

    private fun updateCellState() {
        background = if (cell.living) Color.BLACK else Color.WHITE
    }
}