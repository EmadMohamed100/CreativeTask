package com.virtualblock.virtuwallet.presentation

/**
 * Created by Mohamed Fathy on 20 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 *
 * From https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Event<out T>(private val content: T) {

  var hasBeenHandled = false
    private set // Allow external read but not write

  /**
   * Returns the content and prevents its use again.
   */
  fun getContentIfNotHandled(): T? {
    return if (hasBeenHandled) {
      null
    } else {
      hasBeenHandled = true
      content
    }
  }

  /**
   * Returns the content, even if it's already been handled.
   */
  fun peekContent(): T = content
}
