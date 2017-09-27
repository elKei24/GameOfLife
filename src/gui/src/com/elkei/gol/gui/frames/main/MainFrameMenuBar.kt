/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.frames.main

import com.elkei.gol.gui.actions.ActionsHolder
import com.elkei.gol.gui.actions.file.FileMenuAction
import com.elkei.gol.gui.actions.generations.GenerationsMenuAction
import com.elkei.gol.gui.actions.generations.GenerationsSpeedMenuAction
import com.elkei.gol.gui.actions.help.HelpMenuAction
import javax.swing.JMenu
import javax.swing.JMenuBar

internal class MainFrameMenuBar(actions: ActionsHolder) : JMenuBar() {
    init {
        add(MainFrameFileMenu(actions))
        add(MainFrameGenerationsMenu(actions))
        add(MainFrameHelpMenu(actions))
    }

    private class MainFrameFileMenu(actions: ActionsHolder) : JMenu(FileMenuAction()) {
        init {
            add(actions.newBoardAction)
            add(actions.resizeAction)
            addSeparator()
            add(actions.loadAction)
            add(actions.safeAction)
            addSeparator()
            add(actions.exitAction)
        }
    }

    private class MainFrameGenerationsMenu(actions: ActionsHolder) : JMenu(GenerationsMenuAction()) {
        init {
            add(actions.nextGenerationAction)
            add(actions.startGenerationUpdatesAction)
            add(actions.stopGenerationUpdatesAction)
            add(MainFrameGenerationsSpeedMenu(actions))
        }

        private class MainFrameGenerationsSpeedMenu(actions: ActionsHolder) : JMenu(GenerationsSpeedMenuAction()) {
            init {
                add(actions.fasterGenerationUpdatesAction)
                add(actions.slowerGenerationUpdatesAction)
            }
        }
    }

    private class MainFrameHelpMenu(actions: ActionsHolder) : JMenu(HelpMenuAction()) {
        init {
            add(actions.aboutAction)
        }
    }
}