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

    /**
     * Tries to find the index of the first proper mnemonic escape char (`&`) according to
     * [MnemonicFinder.isProperMnemonicEscapeIndex]. If no is found, null will be returned.
     *
     * @return index of first proper escape char, or null if there is no
     */
    private fun findMnemonicEscapeIndex(): Int? = findMnemonicEscapeIndex(0)

    /**
     * Tries to find the index of the first proper mnemonic escape char (`&`) according to
     * [MnemonicFinder.isProperMnemonicEscapeIndex] at or after the given index.
     * If no is found, null will be returned.
     *
     * This function is implemented using tail recursion and will so be converted to a regular loop by the compiler.
     *
     * @param startIndex the minimum of the returned index
     * @return index of first proper escape char after or at [startIndex], or null if there is no
     */
    private tailrec fun findMnemonicEscapeIndex(startIndex: Int): Int? {
        val index = text.indexOf(MNEMONIC_ESCAPE_CHAR, startIndex)

        return if (index < 0) null
        else if (!isProperMnemonicEscapeIndex(index)) findMnemonicEscapeIndex(index + 1)
        else index
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