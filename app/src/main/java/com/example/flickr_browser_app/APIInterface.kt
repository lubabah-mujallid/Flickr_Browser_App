package com.example.flickr_browser_app

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("?method=flickr.photos.search&api_key=8954c85da90eff4e7cb1d3a128311f68&tags=disney&per_page=20&format=json&nojsoncallback=1")
    fun getAdvice(): Call<FlickrImages>
}