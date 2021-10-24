package com.example.flickr_browser_app

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickr_browser_app.databinding.RecylcerLayoutBinding

class Recycler(val messages: ArrayList<Image>, val context: Context): RecyclerView.Adapter<Recycler.ViewHolder>() {
    class ViewHolder(val binding: RecylcerLayoutBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecylcerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        holder.binding.apply {
            //tvRecyler.text = message
            Glide.with(cv).load(message.link).into(rvImageView)
            rvTextView.text = message.title
            //add move to new activity for big pic
            cv.setOnClickListener {
                val intent = Intent(context, MainActivity2::class.java)
                intent.putExtra("title", message.title)
                intent.putExtra("link", message.link)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = messages.size
}