/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.actions.file

import com.elkei.gol.gui.actions.ResourceAction
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

    override fun actionPerformed(actionEvent: ActionEvent) {
        window.dispose()
    }

}