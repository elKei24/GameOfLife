import javax.swing.JFrame

class MainForm : JFrame(GuiResources.default.getMainFrameTitle()) {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            MainForm().isVisible = true
        }
    }

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
    }
}
