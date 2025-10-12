package com.example.activitat_04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.activitat_04.R

class ImcCalculatorActivity : AppCompatActivity() {

    private lateinit var viewMale: CardView
    private lateinit var viewFamale: CardView

    private var isMaleSeleccted:Boolean = true
    private var isFamaleSeleccted:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imccalculator)
        initComponent()
        initListeners()
        initUI()

    }


    private fun initComponent() {
        viewMale = findViewById(R.id.male)
        viewFamale = findViewById(R.id.famale)

    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor() }
        viewFamale.setOnClickListener {
            changeGender()
            setGenderColor() }
    }

    private fun changeGender(){
        isMaleSeleccted = !isMaleSeleccted
        isFamaleSeleccted = !isFamaleSeleccted
    }

    private fun setGenderColor(){

        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSeleccted))
        viewFamale.setCardBackgroundColor(getBackgroundColor(isFamaleSeleccted))

    }

    private fun getBackgroundColor(isSelectedComponent:Boolean) : Int {

        val colorReference = if(isSelectedComponent){
            R.color.element_selected_color
        }else{
            R.color.element_color
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
    }

}