package com.elkei.gol.model

import com.elkei.gol.model.util.ListenersManager
import com.elkei.gol.model.util.Observable
import kotlin.properties.Delegates

class Cell(living : Boolean = false) : Observable<CellListener>{
    private val listenersManager = ListenersManager<CellListener>()

    var living: Boolean by Delegates.observable(living, {
        _, old, new -> if (old != new) notifyCellLivingChanged()
    })

    fun updateToNextGeneration(livingNeighbours: Int) {
        living = if (!living)
            livingNeighbours == 3
        else
            livingNeighbours == 2 || livingNeighbours == 3
    }

    override fun addListener(listener: CellListener) = listenersManager.addListener(listener)
    override fun removeListener(listener: CellListener) = listenersManager.removeListener(listener)
    private fun notifyCellLivingChanged() = listenersManager.forEach { it.cellLivingChanged(this) }
}

interface CellListener {
    fun cellLivingChanged(cell: Cell)
}