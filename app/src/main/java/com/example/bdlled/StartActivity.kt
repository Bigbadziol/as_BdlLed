
package com.example.bdlled

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bdlled.databinding.ActivityStartBinding
import com.google.android.material.snackbar.Snackbar


class StartActivity : AppCompatActivity() {
    companion object {
        val DEVICE_LIST : ArrayList<BluetoothDevice> = ArrayList() //tak na przyszlosc
    }

    lateinit var bAdapter : BluetoothAdapter
    private lateinit var bind : ActivityStartBinding
    private lateinit var deviceList: ArrayList<DeviceListModel>

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
        //2) Pobieramy liste urzadzen LEDL_xxxx oraz LEDE_xxxx
        GetDevices()

        deviceList = ArrayList()
        deviceList.add(0, DeviceListModel("LEDL_2001","ab:34:65:ff:ca:c1",R.drawable.ic_test))
        deviceList.add(1,DeviceListModel("LEDL_2002","00:34:65:aa:b0:d0",R.drawable.ic_test))
        deviceList.add(2,DeviceListModel("LEDL_2003","ff:34:bb:ff:ca:43",R.drawable.ic_test))
        bind.lvDeviceList.adapter =  DeviceListAdapter(this,deviceList)
        bind.lvDeviceList.isClickable = true
        bind.lvDeviceList.setOnItemClickListener { adapterView, view, position, id ->
            val dd = deviceList.get(position)
            Toast.makeText(this, dd.toString(), Toast.LENGTH_SHORT).show()
        }

        bind.btnTest.setOnClickListener { view->
            Snackbar.make(view, "Bluetooth is disabled", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        bind.btnNext.setOnClickListener {
            Log.i("O Co","chodzi")
            //GetDevices()
            if (DEVICE_LIST.size > 0){
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("START_DEVICE_LIST", DEVICE_LIST)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Brak urządzeń , dodaj jakieś", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun GetDevices() : Int{
        val allPairedDevices = bAdapter.bondedDevices
        DEVICE_LIST.clear()
        if (bAdapter.isEnabled){
            for (d: BluetoothDevice in allPairedDevices){
                val devName = d.name
                if (devName.contains("LEDL_") || devName.contains("LEDE_")){
                    Log.i("ESP_ADDED", "$devName")
                    DEVICE_LIST.add(d)
                }
            }
        }
        return DEVICE_LIST.size
    }

}