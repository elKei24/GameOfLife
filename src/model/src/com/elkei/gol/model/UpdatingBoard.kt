package com.elkei.gol.model

import com.elkei.gol.model.util.ListenersManager
import com.elkei.gol.model.util.Observable
import java.util.*

class UpdatingBoard(
        size: Coordinate,
        coordinatesOfLivingCells: List<Coordinate> = emptyList(),
        msBetweenUpdates: Long = 1000L
) : Board(size, coordinatesOfLivingCells), Observable<UpdatingBoardListener> {
    private val listenersManager = ListenersManager<UpdatingBoardListener>()
    private var timer: Timer? = null

    var running = false
    private set(value) {
        if (field != value) notifyRunningChanged()
    }

    var msBetweenUpdates: Long = msBetweenUpdates
    set(value) {
        field = value
        startGenerationUpdates()
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
    }

    /**
     * Stops updating the board in the background. No further updates to the board will be made.
     */
    fun stopGenerationUpdates() {
        timer?.cancel()
        timer = null
        running = false
    }

    private inner class BoardUpdateTask : TimerTask() {
        override fun run() {
            updateToNextGeneration()
        }
    }

    private fun notifyRunningChanged() = listenersManager.forEach { it.updatingBoardRunningChanged(this, running) }
    override fun addListener(listener: UpdatingBoardListener) = listenersManager.addListener(listener)
    override fun removeListener(listener: UpdatingBoardListener) = listenersManager.removeListener(listener)
}

interface UpdatingBoardListener {
    /**
     * Called if an observed [UpdatingBoard] changes its [UpdatingBoard.running] state
     *
     * @param board the board that started/stopped updating
     * @param updating the new value of [UpdatingBoard.running] of [board]
     */
    fun updatingBoardRunningChanged(board: UpdatingBoard, updating: Boolean)
}