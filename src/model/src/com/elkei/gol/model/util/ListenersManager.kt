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