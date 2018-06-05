package com.juanisaac.huertas.trujillo.librodecampo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.juanisaac.huertas.trujillo.librodecampo.adapter.PageAdapter
import com.juanisaac.huertas.trujillo.librodecampo.fragment.PageFragment
import com.juanisaac.huertas.trujillo.librodecampo.login.LoginActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        val fragmentAdapter = PageAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }

    override fun onBackPressed() {

        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()

        super.onBackPressed()
    }
}
