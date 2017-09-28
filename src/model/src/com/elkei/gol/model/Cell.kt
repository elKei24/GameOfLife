/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model

import com.elkei.gol.model.util.ListenersManager
import kotlin.properties.Delegates

/**
 * Instances of this class represent a cell of Conway's Game Of Live.
 * A cell can either be alive or dead. It can be updated to the next generation and then either change its state
 * or stay the same, depending on the number of living neighbour cells.
 *
 * @param living true if this instance should be initialized as living, false if it should be dead
 */
class Cell(living : Boolean = false) {
    private val listenersManager = ListenersManager<CellListener>()

    /**
     * True if this instance currently represents a living cell, false if it represents a dead one.
     * Listeners are notified if this value changes.
     */
    var living: Boolean by Delegates.observable(living, {
        _, old, new -> if (old != new) notifyCellLivingChanged()
    })

    /**
     * Changes the value of [living], depending on the number of neighbours alive.
     * If this cell is currently dead, it only becomes alive if it has exactly three living neighbours.
     * A living cell only stays alive if it has two or three living neighbours.
     *
     * @param livingNeighbours the number of living neighbours of this cell
     */
    fun updateToNextGeneration(livingNeighbours: Int) {
        living = if (!living)
            livingNeighbours == 3
        else
            livingNeighbours == 2 || livingNeighbours == 3
    }

    //region listeners
    /**
     * Adds a listener to this cell. The listener will be notified on certain events.
     * If [listener] is already listening to this cell, nothing is done.
     * @param listener the listener to add
     */
    fun addCellListener(listener: CellListener) = listenersManager.addListener(listener)

    /**
     * Removes a listener from this cell. The listener will no longer be notified. If [listener] is currently
     * not listening to this cell, nothing is done.
     * @param listener the listener to be removed
     */
    @Suppress("unused")
    fun removeCellListener(listener: CellListener) = listenersManager.removeListener(listener)

    /**
     * Calls [CellListener.cellLivingChanged] for every listener of this cell
     */
    private fun notifyCellLivingChanged() = listenersManager.forEach { it.cellLivingChanged(this) }
    //endregion
}

/**
 * Instances of this listener can listen to instances of [Cell] using [Cell.addCellListener]
 */
interface CellListener {
    /**
     * Called if the value of [Cell.living] of [cell] changed
     *
     * @param cell the cell that changed its [Cell.living] value
     */
    fun cellLivingChanged(cell: Cell)
}