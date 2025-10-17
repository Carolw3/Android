package com.carolms.kotlinimcapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.carolms.kotlinimcapp.ImcCalculaorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView

    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initComponent()
        initListeners()
        initUI(result)

    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            navigateToResult()
        }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 ->{ //Bajo
                tvResult.text = getString(R.string.title_bajo_peso)
                tvDescription.text = getString(R.string.description_bajo_peso)
            }
            in 18.51..24.99 ->{ //Normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvDescription.text = getString(R.string.description_peso_normal)
            }
            in 25.00..29.99 ->{ //Sobrepeso
                tvResult.text = getString(R.string.title_sobrepeso)
                tvDescription.text = getString(R.string.description_sobrepeso)
            }
            in 30.00..99.00 ->{ //Obesidad
                tvResult.text = getString(R.string.title_obesidad)
                tvDescription.text = getString(R.string.description_obesidad)
            }
            else->{ //ERROR
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponent() {
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)

        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

    private fun navigateToResult() {
        val intent = Intent(this, ImcCalculaorActivity::class.java)
        startActivity(intent)
    }
}

