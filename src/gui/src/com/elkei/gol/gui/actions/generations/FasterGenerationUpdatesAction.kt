package com.elkei.gol.gui.actions.generations

import com.elkei.gol.gui.SIMULATION_DELAY_FASTER_FACTOR
import com.elkei.gol.gui.SIMULATION_DELAY_MINIMUM
import com.elkei.gol.model.BoardHolder
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class FasterGenerationUpdatesAction(boardHolder: BoardHolder) :
        ChangeGenerationUpdateSpeedAction(boardHolder, "generations.speed.faster",
                accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0)) {

    override fun checkEnabled(): Boolean {
        return calculateNewDelay() >= SIMULATION_DELAY_MINIMUM
    }

    override fun calculateNewDelay(): Long {
        return (boardHolder.currentBoard.msBetweenGenerationUpdates / SIMULATION_DELAY_FASTER_FACTOR).toLong()
    }
}