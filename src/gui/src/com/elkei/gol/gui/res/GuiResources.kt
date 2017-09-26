package com.elkei.gol.gui.res

import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

class GuiResources(private val locale: Locale = Locale.getDefault()) {
    companion object {
        private const val GUI_TEXTS_FILE = "com/elkei/gol/gui/res/GuiTexts"
        const val MAINTITLE_KEY = "MainFrame.title"
        const val NEW_BOARD_TITLE_KEY = "NewOrResizeBoardDialog.title.new"
        const val RESIZE_BOARD_TITLE_KEY = "NewOrResizeBoardDialog.title.resize"
        const val GENERATION_LABEL = "MainFrame.status.generations"
        const val FILE_FILTER_KEY = "SaveLoadDialog.filterDescription"
        const val FILE_LOAD_ERROR_TITLE_KEY = "SaveLoadDialog.error.load.title"
        const val FILE_SAVE_ERROR_TITLE_KEY = "SaveLoadDialog.error.save.title"
        const val FILE_LOAD_ERROR_FORMAT_KEY = "SaveLoadDialog.error.load.format"
        const val FILE_LOAD_ERROR_GENERAL_KEY = "SaveLoadDialog.error.load.unknown"
        const val FILE_SAVE_ERROR_GENERAL_KEY = "SaveLoadDialog.error.save.unknown"

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

    /**
     * This function uses the string value for [key] or, if it is missing, the key itself (using [getStringOrKey])
     * and uses it as formatter together with [args] in [String.format].
     *
     * @param key the key for the desired string
     * @param args the arguments passed to [String.format] together with the obtained format
     * @return the string belonging to [key] or, if it is missing, the key itself formattet with [args]
     * @see [getStringOrKey]
     */
    fun getFormattedStringOrKey(key: String, vararg args: String): String {
        val format = getStringOrKey(key)
        return try {
            String.format(locale, format, *args)
        } catch (e: IllegalFormatException) {
            format
        }
    }

    private fun ResourceBundle.getStringOrKey(key: String) : String {
        return try {
            getString(key)
        } catch (mre: MissingResourceException) {
            logger.log(Level.WARNING, "Missing string \"$key\" in resource \"${this.baseBundleName}\"", mre)
            key
        }
    }
}