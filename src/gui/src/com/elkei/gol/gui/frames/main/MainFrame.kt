package com.elkei.gol.gui.frames.main

import com.elkei.gol.gui.SIMULATION_DELAY_DEFAULT
import com.elkei.gol.gui.actions.ActionsHolder
import com.elkei.gol.gui.modelpanels.BoardHolderPanel
import com.elkei.gol.gui.res.GuiResources
import com.elkei.gol.model.BoardHolder
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame

class MainFrame : JFrame(GuiResources.default.getStringOrKey(GuiResources.MAINTITLE_KEY)) {
    val boardHolder = BoardHolder(UpdatingBoard(Coordinate(4, 5), listOf(Coordinate(0, 0),
            Coordinate(1, 0), Coordinate(0, 1), Coordinate(1, 1), Coordinate(2, 1),
            Coordinate(1, 3), Coordinate(2, 3))::contains, msBetweenGenerationUpdates = SIMULATION_DELAY_DEFAULT))
    private var actions: ActionsHolder = ActionsHolder(this)

    init {

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jMenuBar = MainFrameMenuBar(actions)
        layout = BorderLayout()

        add(BoardHolderPanel(boardHolder), BorderLayout.CENTER)
        add(MainFrameStatusBar(this), BorderLayout.SOUTH)

        preferredSize = Dimension(700, 500)
        minimumSize = Dimension(170, 150)
        size = preferredSize
    }
}