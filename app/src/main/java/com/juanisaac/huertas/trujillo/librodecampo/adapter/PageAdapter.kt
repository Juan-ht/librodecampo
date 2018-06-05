package com.juanisaac.huertas.trujillo.librodecampo.adapter

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.juanisaac.huertas.trujillo.librodecampo.fragment.FormFragment
import com.juanisaac.huertas.trujillo.librodecampo.fragment.ListDaysFragment
import com.juanisaac.huertas.trujillo.librodecampo.fragment.WeatherFragment


class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ListDaysFragment()
            }
            1 ->
                FormFragment()

            else -> {
                return WeatherFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Lista de días"
            1 -> "Añadir días"
            else -> {
                return "Tiempo"
            }
        }
    }
}