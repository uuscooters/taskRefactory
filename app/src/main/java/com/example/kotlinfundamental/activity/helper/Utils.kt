package com.example.kotlinfundamental.activity.helper

import android.content.Context
import android.widget.Toast

class Utils {

    companion object{
        const val ID_CONTACT = "idContact"

        //Menampilkan Toast yg dipanggil secara universal
        fun showToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}