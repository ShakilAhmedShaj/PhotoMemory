package com.decimalab.photomemory.networking

import com.decimalab.photomemory.model.Image
import com.decimalab.photomemory.model.result.Failure
import com.decimalab.photomemory.model.result.Result
import com.decimalab.photomemory.model.result.Success

/**
 * Holds decoupled logic for all the API calls.
 */

class RemoteApi(private val apiService: RemoteApiService) {

    suspend fun getImages(): Result<List<Image>> = try {
        val data = apiService.getImages().images

        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }
}