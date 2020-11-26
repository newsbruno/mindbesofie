package com.bruno.testesofie.config

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.bruno.testesofie.R
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title

class MessageHelper {

    public fun mostrarMensagem(mensagem: String, titulo: String, positive: String, context : Context) {
        AwesomeDialog.build(context as Activity)
            .title(titulo)
            .body(mensagem)
            .onPositive(positive, buttonBackgroundColor = R.color.colorPrimaryDark) {

            }
    }

    public fun mostrarToast(mensagem: String , context : Context){
        Toast.makeText(context,mensagem, Toast.LENGTH_LONG).show()
    }

}