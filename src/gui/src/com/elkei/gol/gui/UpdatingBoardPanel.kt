package com.elkei.gol.gui

import com.elkei.gol.model.UpdatingBoard
import com.elkei.gol.model.UpdatingBoardListener
import com.elkei.gol.model.util.Observable

class UpdatingBoardPanel(var updatingBoard: UpdatingBoard) : BoardPanel(updatingBoard),
        Observable<UpdatingBoardListener> by updatingBoard {
    fun startGenerationUpdates() {
        updatingBoard.startGenerationUpdates()
    }

    fun stopGenerationUpdates() {
        updatingBoard.stopGenerationUpdates()
    }
}