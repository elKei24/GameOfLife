import actions.FileMenuAction
import javax.swing.JMenu
import javax.swing.JMenuBar

class MainFrameMenuBar(mainForm: MainFrame) : JMenuBar() {
    init {
        add(MainFrameFileMenu(mainForm))
    }

    private class MainFrameFileMenu(mainForm: MainFrame) : JMenu(FileMenuAction()) {
        init {
            add(mainForm.exitAction)
        }
    }
}