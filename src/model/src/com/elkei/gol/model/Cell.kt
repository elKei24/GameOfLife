/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model

import com.elkei.gol.model.util.ListenersManager
import kotlin.properties.Delegates

class Cell(living : Boolean = false) {
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

    fun addCellListener(listener: CellListener) = listenersManager.addListener(listener)
    @Suppress("unused")
    fun removeCellListener(listener: CellListener) = listenersManager.removeListener(listener)
    private fun notifyCellLivingChanged() = listenersManager.forEach { it.cellLivingChanged(this) }
}

interface CellListener {
    fun cellLivingChanged(cell: Cell)
}