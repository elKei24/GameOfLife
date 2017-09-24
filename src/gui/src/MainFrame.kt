import actions.ExitAction
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JTable

class MainFrame : JFrame(GuiResources.default.getMainFrameTitle()) {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            MainFrame().isVisible = true
        }
    }

    val exitAction = ExitAction(this)

    lateinit private var contentTable: JTable

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        jMenuBar = MainFrameMenuBar(this)

        add(MainForm())
        size = Dimension(500, 500)
    }
}