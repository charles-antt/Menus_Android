package com.utxicotepec.menusandroid

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class Hoja3ContextualActivity : AppCompatActivity() {

    private lateinit var txtResultado: TextView
    private lateinit var txtElemento: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hoja3_contextual)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        setSupportActionBar(findViewById<MaterialToolbar>(R.id.toolbar))

        txtResultado = findViewById(R.id.txtResultado)

        // ZONA A: registrar el elemento que abrirá el menú al dejarlo presionado
        registerForContextMenu(findViewById<TextView>(R.id.txtElemento))
    }

    // ZONA B
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle(R.string.ctx_titulo)
        menuInflater.inflate(R.menu.menu_hoja3, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ctx_editar, R.id.ctx_compartir, R.id.ctx_eliminar -> {
                txtResultado.text = getString(R.string.resultado_formato, item.title)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
