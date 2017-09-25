package com.elkei.gol.gui.actions

import com.elkei.gol.gui.actions.file.ExitAction
import com.elkei.gol.gui.actions.file.NewBoardAction
import com.elkei.gol.gui.actions.generations.*
import com.elkei.gol.gui.frames.main.MainFrame

class ActionsHolder(mainFrame: MainFrame) {
    //file actions
    val newBoardAction = NewBoardAction(mainFrame)
    val exitAction = ExitAction(mainFrame)

    //generation actions
    val nextGenerationAction = NextGenerationAction(mainFrame.boardPanel)
    val startGenerationUpdatesAction = StartGenerationUpdatesAction(mainFrame.boardPanel)
    val stopGenerationUpdatesAction = StopGenerationUpdatesAction(mainFrame.boardPanel)
    val slowerGenerationUpdatesAction = SlowerGenerationUpdatesAction(mainFrame.boardPanel)
    val fasterGenerationUpdatesAction = FasterGenerationUpdatesAction(mainFrame.boardPanel)
}