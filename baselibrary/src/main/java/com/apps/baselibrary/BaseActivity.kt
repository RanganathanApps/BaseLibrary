package com.apps.baselibrary

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.apps.baselibrary.R

import kotlinx.android.synthetic.main.activity_base.*
import com.androidnetworking.error.ANError
import org.json.JSONObject
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.AndroidNetworking
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.androidnetworking.common.Priority


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)


    }

    internal fun showSnackbar(view:View,msg:String){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    internal fun showTost(context:Context,msg:String){
        Toast.makeText( context,msg, Toast.LENGTH_LONG).show()
    }

    internal fun makeLog(vararg msg:String){
        if (msg.isNotEmpty()) {
            if (msg.size > 1) {
                Log.w(msg[0], msg[1])
            } else {
                Log.w("Base", msg[0])
            }
        }else{
            Log.w("Base", "empty log msg!")
        }
    }


    protected fun makePostRequest(msg:String){
        AndroidNetworking.post("https://fierce-cove-29863.herokuapp.com/createAnUser")
            .addBodyParameter("firstname", "Amit")
            .addBodyParameter("lastname", "Shekhar")
            .setTag("test")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // do anything with response
                    makeLog("networking","success")
                }

                override fun onError(error: ANError) {
                    // handle error
                    makeLog("networking","error")
                }
            })

    }

}
