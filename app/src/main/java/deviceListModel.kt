package com.example.bdlled

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.bdlled.R
import java.util.*
import kotlin.collections.ArrayList

data class DeviceListModel (var deviceName : String, var deviceAdress : String, var iconId : Int)



class DeviceListAdapter(private val  context: Activity, var items : ArrayList<DeviceListModel>) :
    ArrayAdapter<DeviceListModel>(context , R.layout.device_list_row , items){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater : LayoutInflater = LayoutInflater.from(context)
            val view : View = layoutInflater.inflate(R.layout.device_list_row,null)

            val icon : ImageView = view.findViewById(R.id.ivIcon)
            val deviceName : TextView = view.findViewById(R.id.tvDeviceName)
            val deviceAdress : TextView = view.findViewById(R.id.tvDeviceAdress)

            icon.setImageResource(items[position].iconId)
            deviceName.text = items[position].deviceName
            deviceAdress.text = items[position].deviceAdress

            //return super.getView(position, convertView, parent)
            return view
        }
    }