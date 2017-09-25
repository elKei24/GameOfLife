package com.elkei.gol.gui.actions

import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.Icon
import javax.swing.KeyStroke

open class CustomizableAction(name: String? = null, icon: Icon? = null, shortDescription: String? = null,
                              accelerator: KeyStroke? = null, mnemonic: Int? = null,
                              actionCommand: String? = null, longDescription: String? = null,
                              enabled: Boolean = true) : AbstractAction(name, icon) {

    var name: String?
    get() = getValue(Action.NAME) as String?
    set(value) = putValue(Action.NAME, value)

    var icon: Icon?
        get() = getValue(Action.SMALL_ICON) as Icon?
        set(value) = putValue(Action.SMALL_ICON, value)

    var shortDescription: String?
        get() = getValue(Action.SHORT_DESCRIPTION) as String?
        set(value) = putValue(Action.SHORT_DESCRIPTION, value)

    var accelerator: KeyStroke?
        get() = getValue(Action.ACCELERATOR_KEY) as KeyStroke?
        set(value) = putValue(Action.ACCELERATOR_KEY, value)

    var mnemonic: Int?
        get() = getValue(Action.MNEMONIC_KEY) as Int?
        set(value) = putValue(Action.MNEMONIC_KEY, value)

    var actionCommand: String?
        get() = getValue(Action.ACTION_COMMAND_KEY) as String?
        set(value) = putValue(Action.ACTION_COMMAND_KEY, value)

    var longDescription: String?
        get() = getValue(Action.LONG_DESCRIPTION) as String?
        set(value) = putValue(Action.LONG_DESCRIPTION, value)

    init {
        this.shortDescription = shortDescription
        this.accelerator = accelerator
        this.mnemonic = mnemonic
        this.actionCommand = actionCommand
        this.longDescription = longDescription
        this.enabled = enabled
    }

    override fun actionPerformed(actionEvent: ActionEvent) {
        //default implementation, do nothing
    }
}