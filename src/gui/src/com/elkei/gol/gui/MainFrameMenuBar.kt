package com.elkei.gol.gui

import com.elkei.gol.gui.actions.FileMenuAction
import com.elkei.gol.gui.actions.GenerationsMenuAction
import javax.swing.JMenu
import javax.swing.JMenuBar

class MainFrameMenuBar(mainFrame: MainFrame) : JMenuBar() {
    init {
        add(MainFrameFileMenu(mainFrame))
        add(MainFrameGenerationsMenu(mainFrame))
    }

    private class MainFrameFileMenu(mainFrame: MainFrame) : JMenu(FileMenuAction()) {
        init {
            add(mainFrame.exitAction)
        }
    }

    private class MainFrameGenerationsMenu(mainFrame: MainFrame) : JMenu(GenerationsMenuAction()) {
        init {
            add(mainFrame.nextGenerationAction)
            add(mainFrame.startGenerationUpdatesAction)
            add(mainFrame.stopGenerationUpdatesAction)
        }
    }
}