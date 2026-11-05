package com.utxicotepec.menusandroid

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class Hoja5EmergenteActivity : AppCompatActivity() {

    private lateinit var txtResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hoja5_emergente)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        setSupportActionBar(findViewById<MaterialToolbar>(R.id.toolbar))

        txtResultado = findViewById(R.id.txtResultado)

        // ZONA A: aquí van las instrucciones del tutorial que van dentro de onCreate
        findViewById<View>(R.id.btnMenu).setOnClickListener { boton ->
            mostrarMenuEmergente(boton)
        }
    }

    // ZONA B: aquí van las funciones del tutorial que van FUERA de onCreate
    private fun mostrarMenuEmergente(anclaje: View) {
        val popup = PopupMenu(this, anclaje) // 'anclaje' hace que salga justo debajo del botón
        popup.menuInflater.inflate(R.menu.menu_hoja5, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.pop_guardar, R.id.pop_copiar, R.id.pop_borrar -> {
                    txtResultado.text = getString(R.string.resultado_formato, item.title)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}