package com.juanisaac.huertas.trujillo.librodecampo.fragment

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.juanisaac.huertas.trujillo.database.AppDataBase
import com.juanisaac.huertas.trujillo.database.tables.Jornal
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.Repository
import com.juanisaac.huertas.trujillo.librodecampo.managers.SaveDataTask
import kotlinx.android.synthetic.main.fragment_form.*
import java.text.SimpleDateFormat
import java.util.*


class FormFragment : Fragment() {

    lateinit var mDatabase: DatabaseReference
    lateinit var mJornalList: ArrayList<Jornal>
    lateinit var saveDataTask: SaveDataTask
    lateinit var appDataBase: AppDataBase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mJornalList = ArrayList()
        appDataBase = AppDataBase.getInstance(activity?.applicationContext!!)!!
        saveDataTask = SaveDataTask(activity?.applicationContext!!, appDataBase)

        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val tipoJornales = arrayOf("Cuenta Ajena", "Cuenta propia")
        spinner_tipo_jornal.adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, tipoJornales)
        spinner_tipo_jornal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var temp = tipoJornales[position]
                tipoJornal.text = "$temp"
            }

        }
        btn_anadir_jornal.setOnClickListener {
            getJornal()

        }
    }

    override fun onStart() {
        super.onStart()
        val tipoJornales = arrayOf("Cuenta Ajena", "Cuenta Propia")
        spinner_tipo_jornal.adapter = ArrayAdapter(context!!, R.layout.spinner_item, tipoJornales)
        spinner_tipo_jornal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val temp = tipoJornales[position]
                tipoJornal.text = temp
            }

        }
        btn_anadir_jornal.setOnClickListener {
            getJornal()

        }
    }

    fun getJornal() {

        var jornal: Jornal
        if (btn_anadir_jornal != null && spinner_tipo_jornal != null && edit_nombre != null && edit_tipo_actividad != null && edit_parcela != null
                && edit_valor_jornal != null) {
            var tipoJornal:String = tipoJornal.text.toString()
            var nombreTrabajador = edit_nombre.text.toString()
            var tipoActividad = edit_tipo_actividad.text.toString()
            var parcela = edit_parcela.text.toString()
            var valorJornal = edit_valor_jornal.text.toString()
            val formater = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)
            val date = Date()
            val currentDate = formater.format(date)
            jornal = Jornal(tipoJornal, nombreTrabajador, tipoActividad, parcela, currentDate, valorJornal)
            Repository.list.add(jornal)
            saveDataTask.saveJornals(jornal, appDataBase)
            cleanTextView()
            Log.d("lista", "Jornales: ${jornal}")
            mDatabase = FirebaseDatabase.getInstance().getReference("jornales")
            mDatabase.child("jornal").push().setValue(jornal)

        }
    }

    private fun cleanTextView() {
        edit_nombre.setText("")
        edit_tipo_actividad.setText("")
        edit_parcela.setText("")
        edit_valor_jornal.setText("")
    }


}