package me.tylerbwong.allaboard.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateAndAttach(@LayoutRes resource: Int): View = LayoutInflater.from(context)
        .inflate(resource, this)

fun ViewGroup.justInflate(@LayoutRes resource: Int): View = LayoutInflater.from(context)
        .inflate(resource, this, false)
