/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.model.util

import java.util.concurrent.CopyOnWriteArrayList

class ListenersManager<Listener> : Observable<Listener> {
    private val listeners = CopyOnWriteArrayList<Listener>()

    override fun addListener(listener: Listener) {
        listeners.add(listener)
    }
    override fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun forEach(action: (Listener) -> Unit) {
        listeners.forEach(action)
    }
}

interface Observable<in Listener> {
    fun addListener(listener: Listener)
    fun removeListener(listener: Listener)
}