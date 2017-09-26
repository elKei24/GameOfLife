package com.elkei.gol.model

import com.elkei.gol.model.util.ListenersManager
import com.elkei.gol.model.util.Observable
import java.util.*

/**
 * This class describes a Game-Of-Live board that can do generation updates by itself regularly.
 *
 * @see Board
 *
 * @param size Size of the constructed board.
 * @param init This function is called on each initialization of a [Cell] of the board, passing the coordinate of the new
 *              cell. If true is returned, then [Cell.living] will be set to true at the beginning, to false otherwise.
 * @param msBetweenUpdates Initial value of [UpdatingBoard.msBetweenUpdates]
 */
class UpdatingBoard(
        size: Coordinate,
        init: (Coordinate) -> Boolean = {false},
        msBetweenUpdates: Long = 750L
) : Board(size, init), Observable<UpdatingBoardListener> {
    private val listenersManager = ListenersManager<UpdatingBoardListener>()
    private var timer: Timer? = null

    /**
     * The generation counter is increased whenever this board proceeds to the next generation.
     *
     * @see UpdatingBoard.resetGenerationCounter
     */
    var generationCounter = 0
    private set(value) {
        if (value != field) {
            field = value
            notifyGenerationChanged()
        }
    }

    /**
     * This property describes if there is a background thread running to update the board to the next generation every
     * [msBetweenUpdates] milliseconds.
     */
    var running = false
    private set(value) {
        if (field != value) {
            field = value
            notifyRunningChanged()
        }
    }

    /**
     * This property describes how long to wait between two generation updates executed by the background thread.
     * The time needed for updating the board itself will be subtracted from this value.
     */
    var msBetweenUpdates: Long = msBetweenUpdates
    set(value) {
        val oldValue = field
        field = value
        startGenerationUpdates()
        if (oldValue != field) notifySpeedChanged()
    }

    /**
     * Starts updating the board every [msBetweenUpdates] milliseconds.
     * If already running, the old thread is stopped and a new one is started immediately.
     */
    fun startGenerationUpdates() {
        if (running) stopGenerationUpdates()
        val newTimer = Timer("BoardUpdaterThread", true)
        newTimer.schedule(BoardUpdateTask(), 0L, msBetweenUpdates)
        timer = newTimer
        running = true
    }

    private inner class BoardUpdateTask : TimerTask() {
        override fun run() {
            updateToNextGeneration()
        }
    }

    /**
     * Stops updating the board in the background. No further updates to the board will be made.
     */
    fun stopGenerationUpdates() {
        timer?.cancel()
        timer = null
        running = false
    }

    /**
     * Updates every cell to the next generation, calling [Cell.updateToNextGeneration].
     * After the generation update, [generationCounter] is increased by one.
     */
    override fun updateToNextGeneration() {
        super.updateToNextGeneration()
        generationCounter++
    }

    /**
     * Resets [generationCounter] to zero.
     */
    fun resetGenerationCounter() {
        generationCounter = 0
    }

    /**
     * Calls [UpdatingBoardListener.updatingBoardRunningChanged] for each listener
     */
    private fun notifyRunningChanged() = listenersManager.forEach { it.updatingBoardRunningChanged(this, running) }

    /**
     * Calls [UpdatingBoardListener.updatingBoardSpeedChanged] for each listener
     */
    private fun notifySpeedChanged() = listenersManager.forEach { it.updatingBoardSpeedChanged(this, msBetweenUpdates) }

    /**
     * Calls [UpdatingBoardListener.updatingBoardGenerationChanged] for each listener
     */
    private fun notifyGenerationChanged() = listenersManager.forEach { it.updatingBoardGenerationChanged(this, generationCounter) }

    /**
     * Adds an [UpdatingBoardListener]. It will be notified when special events occur in this [UpdatingBoard]
     *
     * @param listener the listener that will be notified on events
     */
    override fun addListener(listener: UpdatingBoardListener) = listenersManager.addListener(listener)

    /**
     * Removes the given listener. It will no longer be notified.
     *
     * @param listener the listener to remove
     */
    override fun removeListener(listener: UpdatingBoardListener) = listenersManager.removeListener(listener)
}

interface UpdatingBoardListener {
    /**
     * Called if an observed [UpdatingBoard] changes its [UpdatingBoard.running] state
     *
     * @param board the board that started/stopped updating
     * @param updatingFrequently the new value of [UpdatingBoard.running] of [board]
     */
    fun updatingBoardRunningChanged(board: UpdatingBoard, updatingFrequently: Boolean) {}
    /**
     * Called if an observed [UpdatingBoard] changes its [UpdatingBoard.msBetweenUpdates]
     *
     * @param board the board that changed its simulation speed
     * @param msBetweenUpdates the new value of [UpdatingBoard.msBetweenUpdates] of [board]
     */
    fun updatingBoardSpeedChanged(board: UpdatingBoard, msBetweenUpdates: Long) {}
    /**
     * Called if an observed [UpdatingBoard] proceeds to the next generation
     *
     * @param board the board that proceeded to the next generations
     * @param generation the new generation number
     */
    fun updatingBoardGenerationChanged(board: UpdatingBoard, generation: Int) {}
}