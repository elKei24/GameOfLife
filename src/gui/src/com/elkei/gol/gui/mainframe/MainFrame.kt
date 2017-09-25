package com.elkei.gol.gui.mainframe

import com.elkei.gol.gui.actions.ExitAction
import com.elkei.gol.gui.actions.NextGenerationAction
import com.elkei.gol.gui.actions.StartGenerationUpdatesAction
import com.elkei.gol.gui.actions.StopGenerationUpdatesAction
import com.elkei.gol.gui.modelpanels.UpdatingBoardPanel
import com.elkei.gol.gui.res.GuiResources
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame

class MainFrame : JFrame(GuiResources.default.getMainFrameTitle()) {
    val exitAction = ExitAction(this)
    private val boardPanel = UpdatingBoardPanel(UpdatingBoard(Coordinate(4, 5), listOf(Coordinate(0, 0), Coordinate(1, 0), Coordinate(0, 1),
            Coordinate(1, 1), Coordinate(2, 1), Coordinate(1, 3), Coordinate(2, 3))))
    val nextGenerationAction = NextGenerationAction(boardPanel)
    val startGenerationUpdatesAction = StartGenerationUpdatesAction(boardPanel)
    val stopGenerationUpdatesAction = StopGenerationUpdatesAction(boardPanel)

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jMenuBar = MainFrameMenuBar(this)
        layout = BorderLayout()

        add(boardPanel, BorderLayout.CENTER)

        size = Dimension(500, 500)
    }
}