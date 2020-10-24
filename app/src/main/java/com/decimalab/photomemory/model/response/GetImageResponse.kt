package com.decimalab.photomemory.model.response

import com.decimalab.photomemory.model.Image

/**
 * Created by Shakil Ahmed Shaj on 24,October,2020.
 * shakilahmedshaj@gmail.com
 */

data class GetImagesResponse(val images: List<Image> = listOf())