package no.kristiania.myapp.api

interface ItemList {

    fun onSucsess(newItem: MutableList<Any>?)

    fun onError()
}