package me.tylerbwong.allaboard

class Onboarding {
    internal var showIndicator: Boolean = true
    internal var showSkip: Boolean = true
    private val pages: MutableList<Page> = mutableListOf()

    fun addPage(page: Page) = pages.add(page)
}