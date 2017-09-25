package com.elkei.gol.gui.frames.newboard

import com.elkei.gol.gui.SIMULATION_DELAY_DEFAULT
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard

/**
 * Returns a new [UpdatingBoard] with a given size.
 *
 * Java does not support Kotlin's default parameters. To construct a new [UpdatingBoard] with default constructor
 * parameters anyway, the [NewBoardDialog] can use this simple Kotlin function.
 *
 * @param size Size of the generated [UpdatingBoard]
 */
internal fun getNewUpdatingBoard(size: Coordinate) = UpdatingBoard(size, msBetweenUpdates = SIMULATION_DELAY_DEFAULT)