package com.juanisaac.huertas.trujillo.librodecampo.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.adapter.PageAdapter
import kotlinx.android.synthetic.main.main_activity.*

class TabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        val fragmentAdapter = PageAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }
}
