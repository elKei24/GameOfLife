package com.elkei.gol.gui.res

import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

class GuiResources(locale: Locale = Locale.getDefault()) {
    companion object {
        private const val GUI_TEXTS_FILE = "com/elkei/gol/gui/res/GuiTexts"
        private const val GUI_TEXT_MAINTITLE = "MainFrame.title"

        val default = GuiResources()
        private val logger = Logger.getLogger(GuiResources::class.qualifiedName)
    }
    private val texts: ResourceBundle = ResourceBundle.getBundle(GUI_TEXTS_FILE, locale)

    fun getMainFrameTitle(): String {
        return texts.getStringSafe(GUI_TEXT_MAINTITLE)
    }

    private fun ResourceBundle.getStringSafe(key: String) : String {
        return try {
            getString(key)
        } catch (mre: MissingResourceException) {
            logger.log(Level.WARNING, "Missing string \"$key\" in resource \"${this.baseBundleName}\"", mre)
            key
        }
    }
}