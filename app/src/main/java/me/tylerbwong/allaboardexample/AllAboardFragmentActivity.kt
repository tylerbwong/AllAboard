package me.tylerbwong.allaboardexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class AllAboardFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_aboard)

        supportFragmentManager.beginTransaction()
                .add(R.id.container, AllAboardFragment.newInstance())
                .commit()
    }
}