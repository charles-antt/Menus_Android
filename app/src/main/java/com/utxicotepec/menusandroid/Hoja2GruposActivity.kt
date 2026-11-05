package com.utxicotepec.menusandroid

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class Hoja2GruposActivity : AppCompatActivity() {

    private lateinit var txtResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hoja2_grupos)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        setSupportActionBar(findViewById<MaterialToolbar>(R.id.toolbar))

        txtResultado = findViewById(R.id.txtResultado)
    }

    // ZONA B
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_hoja2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Grupo "single": al marcar una, Android desmarca las demás solo
            R.id.dif_facil, R.id.dif_medio, R.id.dif_dificil -> {
                item.isChecked = true
                txtResultado.text = getString(R.string.resultado_formato, item.title)
                true
            }
            // Elemento activable independiente: hay que invertirlo a mano
            R.id.opt_sonido -> {
                item.isChecked = !item.isChecked
                txtResultado.text = getString(
                    if (item.isChecked) R.string.sonido_activado else R.string.sonido_desactivado
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
