/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.frames.newboard

import com.elkei.gol.gui.SIMULATION_DELAY_DEFAULT
import com.elkei.gol.gui.res.GuiResources
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard
import java.awt.Frame

class NewBoardDialog(parent: Frame) : NewOrResizeBoardDialog(parent) {
    init {
        title = GuiResources.default.getStringOrKey(GuiResources.NEW_BOARD_TITLE_KEY)
    }

    override fun generateBoard(size: Coordinate): UpdatingBoard {
        return UpdatingBoard(size, msBetweenGenerationUpdates = SIMULATION_DELAY_DEFAULT)
    }
}