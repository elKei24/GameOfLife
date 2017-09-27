/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.actions.res

import com.elkei.gol.gui.util.MnemonicFinder
import java.awt.event.KeyEvent
import java.util.*

class ActionResources(locale: Locale = Locale.getDefault()) {
    companion object {
        private const val BUNDLE_FILE = "com/elkei/gol/gui/actions/res/GuiActions"
        private const val MNEMORIC_SUFFIX = ".mnem"
        private const val SHORT_DESCRIPTION_SUFFIX = ".short"
        private const val LONG_DESCRIPTION_SUFFIX = ".long"

        val default = ActionResources()
    }
    private val bundle: ResourceBundle = ResourceBundle.getBundle(BUNDLE_FILE, locale)

    fun getActionName(actionKey: String): String {
        val actionNameWithMnem = getActionNameWithMnemCharOrNull(actionKey)
        return if (actionNameWithMnem == null) actionKey else MnemonicFinder(actionNameWithMnem).getWithoutEscapeChar()
    }
    private fun getActionNameWithMnemCharOrNull(actionKey: String): String? = bundle.getStringOrNull(getNameKey(actionKey))
    private fun getNameKey(actionKey: String): String = actionKey

    fun getActionMnemonic(actionKey: String): Int? {
        val extraKeyMnemChar = bundle.getStringOrNull(getMnemonicKey(actionKey))?.takeUnless(String::isEmpty)?.get(0)
        val mnemChar = extraKeyMnemChar ?: {
            val actionNameWithMnemChar = getActionNameWithMnemCharOrNull(actionKey)
            if (actionNameWithMnemChar == null) null else MnemonicFinder(actionNameWithMnemChar).getMnemonicChar()
        }.invoke()
        return if (mnemChar != null) KeyEvent.getExtendedKeyCodeForChar(mnemChar.toInt()) else null
    }
    private fun getMnemonicKey(actionKey: String): String = actionKey + MNEMORIC_SUFFIX

    fun getActionShortDescription(actionKey: String): String? = bundle.getStringOrNull(getShortDescriptionKey(actionKey))
    private fun getShortDescriptionKey(actionKey: String): String = actionKey + SHORT_DESCRIPTION_SUFFIX

    fun getActionLongDescription(actionKey: String): String? = bundle.getStringOrNull(getLongDescriptionKey(actionKey))
    private fun getLongDescriptionKey(actionKey: String): String = actionKey + LONG_DESCRIPTION_SUFFIX

    private fun ResourceBundle.getStringOrNull(key: String) : String? {
        return try {
            getString(key)
        } catch (mre: MissingResourceException) {
            null
        }
    }
}