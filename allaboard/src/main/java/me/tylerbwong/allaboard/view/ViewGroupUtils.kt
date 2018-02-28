package me.tylerbwong.allaboard.view

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflateAndAttach(@LayoutRes resource: Int): View = LayoutInflater.from(context)
        .inflate(resource, this)

fun ViewGroup.justInflate(@LayoutRes resource: Int): View = LayoutInflater.from(context)
        .inflate(resource, this, false)
