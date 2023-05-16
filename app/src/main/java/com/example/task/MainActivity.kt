package com.example.task

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    @SuppressLint("HardwareIds")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.READ_PHONE_NUMBERS,
//                Manifest.permission.READ_SMS), 101)
//        }
//        val telephonManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        try {
//            println(telephonManager.simOperatorName)
//            println(telephonManager.deviceSoftwareVersion)
//            println(telephonManager.simCountryIso)
//            //println(telephonManager.getImei())
//            println(telephonManager.phoneCount)
//            println(telephonManager.signalStrength?.level)
//            println(telephonManager.line1Number)
//        }catch (e:Exception){
//            println(e.message)
//        }



        //println(telephonManager.simSerialNumber)

        setupActionBarWithNavController(findNavController(R.id.fragment))

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}