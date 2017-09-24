package actions

import java.awt.Window
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

/**
 * An action that disposes a given window when triggered.
 *
 * @property window the window that will be disposed
 * @constructor constructs a new exit action that will close the given window when triggered
 */
class ExitAction(private val window: Window) :
        ResourceAction("file.exit", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK)) {

    override fun actionPerformed(p0: ActionEvent?) {
        window.dispose()
    }

}