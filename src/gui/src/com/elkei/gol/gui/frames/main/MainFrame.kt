/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.frames.main

import com.elkei.gol.gui.SIMULATION_DELAY_DEFAULT
import com.elkei.gol.gui.actions.ActionsHolder
import com.elkei.gol.gui.io.SaveLoadDialog
import com.elkei.gol.gui.modelpanels.BoardHolderPanel
import com.elkei.gol.gui.res.GuiResources
import com.elkei.gol.model.BoardHolder
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.UpdatingBoard
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

class MainFrame : JFrame(GuiResources.default.getStringOrKey(GuiResources.MAINTITLE_KEY)) {
    //defaultBoard should not be static because different instances of MainFrame would then use the same board
    private val defaultBoard = UpdatingBoard(Coordinate(10, 10), listOf(
                    Coordinate(3, 5),
                    Coordinate(4, 3),
                    Coordinate(4, 5),
                    Coordinate(5, 4),
                    Coordinate(5, 5))
            ::contains,
            msBetweenGenerationUpdates = SIMULATION_DELAY_DEFAULT)

    val boardHolder = BoardHolder(defaultBoard)
    private var actions: ActionsHolder = ActionsHolder(this)
    val safeLoadDialog = SaveLoadDialog(this)

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jMenuBar = MainFrameMenuBar(actions)
        layout = BorderLayout()

        add(BoardHolderPanel(boardHolder), BorderLayout.CENTER)
        add(MainFrameStatusBar(this), BorderLayout.SOUTH)

        preferredSize = Dimension(700, 500)
        minimumSize = Dimension(170, 150)
        size = preferredSize

        //stop threads when the user exits the application
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(p0: WindowEvent?) {
                boardHolder.currentBoard.stopGenerationUpdates()
            }
        })
    }
}