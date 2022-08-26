package com.dolla.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

enum class SaveState {
    YES,
    NO
}

class ShoeViewModel : ViewModel() {

    // Shoe List
    private val _shoeItem = MutableLiveData<MutableList<Shoe>>()
    val shoeItem: LiveData<MutableList<Shoe>>
        get() = _shoeItem

    // Shoe Save State
    private var _saveState = MutableLiveData<SaveState>()
    val saveState: LiveData<SaveState>
        get() = _saveState


    init {
        // Executed when the class is instantiated
        Timber.i("ShoeListViewModel created!")

        _shoeItem.value = mutableListOf()
        // Put 1 item for example
        addShoe(
            "Air Max Archives",
            40.5,
            "Nike",
            "Men's Nike Air Max 2017 Cool Dark Grey"
        )
        _saveState.value = SaveState.NO

    }

    fun onEventSave(name: String, size: String, company: String, description: String) {
        var sizeDouble = 0.0
        try {
            sizeDouble = size.toDouble()
        } catch (e: NumberFormatException) {
            Timber.i("Invalid size entered")
        }

        addShoe(name, sizeDouble, company, description)
        _saveState.value = SaveState.YES
    }

    fun onEventSaveComplete() {
        _saveState.value = SaveState.NO
    }

    private fun addShoe(name: String, size: Double, company: String, description: String) {
        _shoeItem.value?.add(Shoe(name, size, company, description))

    }

}