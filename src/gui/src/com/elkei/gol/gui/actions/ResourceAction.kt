/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.actions

import com.elkei.gol.gui.actions.res.ActionResources
import java.util.*
import javax.swing.Icon
import javax.swing.KeyStroke

abstract class ResourceAction(key: String, locale: Locale = Locale.getDefault(), icon: Icon? = null,
                              accelerator: KeyStroke? = null, actionCommand: String? = null, enabled: Boolean = true) :
        CustomizableAction(icon = icon, enabled = enabled) {

    init {
        val actionResources = ActionResources(locale)

        this.name = actionResources.getActionName(key)
        this.shortDescription = actionResources.getActionShortDescription(key)
        this.longDescription = actionResources.getActionLongDescription(key)
        this.mnemonic = actionResources.getActionMnemonic(key)
        this.accelerator = accelerator
        this.actionCommand = actionCommand
    }

}