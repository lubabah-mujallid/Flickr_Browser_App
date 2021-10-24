package com.example.flickr_browser_app

data class Image(var title: String, var link: String)

data class FlickrImages (
    val photos: Photos,
    val stat: String
)
data class Photos (
    val page: Long,
    val pages: Long,
    val perpage: Long,
    val total: Long,
    val photo: ArrayList<Photo>
)
data class Photo (
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Long,
    val title: String,
    val ispublic: Long,
    val isfriend: Long,
    val isfamily: Long
)
