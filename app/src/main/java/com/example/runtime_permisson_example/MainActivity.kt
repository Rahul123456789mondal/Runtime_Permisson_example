package com.example.runtime_permisson_example

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val TAG = "Permission"
    private val REQUEST_RECORD_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setpermisson()
    }

    private  fun setpermisson(){
        val permission =  ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) // In this line it is cheak the permission

        if(permission!=PackageManager.PERMISSION_GRANTED){
            makeRequest()
            Log.d(TAG, "Permission Faild")
        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.RECORD_AUDIO)){
            val builder = AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("Permission to Acess this microphone is required for this app to record audion")
                .setPositiveButton("OK")
                { dialog, which ->
                    Log.d(TAG, "Clicked")
                }
            val dialog = builder.create()
            dialog.show()
        }else{
            makeRequest()
        }
    }

    private fun makeRequest(){
       ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray){
        when (requestCode) {
            REQUEST_RECORD_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user")
                } else {
                    Log.i(TAG, "Permission has been granted by user")
                }
            }
        }
    }
}
