package com.juanisaac.huertas.trujillo.librodecampo.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.juanisaac.huertas.trujillo.librodecampo.R
import kotlinx.android.synthetic.main.fragment_form.*
import com.juanisaac.huertas.trujillo.database.AppDataBase
import com.juanisaac.huertas.trujillo.librodecampo.listener.CallBackSaveData
import com.juanisaac.huertas.trujillo.librodecampo.managers.SaveDataTask
import com.juanisaac.huertas.trujillo.librodecampo.model.JornalModel
import java.util.*


class FormFragment : Fragment() {

    lateinit var mDatabase: DatabaseReference
    lateinit var mJornalList: ArrayList<JornalModel>
    lateinit var saveDataTask: SaveDataTask
    lateinit var appDataBase:AppDataBase

    companion object {
        val JORNAL_LIST = "JORNAL_LIST"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mJornalList = ArrayList()
        appDataBase = AppDataBase.getInstance(activity?.applicationContext!!)!!
        saveDataTask = SaveDataTask(activity?.applicationContext!!, appDataBase)
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        btn_anadir_jornal.setOnClickListener {
            getJornal()

        }
    }

    override fun onStart() {
        super.onStart()
        btn_anadir_jornal.setOnClickListener {
            getJornal()

        }
        //getData()
    }

    fun getJornal() {

        var jornal: JornalModel
        if (btn_anadir_jornal != null && edit_tipo_jornal != null && edit_nombre != null && edit_tipo_actividad != null && edit_parcela != null
                 && edit_valor_jornal != null ) {
            var tipoJornal = edit_tipo_jornal.text.toString()
            var nombreTrabajador = edit_nombre.text.toString()
            var tipoActividad = edit_tipo_actividad.text.toString()
            var parcela = edit_parcela.text.toString()
            var valorJornal = edit_valor_jornal.text.toString()
            jornal = JornalModel(tipoJornal, nombreTrabajador, tipoActividad, parcela, Date(), valorJornal)
            mJornalList.add(jornal)
            saveDataTask.saveJornals(jornal,appDataBase)
            cleanTextView()
            Log.d("lista", "Jornales: $mJornalList")
            mDatabase = FirebaseDatabase.getInstance().getReference("jornales")
            mDatabase.child("jornal").setValue(mJornalList)

        }
    }

    private fun cleanTextView() {
        edit_tipo_jornal.setText("")
        edit_nombre.setText("")
        edit_tipo_actividad.setText("")
        edit_parcela.setText("")
        edit_valor_jornal.setText("")
    }

    /*fun getData() {
        mDatabase = FirebaseDatabase.getInstance().getReference("jornales")
        val jornalListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var gson = Gson()
                var jornal:String = dataSnapshot.value.toString()
                val collectionType = object : TypeToken<JornalModel>() {

                }.type
                var jsonArray = gson.toJson(dataSnapshot.value)
                val enums = gson.fromJson(jsonArray, JornalModel::class.java)
                Log.d("hijos", "${enums}")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        mDatabase.addValueEventListener(jornalListener)
        Log.d("FormFragment", "${mJornalList}")

    }
*/
}