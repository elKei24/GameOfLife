package com.elkei.gol.gui.actions

import com.elkei.gol.gui.actions.file.ExitAction
import com.elkei.gol.gui.actions.generations.NextGenerationAction
import com.elkei.gol.gui.actions.generations.StartGenerationUpdatesAction
import com.elkei.gol.gui.actions.generations.StopGenerationUpdatesAction
import com.elkei.gol.gui.mainframe.MainFrame

class ActionsHolder(mainFrame: MainFrame) {
    //file actions
    val exitAction = ExitAction(mainFrame)

    //generation actions
    val nextGenerationAction = NextGenerationAction(mainFrame.boardPanel)
    val startGenerationUpdatesAction = StartGenerationUpdatesAction(mainFrame.boardPanel)
    val stopGenerationUpdatesAction = StopGenerationUpdatesAction(mainFrame.boardPanel)
}