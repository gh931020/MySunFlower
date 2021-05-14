package com.peninsula.mysunflower.data

import com.google.gson.annotations.SerializedName

/**
 * 只需要显示需要的字段
 * For a full list of fields, consult the API documentation
 * [here](https://unsplash.com/documentation#get-a-photo).
 * @property id String
 * @property urls urls
 * @property user user
 * @constructor
 */
data class UnsplashPhoto(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("urls") val urls: UnsplashPhotoUrls,
    @field:SerializedName("user") val user: UnsplashUser
)