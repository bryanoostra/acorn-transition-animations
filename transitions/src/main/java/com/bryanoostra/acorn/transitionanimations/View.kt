package com.bryanoostra.acorn.transitionanimations

import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.AnyRes
import androidx.annotation.ColorInt

internal inline fun View.doOnPreDraw(crossinline f: () -> Unit) {
    val viewTreeObserver = viewTreeObserver
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            f()
            when {
                viewTreeObserver.isAlive -> viewTreeObserver.removeOnPreDrawListener(this)
                else -> this@doOnPreDraw.viewTreeObserver.removeOnPreDrawListener(this)
            }
            return true
        }
    })
}

internal fun View.applyWindowBackground() {
    val value = TypedValue().also {
        context.theme.resolveAttribute(android.R.attr.windowBackground, it, true)
    }

    when {
        value.type == TypedValue.TYPE_NULL -> background = null
        value.type == TypedValue.TYPE_REFERENCE -> applyBackgroundReference(value.resourceId)
        value.type == TypedValue.TYPE_STRING -> applyBackgroundReference(value.resourceId)
        value.type in TypedValue.TYPE_FIRST_COLOR_INT..TypedValue.TYPE_LAST_COLOR_INT -> applyBackgroundColor(value.data)
        else -> error("Unknown type: 0x${Integer.toHexString(value.type)}")
    }.let { /* Safe when let */ }
}

private fun View.applyBackgroundReference(@AnyRes resourceId: Int) {
    return when (resourceId) {
        0 -> background = null
        else -> setBackgroundResource(resourceId)
    }
}

private fun View.applyBackgroundColor(@ColorInt value: Int) {
    setBackgroundColor(value)
}
