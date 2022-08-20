package com.hamed.common.presentation.bindings

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.hamed.common.presentation.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    url ?: return
    load(url) {
        crossfade(true)
        placeholder(R.drawable.ic_placeholder)
        transformations(CircleCropTransformation())
        scale(Scale.FILL)
      //  addHeader("Authorization", BuildConfig.API_KEY)
    }
}

@BindingAdapter("isVisible")
fun View.isVisible(result: Boolean) {
    isVisible = result
}


