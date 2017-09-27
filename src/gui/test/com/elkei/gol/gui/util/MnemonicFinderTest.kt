/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.util

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MnemonicFinderTest {

    @Test
    fun getMnemonicChar() {
        assertEquals('e', MnemonicFinder("T&est").getMnemonicChar())
        assertEquals('T', MnemonicFinder("&Test").getMnemonicChar())
        assertEquals('t', MnemonicFinder("Tes&t").getMnemonicChar())
        assertEquals('e', MnemonicFinder("& xxx&\tT&est&").getMnemonicChar())
        assertEquals(null, MnemonicFinder("& xxx&\tTest&").getMnemonicChar())
    }

    @Test
    fun getWithoutEscapeChar() {
        assertEquals("Test", MnemonicFinder("T&est").getWithoutEscapeChar())
        assertEquals("Test", MnemonicFinder("&Test").getWithoutEscapeChar())
        assertEquals("Test", MnemonicFinder("Tes&t").getWithoutEscapeChar())
        assertEquals("& xxx&\tTest&", MnemonicFinder("& xxx&\tT&est&").getWithoutEscapeChar())
        assertEquals("& xxx&\tTest&", MnemonicFinder("& xxx&\tTest&").getWithoutEscapeChar())
    }

    @Test
    fun getMnemonicIndex() {
        assertEquals(1, MnemonicFinder("T&est").getMnemonicIndex())
        assertEquals(0, MnemonicFinder("&Test").getMnemonicIndex())
        assertEquals(3, MnemonicFinder("Tes&t").getMnemonicIndex())
        assertEquals(8, MnemonicFinder("& xxx&\tT&est&").getMnemonicIndex())
        assertEquals(null, MnemonicFinder("& xxx&\tTest&").getMnemonicIndex())
    }
}