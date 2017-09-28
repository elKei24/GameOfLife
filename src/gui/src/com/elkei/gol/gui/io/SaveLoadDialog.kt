/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.io

import com.elkei.gol.gui.FILE_EXTENSION
import com.elkei.gol.gui.MAXIMUM_BOARD_SIZE_X
import com.elkei.gol.gui.MAXIMUM_BOARD_SIZE_Y
import com.elkei.gol.gui.res.GuiResources
import com.elkei.gol.model.Board
import com.elkei.gol.model.Coordinate
import com.elkei.gol.model.util.BoardReader
import com.elkei.gol.model.util.BoardWriter
import java.awt.Window
import java.util.logging.Level
import java.util.logging.Logger
import javax.swing.JFileChooser
import javax.swing.JOptionPane
import javax.swing.filechooser.FileNameExtensionFilter

class SaveLoadDialog(private val parent: Window) : JFileChooser() {
    companion object {
        private val logger = Logger.getLogger(SaveLoadDialog::class.qualifiedName)
    }

    init {
        setFilters()
        isMultiSelectionEnabled = false
    }

    private fun setFilters() {
        val golFilter = FileNameExtensionFilter(GuiResources.default.getStringOrKey(GuiResources.FILE_FILTER_KEY), FILE_EXTENSION)
        addChoosableFileFilter(golFilter)
        fileFilter = golFilter
    }

    fun saveBoard(board: Board) {
        if (showSaveDialog(parent) != APPROVE_OPTION) return
        val file = selectedFile

        try {
            BoardWriter.write(file.outputStream(), board)
        } catch (e: Exception) {
            logger.log(Level.INFO, "Failed to save board", e)
            showErrorMessage(GuiResources.default.getStringOrKey(GuiResources.FILE_SAVE_ERROR_GENERAL_KEY),
                    GuiResources.default.getStringOrKey(GuiResources.FILE_SAVE_ERROR_TITLE_KEY))
        }
    }

    fun loadBoard(): Board? {
        if (showOpenDialog(parent) != APPROVE_OPTION) return null
        val file = selectedFile
        return try {
            BoardReader.read(file.inputStream(), Coordinate(MAXIMUM_BOARD_SIZE_X, MAXIMUM_BOARD_SIZE_Y))
        } catch (e: BoardReader.BoardFileFormatException) {
            showErrorMessage(GuiResources.default.getFormattedStringOrKey(GuiResources.FILE_LOAD_ERROR_FORMAT_KEY, e.localizedMessage),
                    GuiResources.default.getStringOrKey(GuiResources.FILE_LOAD_ERROR_TITLE_KEY))
            null
        } catch (e: Exception) {
            logger.log(Level.INFO, "Failed to load board", e)
            showErrorMessage(GuiResources.default.getStringOrKey(GuiResources.FILE_LOAD_ERROR_GENERAL_KEY),
                    GuiResources.default.getStringOrKey(GuiResources.FILE_LOAD_ERROR_TITLE_KEY))
            null
        }
    }

    private fun showErrorMessage(message: String, title: String) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE)
    }

}