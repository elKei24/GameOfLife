package com.elkei.gol.model

import com.elkei.gol.model.util.ListenersManager
import com.elkei.gol.model.util.Observable
import java.util.*

class UpdatingBoard(
        size: Coordinate,
        coordinatesOfLivingCells: List<Coordinate> = emptyList(),
        msBetweenUpdates: Long = 750L
) : Board(size, coordinatesOfLivingCells), Observable<UpdatingBoardListener> {
    private val listenersManager = ListenersManager<UpdatingBoardListener>()
    private var timer: Timer? = null
    var generationCounter = 0
    private set(value) {
        if (value != field) {
            field = value
            notifyGenerationChanged()
        }
    }

    var running = false
    private set(value) {
        if (field != value) {
            field = value
            notifyRunningChanged()
        }
    }

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

    /**
     * Stops updating the board in the background. No further updates to the board will be made.
     */
    fun stopGenerationUpdates() {
        timer?.cancel()
        timer = null
        running = false
    }

    override fun updateToNextGeneration() {
        super.updateToNextGeneration()
        generationCounter++
    }

    private inner class BoardUpdateTask : TimerTask() {
        override fun run() {
            updateToNextGeneration()
        }
    }

    private fun notifyRunningChanged() = listenersManager.forEach { it.updatingBoardRunningChanged(this, running) }
    private fun notifySpeedChanged() = listenersManager.forEach { it.updatingBoardSpeedChanged(this, msBetweenUpdates) }
    private fun notifyGenerationChanged() = listenersManager.forEach { it.updatingBoardGenerationChanged(this, generationCounter) }
    override fun addListener(listener: UpdatingBoardListener) = listenersManager.addListener(listener)
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