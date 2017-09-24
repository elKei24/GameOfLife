package com.elkei.gol.model

import java.util.concurrent.CopyOnWriteArrayList

class Cell(living : Boolean = false) {
    private val listeners = CopyOnWriteArrayList<CellListener>()

    var living = living
    set(value) {
        if (field != value) {
            field = value
            notifyCellLivingChanged()
        }
    }

    fun updateToNextGeneration(livingNeighbours: Int) {
        living = if (!living)
            livingNeighbours == 3
        else
            livingNeighbours == 2 || livingNeighbours == 3
    }

    fun addListener(cellListener: CellListener) = listeners.add(cellListener)
    fun removeListener(cellListener: CellListener) = listeners.remove(cellListener)
    private fun notifyCellLivingChanged() = listeners.forEach{it.cellLivingChanged(this)}
}

interface CellListener {
    fun cellLivingChanged(cell: Cell)
}