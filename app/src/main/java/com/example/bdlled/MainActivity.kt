//https://github.com/Dhaval2404/ColorPicker
package com.example.bdlled

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import com.example.bdlled.databinding.ActivityMainBinding
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.util.setVisibility
import jsonData_config

//class MainActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener{ //"pojebane"
class MainActivity : AppCompatActivity(){
    private lateinit var bind : ActivityMainBinding
    /*
    Clear Seek Bar parameter
    */
    private fun clearParamVal(p : SeekBar){
        p.min = 0
        p.max = 255
        p.setProgress(0,false)
    }

    private fun clearAndHideEffectInterface(){
        bind.tvEffectName.text = resources.getString(R.string.tvEffectName)
        bind.edColor1.setBackgroundColor(Color.parseColor("#000000"))
        bind.edColor2.setBackgroundColor(Color.parseColor("#000000"))
        bind.lbPalette.text = resources.getString(R.string.lbPalette)
        bind.spPalette.setSelection(0)
        //TAKIE TROCHE NA PALE
        bind.lbCustom.text = resources.getString(R.string.lbCustom)
        bind.spCustom.adapter = ArrayAdapter(this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                resources.getStringArray(R.array.TestCustomParameterList))
        bind.spCustom.setSelection(0)

        bind.lbParam1.text = resources.getString(R.string.lbParam1)
        clearParamVal(bind.sbParam1)
        bind.lbParam2.text = resources.getString(R.string.lbParam2)
        clearParamVal(bind.sbParam2)
        bind.lbParam3.text = resources.getString(R.string.lbParam3)
        clearParamVal(bind.sbParam3)
        bind.lbParam4.text = resources.getString(R.string.lbParam4)
        clearParamVal(bind.sbParam4)

        bind.lbBool1.text = resources.getString(R.string.lbBool1)
        bind.swBool1.setChecked(false)

        bind.lbBool2.text = resources.getString(R.string.lbBool2)
        bind.swBool2.setChecked(false)

/*
        //tutaj pewnie dodatkowo ukrywane są wiersze tabeli , problem się pojawia z
        //dostępem do nich , zatem rozwiązanie na pałe , zwyczajnie ręcznie powyłączać elementy
        for (i in bind.panelEffect.children ){
            i.setVisibility(false)
        }
*/
        //wersja na pałe
        bind.tvEffectName.setVisibility(false)
        bind.btnColor1.setVisibility(false)
        bind.edColor1.setVisibility(false)
        bind.btnColor2.setVisibility(false)
        bind.edColor2.setVisibility(false)
        bind.lbPalette.setVisibility(false)
        bind.spPalette.setVisibility(false)
        bind.lbCustom.setVisibility(false)
        bind.spCustom.setVisibility(false)

        bind.lbParam1.setVisibility(false)
        bind.lbParam1Val.setVisibility(false)
        bind.sbParam1.setVisibility(false)

        bind.lbParam2.setVisibility(false)
        bind.lbParam2Val.setVisibility(false)
        bind.sbParam2.setVisibility(false)

        bind.lbParam3.setVisibility(false)
        bind.lbParam3Val.setVisibility(false)
        bind.sbParam3.setVisibility(false)

        bind.lbParam4.setVisibility(false)
        bind.lbParam4Val.setVisibility(false)
        bind.sbParam4.setVisibility(false)

        bind.lbBool1.setVisibility(false)
        bind.swBool1.setVisibility(false)

        bind.lbBool2.setVisibility(false)
        bind.swBool2.setVisibility(false)

        bind.btnEffectConfirm.setVisibility(false)
    }

    private fun setEffectName( name : String ){
        bind.panelEffect.setVisibility(visible = true)
        bind.tvEffectName.setVisibility(visible = true)
        bind.tvEffectName.text = name
    }

    private fun setParamColor(pColorNum : Int ,  r :Int , g : Int ,b : Int){
        when (pColorNum){
            1 -> {
                    bind.btnColor1.setVisibility(true)
                    bind.edColor1.setVisibility(true)
                    bind.edColor1.setBackgroundColor(Color.rgb(r,g,b))
                }
            2 -> {
                bind.btnColor2.setVisibility(true)
                bind.edColor2.setVisibility(true)
                bind.edColor2.setBackgroundColor(Color.rgb(r,g,b))
            }
        }
    }

    private fun setPalette( index : Int){
        bind.lbPalette.setVisibility(true)
        bind.spPalette.setVisibility(true)
        if (index > bind.spPalette.count - 1) bind.spPalette.setSelection(0,false)
        else bind.spPalette.setSelection(index,false)
     //   Toast.makeText(this,"Count P:" +bind.spPalette.count,Toast.LENGTH_SHORT).show()
    }

    private fun setCustom(desc : String , elem : Array<String>, index : Int){
        if (elem.size > 0){
            bind.lbCustom.setVisibility(true)
            bind.lbCustom.text = desc
            bind.spCustom.setVisibility(true)
            val aC = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,elem)
            bind.spCustom.adapter = aC
            if (index > elem.size -1) bind.spCustom.setSelection(0)
            else bind.spCustom.setSelection(index)
        }
    }

    private fun setParamVal(pNum : Int , desc : String , pVal : Int , pMin : Int , pMax : Int){
        when (pNum){
            1 -> {
                    bind.lbParam1.setVisibility(true)
                    bind.lbParam1Val.setVisibility(true)
                    bind.sbParam1.setVisibility(true)
                    bind.lbParam1.text = desc
                    bind.sbParam1.min = pMin
                    bind.sbParam1.max = pMax
                    bind.sbParam1.progress = pVal
                }
            2->{
                    bind.lbParam2.setVisibility(true)
                    bind.lbParam2Val.setVisibility(true)
                    bind.sbParam2.setVisibility(true)
                    bind.lbParam2.text = desc
                    bind.sbParam2.min = pMin
                    bind.sbParam2.max = pMax
                    bind.sbParam2.progress = pVal
                }
            3->{
                    bind.lbParam3.setVisibility(true)
                    bind.lbParam3Val.setVisibility(true)
                    bind.sbParam3.setVisibility(true)
                    bind.lbParam3.text = desc
                    bind.sbParam3.min = pMin
                    bind.sbParam3.max = pMax
                    bind.sbParam3.progress = pVal
            }
            4->{
                bind.lbParam4.setVisibility(true)
                bind.lbParam4Val.setVisibility(true)
                bind.sbParam4.setVisibility(true)
                bind.lbParam4.text = desc
                bind.sbParam4.min = pMin
                bind.sbParam4.max = pMax
                bind.sbParam4.progress = pVal
            }
        }
    }

    private fun setParamBool(pNum : Int , desc : String ,state : Boolean){
        when (pNum){
            1-> {
                    bind.lbBool1.setVisibility(true)
                    bind.lbBool1.text = desc
                    bind.swBool1.setVisibility(true)
                    bind.swBool1.setChecked(state)
                }
            2-> {
                    bind.lbBool2.setVisibility(true)
                    bind.lbBool2.text = desc
                    bind.swBool2.setVisibility(true)
                    bind.swBool2.setChecked(state)
                }
        }
    }
    /*
    * pi - prepare interface
    *  Idea - > Get Data from "current sellected" object
    * */
    private fun piTest(){
        clearAndHideEffectInterface()
        setEffectName("!Fajer 2!")
        setParamColor(2,255,128,128)
        setPalette(3)
        val cp = arrayOf("Ala","Ma","Kota","Milicja","Ma","Pale")
        setCustom("MRAU!",cp,3)
        setParamVal(1,"Fade",180,128,196)
        setParamVal(2,"Papa2:",6,5,10)
        setParamBool(1,"This T",true)
        setParamBool(2,"ThisF",false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        bind = ActivityMainBinding.inflate(layoutInflater)
        val devicesList = resources.getStringArray(R.array.TestDeviceList) //przeniesione z blok 1
        val modeList = resources.getStringArray(R.array.ModeList)
        val effectList = resources.getStringArray(R.array.TestEffectList)
        val paletteList = resources.getStringArray(R.array.PaletteList)
        val customList = resources.getStringArray(R.array.TestCustomParameterList)
        super.onCreate(savedInstanceState)
        setContentView(bind.root)


        //--------------------------connection panel------------------------------------------------
        //simple_spinner_item
        //simple_spinner_dropdown_item - wyglada najlepiej
        //val devicesList2 = arrayOf("Wybor 1" , "Wybor 2 " , "Wybor 3") // i do adaptera
        //-----decices list
        //Blok 1 - Poczatek
        val adapterDevices = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item,devicesList)
        bind.spDevices.adapter = adapterDevices
        bind.spDevices.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,  view: View, position: Int, id: Long) {
                    //Toast.makeText(this@MainActivity, "" + devicesList[position], Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, "DEVICE : " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
                }
            }
        //Blok 1 - Koniec
        //----btn connect
        bind.btnConnect.setOnClickListener {
            Toast.makeText(this, "Conneting to device", Toast.LENGTH_SHORT).show()
        }
        //------------------------main settings-----------------------------------------------------
        //-----mode
        val adapterMode = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,modeList)
        bind.spMode.adapter = adapterMode
        bind.spMode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, "M: " + parent.getItemAtPosition(position) + " : " + position, Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        //----effects
        val adapterEffects = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,effectList)
        bind.spEffect.adapter = adapterEffects
        bind.spEffect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, "E: " + parent.getItemAtPosition(position) + " : " + position, Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

        }
        //----time
        bind.sbTime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                bind.lbTimeVal.text = progress.toString()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {
            }
            override fun onStopTrackingTouch(sb: SeekBar?) {
                Toast.makeText(this@MainActivity, "Time seek bar last val : ${sb?.progress}", Toast.LENGTH_SHORT).show()
            }
        })
        //----main color pick
        bind.btnColorMain.setOnClickListener {
            ColorPickerDialog
                .Builder(this)        				// Pass Activity Instance
                .setTitle("Wybierz kolor")           	// Default "Choose Color"
                .setColorShape(ColorShape.CIRCLE)   // Default ColorShape.CIRCLE
                .setDefaultColor("#ff0000")     // Pass Default Color
                .setColorListener { _, colorHex ->
                    Toast.makeText(this, "Main Color:"+colorHex+" ", Toast.LENGTH_SHORT).show()
                    bind.tvColorMain.setBackgroundColor(Color.parseColor(colorHex))
                }.show()
        }
        //----confirm
        bind.btnMainConfirm.setOnClickListener {
            Toast.makeText(this, "Main confirm ", Toast.LENGTH_SHORT).show()
        }
        //------------------------Effect settings----------------------------------------------------
        //----pick color 1
        bind.btnColor1.setOnClickListener {
            ColorPickerDialog
                .Builder(this)        				// Pass Activity Instance
                .setTitle("Wybierz kolor")           	// Default "Choose Color"
                .setColorShape(ColorShape.CIRCLE)   // Default ColorShape.CIRCLE
                .setDefaultColor("#ff0000")     // Pass Default Color
                .setColorListener { _, colorHex ->
                    //Toast.makeText(this, "Test1 Color"+colorHex+" ", Toast.LENGTH_SHORT).show()
                    bind.edColor1.setBackgroundColor(Color.parseColor(colorHex))
                }
                .show()
        }
        //----pick color 2
        bind.btnColor2.setOnClickListener {
            ColorPickerDialog
                .Builder(this)        				// Pass Activity Instance
                .setTitle("Wybierz kolor")           	// Default "Choose Color"
                .setColorShape(ColorShape.CIRCLE)   // Default ColorShape.CIRCLE
                .setDefaultColor("#ff0000")     // Pass Default Color
                .setColorListener { _, colorHex ->
                    //Toast.makeText(this, "Test1 Color"+colorHex+" ", Toast.LENGTH_SHORT).show()
                    bind.edColor2.setBackgroundColor(Color.parseColor(colorHex))
                }
                .show()
        }
        //----palette pick
        val adapterPalette = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,paletteList)
        bind.spPalette.adapter = adapterPalette
        bind.spPalette.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, "P: " + parent.getItemAtPosition(position) + " : " + position, Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        //----custom pick
        val adapterCustom = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,customList)
        //adapterCustom.also { bind.spCustom.adapter = it } // ?!?!!?!!?!?!??!?!?!!? JA JEBIE
        bind.spCustom.adapter = adapterCustom
        bind.spCustom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, "C: " + parent.getItemAtPosition(position) + " : " + position, Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        //----param 1
        bind.sbParam1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                bind.lbParam1Val.text = progress.toString()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {
            }
            override fun onStopTrackingTouch(sb: SeekBar?) {
                Toast.makeText(this@MainActivity, "P1: ${sb?.progress}", Toast.LENGTH_SHORT).show()
            }
        })
        //----param 2
        bind.sbParam2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                bind.lbParam2Val.text = progress.toString()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {
            }
            override fun onStopTrackingTouch(sb: SeekBar?) {
                Toast.makeText(this@MainActivity, "P2: ${sb?.progress}", Toast.LENGTH_SHORT).show()
            }
        })
        //----param 3
        bind.sbParam3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                bind.lbParam3Val.text = progress.toString()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {
            }
            override fun onStopTrackingTouch(sb: SeekBar?) {
                Toast.makeText(this@MainActivity, "P3: ${sb?.progress}", Toast.LENGTH_SHORT).show()
            }
        })
        //----param 4
        bind.sbParam4.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                bind.lbParam4Val.text = progress.toString()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {
            }
            override fun onStopTrackingTouch(sb: SeekBar?) {
                Toast.makeText(this@MainActivity, "P4: ${sb?.progress}", Toast.LENGTH_SHORT).show()
            }
        })
        //----bool 1
        bind.swBool1.setOnCheckedChangeListener { _, b ->
            Toast.makeText(this, "B1:"+b.toString(), Toast.LENGTH_SHORT).show()
        }
        //----bool 2
        bind.swBool2.setOnCheckedChangeListener { _, b ->
            Toast.makeText(this, "B2:"+b.toString(), Toast.LENGTH_SHORT).show()
        }
        //----button confirm effect
        bind.btnEffectConfirm.setOnClickListener {
            Toast.makeText(this, "Effect confirm", Toast.LENGTH_SHORT).show()
        }

        //------------------------test panel--------------------------------------------------------
        // TEST PANEL///
        bind.btnTest1.setOnClickListener {
            //Toast.makeText(this, "Test1 click", Toast.LENGTH_SHORT).show()
            piTest()
        }
        bind.btnTest2.setOnClickListener {
            //Toast.makeText(this, "Test2 click", Toast.LENGTH_SHORT).show()
            //bind.panelMainSettings.setVisibility(visible = false)
            piTest()
        }
        bind.btnTest3.setOnClickListener {
            Toast.makeText(this,jsonData_config, Toast.LENGTH_SHORT).show()
            //bind.panelMainSettings.setVisibility(true)
        }
    }
}


