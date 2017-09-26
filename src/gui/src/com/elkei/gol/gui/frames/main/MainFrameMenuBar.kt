package com.elkei.gol.gui.frames.main

import com.elkei.gol.gui.actions.ActionsHolder
import com.elkei.gol.gui.actions.file.FileMenuAction
import com.elkei.gol.gui.actions.generations.GenerationsMenuAction
import com.elkei.gol.gui.actions.generations.GenerationsSpeedMenuAction
import javax.swing.JMenu
import javax.swing.JMenuBar

internal class MainFrameMenuBar(actions: ActionsHolder) : JMenuBar() {
    init {
        add(MainFrameFileMenu(actions))
        add(MainFrameGenerationsMenu(actions))
    }

    private class MainFrameFileMenu(actions: ActionsHolder) : JMenu(FileMenuAction()) {
        init {
            add(actions.newBoardAction)
            add(actions.resizeAction)
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
}