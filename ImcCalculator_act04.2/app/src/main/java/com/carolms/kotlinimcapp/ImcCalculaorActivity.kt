package com.carolms.kotlinimcapp

import android.app.backup.BackupAgent
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculaorActivity : AppCompatActivity() {

    private lateinit var viewMale: CardView
    private lateinit var viewFamale: CardView

    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private var currentHeight :Int = 120


    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var numberWeigth: TextView
    private var currentWeigth: Int = 70


    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var numberAge: TextView
    private var currentAge: Int = 35

    private var isMaleSelected: Boolean = true
    private var isFamaleSelected: Boolean = false


    private lateinit var btnCalculate: Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculaor)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //   v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        initComponents()
        initListeners()
        initUI()
        }


    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFamale = findViewById(R.id.viewFamale)

        tvHeight = findViewById(R.id.numberHeight)
        rsHeight = findViewById(R.id.rsHeight)

        btnSubstractWeight= findViewById(R.id.btnSubstractWeight)
        btnPlusWeight= findViewById(R.id.btnPlusWeight)
        numberWeigth= findViewById(R.id.numberWeight)

        btnSubstractAge= findViewById(R.id.btnSubstractAge)
        btnPlusAge= findViewById(R.id.btnPlusAge)
        numberAge= findViewById(R.id.numberAge)

        btnCalculate= findViewById(R.id.btnCalculate)



    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFamale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _-> //Altura
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()

            tvHeight.text = "${currentHeight.toString()} cm"
        }

        btnPlusWeight.setOnClickListener { //Peso
            currentWeigth += 1
            setWeigth()
        }
        btnSubstractWeight.setOnClickListener {
            currentWeigth -= 1
            setWeigth()
        }

        btnPlusAge.setOnClickListener { //Edad
            currentAge += 1
            setAge()
        }
        btnSubstractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        btnCalculate.setOnClickListener {
            val result = calculateImc()
            navigateToResult(result)
        }
    }


    private fun calculateImc(): Double{
        val df = DecimalFormat("#.##")
        val imc = currentWeigth/((currentHeight.toDouble()/100 )*(currentHeight.toDouble()/100))
        return df.format(imc).toDouble()

    }

    private fun ImcCalculaorActivity.navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun setWeigth(){
        numberWeigth.text = currentWeigth.toString()
    }

    private fun setAge(){
        numberAge.text = currentAge.toString()
    }

    private fun changeGender(){  //Seleccio de genere
        isMaleSelected = !isMaleSelected
        isFamaleSelected = !isFamaleSelected

    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFamale.setCardBackgroundColor(getBackgroundColor(isFamaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int{
        val componentColor = if (isSelectedComponent){
            R.color.element_selected
        }else{
            R.color.element
        }

        return ContextCompat.getColor(this, componentColor)
    }

    private fun initUI() {
        setGenderColor()
        calculateImc()
    }
}




