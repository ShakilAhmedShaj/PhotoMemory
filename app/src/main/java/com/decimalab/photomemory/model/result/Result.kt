package com.decimalab.photomemory.model.result

/**
 * Created by Shakil Ahmed Shaj on 24,October,2020.
 * shakilahmedshaj@gmail.com
 */
sealed class Result<out T : Any>

data class Success<out T : Any>(val data: T) : Result<T>()

data class Failure(val error: Throwable?) : Result<Nothing>()