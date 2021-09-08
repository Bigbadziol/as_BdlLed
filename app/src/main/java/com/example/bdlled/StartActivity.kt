
package com.example.bdlled

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bdlled.databinding.ActivityStartBinding
import com.google.android.material.snackbar.Snackbar



class StartActivity : AppCompatActivity() {
    companion object {
        var DEVICE_LIST : ArrayList<BluetoothDevice> = ArrayList() //tak na przyszlosc
        var CURRENT_SELECTED : Int = 0
    }

    lateinit var bAdapter : BluetoothAdapter
    private lateinit var bind : ActivityStartBinding
    private lateinit var deviceList: ArrayList<DeviceListModel>

    var state : EspConnectionState =  EspConnectionState.DISCONNECTED

    override fun onCreate(savedInstanceState: Bundle?) {
        bind = ActivityStartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        //1) Wlaczamy BT jesli wylaczone
        bAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bAdapter.isEnabled==false) {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivity(intent)
        }
        //2) Pobieramy liste urzadzen LEDL_xxxx oraz LEDE_xxxx , jedna lista do "intent" , druga
        // do customowego listboxa
        deviceList = ArrayList()
        GetDevices()

        bind.lvDeviceList.isClickable = true
        bind.lvDeviceList.setOnItemClickListener { adapterView, view, position, id ->
            CURRENT_SELECTED = position
            //Toast.makeText(this, position, Toast.LENGTH_SHORT).show()
        }

        bind.btnAddDevice.setOnClickListener { view->
            Log.d("TEST", state.description)
            state = EspConnectionState.CONNECTING
            Log.d("TEST", state.description)
            state = EspConnectionState.CONNECTED
            Log.d("TEST", state.description)
            state = EspConnectionState.CONNECTION_ERROR
            Log.d("TEST", state.description)

            Snackbar.make(view, "Tu bedzie dodawanie urzadzenia", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        bind.btnNext.setOnClickListener {
            Log.i("O Co","chodzi")
            //GetDevices()
            if (DEVICE_LIST.size > 0){
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("START_DEVICE_LIST", DEVICE_LIST)
                intent.putExtra("START_CURRENT_SELECTED", CURRENT_SELECTED)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Brak urządzeń , dodaj jakieś", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun GetDevices() : Int{
        val allPairedDevices = bAdapter.bondedDevices
        var name : String
        var addres : String
        var iconID : Int
        DEVICE_LIST.clear()
        deviceList.clear()
        if (bAdapter.isEnabled){
            for (d: BluetoothDevice in allPairedDevices){
                 name = d.name
                 addres = d.address
                if (name.contains("LEDL_") || name.contains("LEDE_")){
                    if (name.contains("LEDL_")) iconID = R.drawable.ic_test //ikonka listwy
                    else iconID = R.drawable.ic_test //ikonka ekranu
                    DEVICE_LIST.add(d)
                    deviceList.add(DeviceListModel(name,addres,iconID))
                    Log.i("ESP_ADDED", name)
                }
            }
            bind.lvDeviceList.adapter =  DeviceListAdapter(this,deviceList)
        }
        return DEVICE_LIST.size
    }


}