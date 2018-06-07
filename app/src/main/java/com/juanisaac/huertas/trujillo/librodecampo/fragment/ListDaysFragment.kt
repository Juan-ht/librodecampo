package com.juanisaac.huertas.trujillo.librodecampo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juanisaac.huertas.trujillo.database.tables.Jornal
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.adapter.JornalAdapter
import com.juanisaac.huertas.trujillo.librodecampo.listener.CallBackData
import com.juanisaac.huertas.trujillo.librodecampo.managers.DBManager
import kotlinx.android.synthetic.main.fragment_list.*


class ListDaysFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)

    }

    override fun onStart() {
        super.onStart()
        setView()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        setView()
        super.onViewStateRestored(savedInstanceState)
    }

    private fun setView() {
        Thread {
            var jornalList: ArrayList<Jornal> = ArrayList()
            var dbManager = DBManager()
            var jornalAdapter:JornalAdapter
            dbManager.getListJornal(object : CallBackData {

                override fun finishAction(jornal: ArrayList<Jornal>) {
                    jornalList = jornal
                    Log.d("bbdd data", jornal.toString())
                    activity?.runOnUiThread({
                        Log.d("adapter data", jornalList.toString())
                        jornalAdapter = JornalAdapter(jornalList)
                        lista_jornales.adapter = jornalAdapter
                    })
                }

                override fun error(s: String) {
                    Log.d("error", s)
                }
            }, context!!)

        }.start()
    }


}