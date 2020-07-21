package com.app.kotlintrends.utils

import com.app.creativetask.utils.Status


/**
 * Created by Emad Mohamed Oun on 7/20/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }

}