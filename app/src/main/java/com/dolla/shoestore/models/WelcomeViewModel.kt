package com.dolla.shoestore.models


import androidx.lifecycle.ViewModel
import com.dolla.shoestore.R
import timber.log.Timber

class WelcomeViewModel : ViewModel() {

    var clickNextCounter: Int = 0
    var messageList: List<Int>
    var photosList: List<Int>

    init {
        // Executed when the class is instantiated
        Timber.i("WelcomeViewModel created!")

        messageList = listOf(
            R.string.msg_info_1,
            R.string.msg_info_2,
            R.string.msg_info_3
        )

        photosList = listOf(
            R.drawable.shoe1,
            R.drawable.shoe2,
            R.drawable.shoe3
        )

    }

    override fun onCleared() {      // Executed before destroying the fragment
        super.onCleared()
        Timber.i("WelcomeViewModel destroyed!")
        clickNextCounter = 0
    }

}
