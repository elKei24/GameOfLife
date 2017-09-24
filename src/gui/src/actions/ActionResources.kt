package actions

import java.awt.event.KeyEvent
import java.util.*

class ActionResources(locale: Locale = Locale.getDefault()) {
    companion object {
        private const val BUNDLE_FILE = "res/GuiActions"
        private const val MNEMORIC_SUFFIX = ".mnem"
        private const val SHORT_DESCRIPTION_SUFFIX = ".short"
        private const val LONG_DESCRIPTION_SUFFIX = ".long"

        val default = ActionResources()
    }
    private val bundle: ResourceBundle = ResourceBundle.getBundle(BUNDLE_FILE, locale)

    fun getActionName(actionKey: String): String? = bundle.getStringOrNull(getNameKey(actionKey))
    private fun getNameKey(actionKey: String): String = actionKey

    fun getActionMnemoric(actionKey: String): Int? {
        val mnemChar = bundle.getStringOrNull(getMnemoricKey(actionKey))?.takeUnless(String::isEmpty)?.get(0) ?: return null
        return KeyEvent.getExtendedKeyCodeForChar(mnemChar.toInt())
    }
    private fun getMnemoricKey(actionKey: String): String = actionKey + MNEMORIC_SUFFIX

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