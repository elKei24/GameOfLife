package com.elkei.gol.gui.frames.main

import com.elkei.gol.gui.SIMULATION_DELAY_DEFAULT
import com.elkei.gol.gui.actions.ActionsHolder
import com.elkei.gol.gui.modelpanels.UpdatingBoardPanel
import com.elkei.gol.gui.res.GuiResources
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame

class MainFrame : JFrame(GuiResources.default.getStringOrKey(GuiResources.MAINTITLE_KEY)) {
    internal val boardPanel = UpdatingBoardPanel(UpdatingBoard(Coordinate(4, 5), listOf(Coordinate(0, 0),
            Coordinate(1, 0), Coordinate(0, 1), Coordinate(1, 1), Coordinate(2, 1),
            Coordinate(1, 3), Coordinate(2, 3)), msBetweenUpdates = SIMULATION_DELAY_DEFAULT))
    private val actions = ActionsHolder(this)

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jMenuBar = MainFrameMenuBar(actions)
        layout = BorderLayout()

        add(boardPanel, BorderLayout.CENTER)

        preferredSize = Dimension(700, 500)
        minimumSize = Dimension(170, 150)
        size = preferredSize
    }
}