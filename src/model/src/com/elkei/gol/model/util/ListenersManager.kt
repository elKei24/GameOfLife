/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model.util

import java.util.concurrent.CopyOnWriteArrayList

/**
 * A [ListenersManager] provides basic functionality to store and manage a list of listeners.
 * A thread-safe [CopyOnWriteArrayList] is used for storage.
 *
 * @param ListenerType the type of listeners that this [ListenersManager] can manage
 */
class ListenersManager<ListenerType> : Observable<ListenerType> {
    private val listeners = CopyOnWriteArrayList<ListenerType>()

    /**
     * Adds [listener] to the listeners list. It can then be notified on certain events.
     *
     * If [listener] is already listening, it is ignored.
     *
     * @param listener the [ListenerType] that should be added
     */
    override fun addListener(listener: ListenerType) {
        listeners.add(listener)
    }

    /**
     * Removes a listener. It will no longer be notified on events.
     *
     * If [listener] is currently no listener, it will be ignored.
     *
     * @param listener the listener that should be removed
     */
    override fun removeListener(listener: ListenerType) {
        listeners.remove(listener)
    }

    /**
     * Calls [action] on every [ListenerType] held by this [ListenersManager].  The listener that
     * the function is called on will be delivered as parameter of [action].
     * Can be used to notify listeners about
     * certain events for example.
     *
     * @param action the function that will be called on every [ListenerType] held by this instance.
     */
    fun forEach(action: (ListenerType) -> Unit) {
        listeners.forEach(action)
    }
}

/**
 * An interface describing an observable that can be observed using an instance of [ListenerType].
 * Primarily for use in delegation as the function names are pretty general
 *
 * @param ListenerType type of the listeners that can listen to this [Observable]
 */
interface Observable<in ListenerType> {
    /**
     * Adds a listener to this [Observable]. It can then be notified on certain events.
     *
     * @param listener the listener that should be notified on certain events
     */
    fun addListener(listener: ListenerType)

    /**
     * Removes a listener from this [Observable]. It will no longer be notified on events.
     *
     * @param listener the listener that should no longer be notified on events
     */
    fun removeListener(listener: ListenerType)
}