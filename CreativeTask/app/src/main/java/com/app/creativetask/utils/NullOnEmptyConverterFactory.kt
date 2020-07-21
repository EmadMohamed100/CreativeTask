package com.app.kotlintrends.data.api

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


/**
 * Created by Emad Mohamed Oun on 7/20/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

class NullOnEmptyConverterFactory : Converter.Factory() {


    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val delegate = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)

        return Converter<ResponseBody, Any> { body ->
            if (body.contentLength() == 0L) null
            else delegate.convert(body)
        }
    }
}