package com.apps.officialbase

import android.os.Bundle
import com.apps.baselibrary.BaseActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        fab.setOnClickListener {
            makePostRequest("https://fierce-cove-29863.herokuapp.com/createAnUser1",object :ApiResponse{
                override fun onError(error: Any) {

                    showToast(this@MainActivity,error.toString())
                }

                override fun onCompleted(res: Any) {
                    showToast(this@MainActivity,res.toString())
                }

            })
        }
    }


}
