package com.elkei.gol.gui.actions

import com.elkei.gol.model.Board
import java.awt.event.ActionEvent

class GenerationsMenuAction : ResourceAction("generations") {
    override fun actionPerformed(p0: ActionEvent?) {
        //nothing to do, just open the submenu
    }
}


class NextGenerationAction(private val board: Board) : ResourceAction("generations.step") {
    override fun actionPerformed(p0: ActionEvent?) {
        board.updateToNextGeneration()
    }
}