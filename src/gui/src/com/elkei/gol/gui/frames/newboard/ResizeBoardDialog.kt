package com.elkei.gol.gui.frames.newboard

import com.elkei.gol.gui.res.GuiResources
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard
import java.awt.Frame

class ResizeBoardDialog(parent: Frame, private val currentBoard: UpdatingBoard) : NewOrResizeBoardDialog(parent) {
    init {
        title = GuiResources.default.getStringOrKey(GuiResources.RESIZE_BOARD_TITLE_KEY)

        val (sizeX, sizeY) = currentBoard.size
        xSpinner.value = sizeX
        ySpinner.value = sizeY
    }

    override fun generateBoard(size: Coordinate): UpdatingBoard {
        return currentBoard.getResized(size)
    }
}