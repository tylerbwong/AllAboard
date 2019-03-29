package me.tylerbwong.allaboard.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

internal fun ViewGroup.inflateAndAttach(@LayoutRes resource: Int): View = LayoutInflater.from(context)
        .inflate(resource, this)

internal fun ViewGroup.justInflate(@LayoutRes resource: Int): View = LayoutInflater.from(context)
        .inflate(resource, this, false)
