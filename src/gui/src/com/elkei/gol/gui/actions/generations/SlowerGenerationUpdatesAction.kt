package com.elkei.gol.gui.actions.generations

import com.elkei.gol.gui.SIMULATION_DELAY_FASTER_FACTOR
import com.elkei.gol.gui.SIMULATION_DELAY_MAXIMUM
import com.elkei.gol.model.BoardHolder
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class SlowerGenerationUpdatesAction(boardHolder: BoardHolder) :
        ChangeGenerationUpdateSpeedAction(boardHolder, "generations.speed.slower",
                accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0)) {

    override fun checkEnabled(): Boolean {
        return (calculateNewDelay() <= SIMULATION_DELAY_MAXIMUM)
    }

    override fun calculateNewDelay(): Long {
        return (boardHolder.currentBoard.msBetweenGenerationUpdates * SIMULATION_DELAY_FASTER_FACTOR).toLong()
    }
}
