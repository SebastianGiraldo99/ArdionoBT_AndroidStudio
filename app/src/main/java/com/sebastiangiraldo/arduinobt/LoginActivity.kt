package com.sebastiangiraldo.arduinobt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var txtUser:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtUser=findViewById(R.id.txtUser)
        txtPassword=findViewById(R.id.txtPassword)
        progressBar= findViewById(R.id.progressBar2)
        auth= FirebaseAuth.getInstance()
    }
    fun forgotPassword(view:View){
        startActivity(Intent(this,ForgotPassActivity::class.java))
    }
    fun register(view:View){
        startActivity(Intent(this,RegistroActivity::class.java))
    }
    fun login(view: View){
        loginUser()
    }
    private fun loginUser(){
        val user:String = txtUser.text.toString()
        val password:String = txtPassword.text.toString()

        if(!TextUtils.isEmpty(user)&& !TextUtils.isEmpty(password)){
            progressBar.visibility=View.VISIBLE
            auth.signInWithEmailAndPassword(user, password)
                    .addOnCompleteListener(this) {
                        task ->
                        if (task.isSuccessful){
                            action()
                        }else{
                            Toast.makeText(this, "Error al ingresar", Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }
    private fun action(){
        startActivity(Intent(this,Dispositivos_BT::class.java))
    }
}
