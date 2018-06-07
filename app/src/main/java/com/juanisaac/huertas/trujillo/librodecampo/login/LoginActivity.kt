package com.juanisaac.huertas.trujillo.librodecampo.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.juanisaac.huertas.trujillo.librodecampo.views.TabActivity
import com.juanisaac.huertas.trujillo.librodecampo.R

import kotlinx.android.synthetic.main.login_activity.*


class LoginActivity : AppCompatActivity() {

    val TAG: String = "LoginActivity"
    private var mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        btn_login.setOnClickListener({
            singIn(edit_email.text.toString(), edit_password.text.toString())
        })

    }


    fun singIn(email:String, password:String){
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){
                var intent = Intent(this, TabActivity::class.java)
                startActivity(intent)

            }else{
                toastMessage("Error: ${task.exception?.message}")
            }
        })

    }

    fun toastMessage(message: String) = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}