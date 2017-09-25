package com.elkei.gol.gui.util

/**
 * Helper class to find or extract mnemonics of strings. The mnemonic must be marked by a '&' char.
 *
 * *Example*: `"Mnem&onic"`: In this case, `o` would be the mnemonic char.
 *
 *
 * @param text the text that the mnemonic char should be found of
 */
class MnemonicFinder(private val text: String) {
    companion object {
        private const val MNEMONIC_ESCAPE_CHAR = '&'
    }
    private val mnemonicEscapeIndex = lazy { findMnemonicEscapeIndex() }

    /**
     * @return the mnemonic char if a mnemonic was found, else null
     */
    fun getMnemonicChar(): Char? {
        return text[(getMnemonicIndex() ?: return null) + 1]
    }

    /**
     * @return the text without the & which indicates where the mnemonic is located
     */
    fun getWithoutEscapeChar(): String {
        val index = (mnemonicEscapeIndex.value ?: return text)
        return text.removeRange(index..index)
    }

    /**
     * @return the mnemonic char index if a mnemonic was found, else null. The & indicating the mnemonic is ignored!
     */
    fun getMnemonicIndex(): Int? {
        return mnemonicEscapeIndex.value
    }

    private fun findMnemonicEscapeIndex(): Int? {
        var index = -1
        do {
            index++
            index = text.indexOf(MNEMONIC_ESCAPE_CHAR, index)
            if (index < 0) return null
        } while (!isProperMnemonicEscapeIndex(index))
        return index
    }

    private fun isProperMnemonicEscapeIndex(index: Int) = (index >= 0 && isProperMnemonicCharAt(index + 1))

    private fun isProperMnemonicCharAt(index: Int): Boolean {
        return try {
            val mnemonicChar = text[index]
            !mnemonicChar.isWhitespace()
        } catch (ex: IndexOutOfBoundsException) {
            false
        }
    }
}