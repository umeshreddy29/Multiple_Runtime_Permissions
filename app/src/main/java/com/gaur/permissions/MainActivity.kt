package com.gaur.permissions

import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {



    private val registerActivity = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){permissions->

        if(permissions[READ_CONTACTS]==true){
            Toast.makeText(this,"read contacts permission is given",Toast.LENGTH_SHORT).show()
        }

        if(permissions[READ_EXTERNAL_STORAGE]==true){
            Toast.makeText(this,"read read external storage permission is given",Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.takeMultiplePermission)

        button.setOnClickListener {
            takeMultiplePermissions()
        }


    }

    private fun takeMultiplePermissions() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED ||
                 ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                registerActivity.launch(arrayOf(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.READ_EXTERNAL_STORAGE))
            }
        }else{
            registerActivity.launch(arrayOf(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.READ_EXTERNAL_STORAGE))
        }
    }


}