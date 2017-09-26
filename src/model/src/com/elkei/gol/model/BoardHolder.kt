package com.elkei.gol.model

import com.elkei.gol.model.util.ListenersManager

/**
 * This class holds an instance of [UpdatingBoard] and can notify its listeners if this instance changes.
 *
 * @see UpdatingBoard
 * @see BoardHolderListener
 */
class BoardHolder(board: UpdatingBoard = UpdatingBoard(Coordinate(20, 20))) {
    /**
     * The board held by this [BoardHolder].
     *
     * **Attention!** The held board can change any time, do not store the instance of this field or any of its
     * properties without updating them using a [BoardHolderListener]!
     */
    var currentBoard = board
    set(value) {
        if (field != value) {
            run {
                val oldValue = field
                field = value
                onInstanceChange(oldValue, value)
            }
            System.gc()
        }
    }

    private val listeners = ListenersManager<BoardHolderListener>()

    /**
     * Replaces the held board with another one with the given size.
     *
     * @param size size of the new board
     */
    fun resize(size: Coordinate) {
        currentBoard = currentBoard.getResized(size)
    }

    /**
     * Sets [Cell.living] of every [Cell] in the held [Board] to `false`
     *
     * @see Cell.living
     */
    fun clear() {
        currentBoard.allCoordinates().map(currentBoard::getItemForCoordinate).forEach { it.living = false }
    }

    //region listeners
    /**
     * Adds a [BoardHolderListener]. It will be notified on special events.
     *
     * @param listener added listener
     */
    fun addBoardHolderListener(listener: BoardHolderListener) = listeners.addListener(listener)

    /**
     * Removes a [BoardHolderListener]. It will no longer be notified.
     *
     * @param listener listener to be removed
     */
    fun removeBoardHolderListener(listener: BoardHolderListener) = listeners.removeListener(listener)
    private fun onInstanceChange(oldBoard: UpdatingBoard, newBoard: UpdatingBoard) =
            listeners.forEach { it.heldInstanceChanged(this, oldBoard, newBoard) }
    //endregion
}

/**
 * An interface used to observe a [BoardHolder]
 */
interface BoardHolderListener {
    /**
     * Called when [BoardHolder.currentBoard] changes
     *
     * @param holder the [BoardHolder] that fired this event
     * @param oldBoard the old instance held by [holder]
     * @param newBoard the new instance held by [holder]
     */
    fun heldInstanceChanged(holder: BoardHolder, oldBoard: UpdatingBoard, newBoard: UpdatingBoard)
}