package com.hamed.common.presentation.properties

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T,
    beforeDestroyCallback: (T.() -> Unit)? = null
) = ViewBindingProperty(this, viewBindingFactory, beforeDestroyCallback)
