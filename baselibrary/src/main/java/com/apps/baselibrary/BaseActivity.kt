package com.apps.baselibrary

import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.error.ANError
import org.json.JSONObject
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.AndroidNetworking
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.androidnetworking.common.Priority


open class BaseActivity : AppCompatActivity() {

    open lateinit var listener: ApiResponse


    private fun setRequestListener():JSONObjectRequestListener{
       return  object : JSONObjectRequestListener {
            override fun onResponse(response: JSONObject) {
                // do anything with response
                makeLog("networking","success")
                listener.onCompleted(response)
            }

            override fun onError(error: ANError) {
                // handle error
                makeLog("networking","error")
                listener.onError(error)
            }
        }
    }

    public fun setListeners(res:ApiResponse){
        listener = res
    }

    protected fun showSnackbar(view:View,msg:String){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    protected fun showToast(context:Context,msg:String){
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


    protected fun makePostRequest(url: String, param: ApiResponse){
        setListeners(param)
        AndroidNetworking.post(url)
            .addBodyParameter("firstname", "Amit")
            .addBodyParameter("lastname", "Shekhar")
            .setTag("test")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(setRequestListener())

    }

    interface ApiResponse{
        public fun onCompleted(res:Any)
        public fun onError(error:Any)
    }


}
