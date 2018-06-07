package com.juanisaac.huertas.trujillo.librodecampo.extensions

import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.json.JSONArray
import org.json.JSONObject

operator fun JSONArray.iterator():Iterator<JSONObject> =
        (0 until length()).asSequence().map { get(it) as JSONObject }.iterator()

fun ViewGroup.inflate(resource:Int):View
        = LayoutInflater.from(context).inflate(resource, this,false)



fun View.displaySnak(message:String, length:Int = Snackbar.LENGTH_LONG, func:Snackbar.() -> Unit){

    val snackbar:Snackbar = Snackbar.make(this, message, length)
    snackbar.func()
    snackbar.show()
}
fun Snackbar.action(message:String, listener: (View) -> Unit){
    setAction(message, listener)
}