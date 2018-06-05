package com.juanisaac.huertas.trujillo.librodecampo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.model.Jornal
import kotlinx.android.synthetic.main.fragment_form.*
import com.google.firebase.database.DataSnapshot




class FormFragment : Fragment() {

    lateinit var mDatabase: DatabaseReference
    lateinit var mJornalList: ArrayList<Jornal>

    companion object {
        val JORNAL_LIST = "JORNAL_LIST"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mJornalList = ArrayList()
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onStart() {
        super.onStart()
        btn_anadir_jornal.setOnClickListener {
            getJornal()
        }
//        getDataFromFirebase()
    }

    fun getJornal() {
        var jornal: Jornal
        if (btn_anadir_jornal != null && edit_tipo_jornal != null && edit_nombre != null && edit_tipo_actividad != null && edit_parcela != null
                && edit_dia != null && edit_valor_jornal != null) {
            var tipoJornal = edit_tipo_jornal.text.toString()
            var nombreTrabajador = edit_nombre.text.toString()
            var tipoActividad = edit_tipo_actividad.text.toString()
            var parcela = edit_parcela.text.toString()
            var dia = edit_dia.text.toString()
            var valorJornal = edit_valor_jornal.text.toString()
            jornal = Jornal(tipoJornal, nombreTrabajador,
                    tipoActividad,
                    parcela, dia, valorJornal)
            mJornalList.add(jornal)
            Log.d("lista", "Jornales: $mJornalList")
            mDatabase = FirebaseDatabase.getInstance().getReference("jornales")
            val jornalID: String = mDatabase.push().getKey().toString()
            mDatabase.child(jornalID).setValue(mJornalList)
            val jornalListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var list:ArrayList<String> = ArrayList()
                    for (postSnapshot in dataSnapshot.children) {
                        list.add(postSnapshot.value.toString())
                    }
                    Log.d("datos", list.toString())
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("loadPost:onCancelled ${databaseError.toException()}")
                }
            }


        }
    }

}