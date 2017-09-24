package actions

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
        this.mnemoric = actionResources.getActionMnemoric(key)
        this.accelerator = accelerator
        this.actionCommand = actionCommand
    }
}