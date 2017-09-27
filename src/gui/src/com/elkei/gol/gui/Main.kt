/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui

import com.elkei.gol.gui.frames.main.MainFrame
import javax.swing.UIManager


fun main(args: Array<String>) {
    setNimbusLookAndFeel()
    MainFrame().isVisible = true
}

private fun setNimbusLookAndFeel() {
    try {
        for (info in UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus" == info.name) {
                UIManager.setLookAndFeel(info.className)
                break
            }
        }
    } catch (e: Exception) {
        //well, then just use the default look and feel
    }
}