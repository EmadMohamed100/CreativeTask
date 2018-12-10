package com.virtualblock.virtuwallet.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virtualblock.virtuwallet.presentation.Event


/**
 * Authored by Mohamed Fathy on 10 Dec, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
class MainViewModel : ViewModel() {

  private val _buttonClick = MutableLiveData<Event<Unit>>()

  val buttonClick: LiveData<Event<Unit>>
    get() = _buttonClick

  var text = MutableLiveData<String>()

  fun onButtonClicked() {
    _buttonClick.value = Event(Unit)
  }
}