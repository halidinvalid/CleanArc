package com.hx.codecase.presentation.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hx.codecase.R
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@SuppressLint("WrongConstant")
fun RecyclerView.setup(
    layoutManager: LinearLayoutManager,
    orientation: Int = LinearLayoutManager.VERTICAL,
    adapter: RecyclerView.Adapter<*>? = null
) {
    layoutManager.orientation = orientation
    this.layoutManager = layoutManager
    this.setHasFixedSize(false)
    adapter?.let {
        this.adapter = adapter
    }
}


fun String.loadImage(
    context: Context?,
    networkPolicy: NetworkPolicy = NetworkPolicy.OFFLINE,
    imageView: ImageView?
) {
    Picasso.with(context)
        .load(this)
        .networkPolicy(networkPolicy)
        .placeholder(R.drawable.ic_place_holder)
        .into(imageView, object : Callback {
            override fun onSuccess() {}

            override fun onError() {
                Picasso.with(context)
                    .load(this@loadImage)
                    .placeholder(R.drawable.ic_place_holder)
                    .into(imageView)
            }
        })
}

@SuppressLint("NewApi")
fun String.formatDate(): String {
    val timeZone = ZonedDateTime.ofInstant(
        Instant.parse(this),
        ZoneId.of("Europe/Istanbul")
    )
    return timeZone.dayOfMonth.toString() + "-" + timeZone.monthValue + "-" + timeZone.year
}

inline fun <reified T : Any> Context.launchActivity(
    bundle: Bundle? = null,
    init: Intent.() -> Unit
) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent, bundle)
}

