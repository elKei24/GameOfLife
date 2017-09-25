package com.elkei.gol.gui.mainframe

import com.elkei.gol.gui.actions.ActionsHolder
import com.elkei.gol.gui.actions.file.FileMenuAction
import com.elkei.gol.gui.actions.generations.GenerationsMenuAction
import javax.swing.JMenu
import javax.swing.JMenuBar

class MainFrameMenuBar(actions: ActionsHolder) : JMenuBar() {
    init {
        add(MainFrameFileMenu(actions))
        add(MainFrameGenerationsMenu(actions))
    }

    private class MainFrameFileMenu(actions: ActionsHolder) : JMenu(FileMenuAction()) {
        init {
            add(actions.exitAction)
        }
    }

    private class MainFrameGenerationsMenu(actions: ActionsHolder) : JMenu(GenerationsMenuAction()) {
        init {
            add(actions.nextGenerationAction)
            add(actions.startGenerationUpdatesAction)
            add(actions.stopGenerationUpdatesAction)
        }
    }
}