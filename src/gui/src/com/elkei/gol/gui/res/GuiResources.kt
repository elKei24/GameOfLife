package com.elkei.gol.gui.res

import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

class GuiResources(locale: Locale = Locale.getDefault()) {
    companion object {
        private const val GUI_TEXTS_FILE = "com/elkei/gol/gui/res/GuiTexts"
        const val MAINTITLE_KEY = "MainFrame.title"
        const val NEWBOARDTITLE_KEY = "NewBoardDialog.title"

        val default = GuiResources()
        private val logger = Logger.getLogger(GuiResources::class.qualifiedName)
    }
    private val strings: ResourceBundle = ResourceBundle.getBundle(GUI_TEXTS_FILE, locale)

    /**
     * This function tries to obtain the string value for this key. If it is missing, the key is returned and a log
     * message will be sent.
     *
     * @param key the key for the desired string
     * @return the string belonging to [key] or, if it is missing, the key itself
     */
    fun getStringOrKey(key: String) = strings.getStringOrKey(key)

    private fun ResourceBundle.getStringOrKey(key: String) : String {
        return try {
            getString(key)
        } catch (mre: MissingResourceException) {
            logger.log(Level.WARNING, "Missing string \"$key\" in resource \"${this.baseBundleName}\"", mre)
            key
        }
    }
}