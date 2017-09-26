package com.elkei.gol.gui.actions

import com.elkei.gol.gui.actions.file.*
import com.elkei.gol.gui.actions.generations.*
import com.elkei.gol.gui.actions.help.AboutAction
import com.elkei.gol.gui.frames.main.MainFrame

class ActionsHolder(mainFrame: MainFrame) {
    //file actions
    val newBoardAction = NewBoardAction(mainFrame)
    val resizeAction = ResizeAction(mainFrame)
    val safeAction = SafeAction(mainFrame)
    val loadAction = LoadAction(mainFrame)
    val exitAction = ExitAction(mainFrame)

    //generation actions
    val nextGenerationAction = NextGenerationAction(mainFrame.boardHolder)
    val startGenerationUpdatesAction = StartGenerationUpdatesAction(mainFrame.boardHolder)
    val stopGenerationUpdatesAction = StopGenerationUpdatesAction(mainFrame.boardHolder)
    val slowerGenerationUpdatesAction = SlowerGenerationUpdatesAction(mainFrame.boardHolder)
    val fasterGenerationUpdatesAction = FasterGenerationUpdatesAction(mainFrame.boardHolder)

    //help actions
    val aboutAction = AboutAction(mainFrame)
}