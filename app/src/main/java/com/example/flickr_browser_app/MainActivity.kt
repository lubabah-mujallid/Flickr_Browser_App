package com.example.flickr_browser_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr_browser_app.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    private lateinit var messages: ArrayList<Image>
    private lateinit var adapter: Recycler
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeBinding()
        initializeRecycler()
        requestAPI()

    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeRecycler() {
        messages = ArrayList()
        adapter = Recycler(messages,this)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
    }


    //https://live.staticflickr.com/65535/51621107532_320a85d6bf_s.jpg
//https://live.staticflickr.com/{server-id}/{id}_{secret}_{size-suffix}.jpg
    private fun requestAPI() {
        CoroutineScope(IO).launch {
            Log.d("MAIN", "fetch advice")
            val flickrImages = async { fetchAdvice() }.await()
            if (flickrImages?.stat == "ok") {
                updateTextView(flickrImages)
            } else {
                Log.d("MAIN", "Unable to get data")
            }
        }
    }

    private fun fetchAdvice(): FlickrImages? {
        Log.d("MAIN", "went inside fetch")
        val apiInterface = APIClient.getClient()?.create(APIInterface::class.java)
        val call: Call<FlickrImages> = apiInterface!!.getAdvice()
        var flickrImages: FlickrImages? = null

        try {
            val response = call.execute()
            flickrImages = response.body()
            //advice = response.body()?.slip?.advice.toString()
            //Log.d("MAIN", "read flickr $flickrImages")
        } catch (e: Exception) {
            Log.d("MAIN", "ISSUE: $e")
        }

        Log.d("MAIN", "flickr $flickrImages")
        return flickrImages
    }

    private suspend fun updateTextView(flickrImages: FlickrImages) {
        withContext(Main) {
            Log.d("MAIN", "updating RV")
            for (i in flickrImages.photos.photo) {
                val url = "https://live.staticflickr.com/${i.server}/${i.id}_${i.secret}_q.jpg"
                val title = if(i.title.isBlank()) "no title" else i.title
                messages.add(Image(title,url))
                adapter.notifyDataSetChanged()
            }
            //https://live.staticflickr.com/{server-id}/{id}_{secret}_{size-suffix}.jpg
            //tvAdvice.text = advice
        }
    }
}
// add final version of Recycler view
// add binding fun
// add final version of retrofit singleton with coroutines
// add glide library

/*
- retrofit singleton with coroutines
- add api
- api class
- rv

 */


/*
Create a Flickr application that does the following:
- Makes us of Flickr API to fetch photos based on a search
- Makes use of Glide to display photos
- Displays photo thumbnails in a Recycler View along with their title
- Allows users to view large versions of the thumbnails by tapping on them

You will need to do your own research regarding the Flickr API. You will have to sign up to get your API Key, read the API docs to learn how to use the API, learn how to pass in the correct parameters, convert the output to JSON, and more.
You can find the API here: https://www.flickr.com/services/developer/api/


* */