package com.decimalab.photomemory.networking

import com.decimalab.photomemory.model.response.GetImagesResponse
import retrofit2.http.GET

/**
 * Api service to build Retrofit-powered API calls.
 */
interface RemoteApiService {

    @GET("/images")
    suspend fun getImages(): GetImagesResponse
}