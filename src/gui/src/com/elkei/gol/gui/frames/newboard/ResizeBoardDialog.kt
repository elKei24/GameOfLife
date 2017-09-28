/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.frames.newboard

import com.elkei.gol.gui.res.GuiResources
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard
import java.awt.Frame

class ResizeBoardDialog(parent: Frame, private val currentBoard: UpdatingBoard) : NewOrResizeBoardDialog(parent) {
    init {
        title = GuiResources.default.getStringOrKey(GuiResources.RESIZE_BOARD_TITLE_KEY)

        //set size spinners to the current board size
        val (sizeX, sizeY) = currentBoard.size
        xSpinner.value = sizeX
        ySpinner.value = sizeY
    }

    override fun generateBoard(size: Coordinate): UpdatingBoard {
        return currentBoard.getResized(size)
    }
}