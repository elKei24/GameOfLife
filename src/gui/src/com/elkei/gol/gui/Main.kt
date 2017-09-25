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