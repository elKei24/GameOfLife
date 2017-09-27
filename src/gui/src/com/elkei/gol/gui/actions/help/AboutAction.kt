/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.actions.help

import com.elkei.gol.gui.actions.ResourceAction
import com.elkei.gol.gui.frames.about.AboutDialog
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class AboutAction(private val parent: Frame) :
        ResourceAction("help.about", accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0)) {

    override fun actionPerformed(actionEvent: ActionEvent) {
        AboutDialog(parent).isVisible = true
    }
}