package com.juanisaac.huertas.trujillo.librodecampo.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.juanisaac.huertas.trujillo.database.tables.Jornal
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.extensions.inflate
import com.juanisaac.huertas.trujillo.librodecampo.model.JornalModel

class JornalAdapter(val dataSource: ArrayList<Jornal>) : BaseAdapter() {
    override fun getView(position: Int, currentView: View?, parentView: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (currentView == null) {
            view = parentView.inflate(R.layout.item_jornal)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = currentView.tag as ViewHolder
            view = currentView
        }
        val jornal = dataSource[position]
        viewHolder.apply {
            dia.text = "Dia: ${jornal.dia}"
            nombre.text = "Nombre: ${jornal.nombreTrabajador}"
            parcela.text = "Parcela: ${jornal.parcela}"
            tipoJornal.text = "Tipo jornal: ${jornal.tipoJornal}"
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private class ViewHolder(view: View) {
        val dia: TextView = view.findViewById(R.id.dia_item)
        val nombre: TextView = view.findViewById(R.id.nombre_item)
        val parcela: TextView = view.findViewById(R.id.parcela_item)
        val tipoJornal: TextView = view.findViewById(R.id.tipo_jornal_item)

    }
}