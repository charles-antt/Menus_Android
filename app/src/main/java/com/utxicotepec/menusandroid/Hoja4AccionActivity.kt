package com.utxicotepec.menusandroid

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class Hoja4AccionActivity : AppCompatActivity() {

    private lateinit var txtResultado: TextView
    private var modoAccion: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hoja4_accion)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        setSupportActionBar(findViewById<MaterialToolbar>(R.id.toolbar))

        txtResultado = findViewById(R.id.txtResultado)

        // ZONA A: al dejar presionado el elemento, se abre el modo de acción
        findViewById<TextView>(R.id.txtElemento).setOnLongClickListener {
            if (modoAccion != null) {
                false
            } else {
                modoAccion = startSupportActionMode(callbackAccion)
                true
            }
        }
    }

    // ZONA B
    private val callbackAccion = object : ActionMode.Callback {

        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            mode.menuInflater.inflate(R.menu.menu_hoja4, menu)
            mode.setTitle(R.string.accion_titulo)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = false

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.act_editar, R.id.act_compartir, R.id.act_eliminar -> {
                    txtResultado.text = getString(R.string.resultado_formato, item.title)
                    mode.finish() // la barra contextual desaparece
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            modoAccion = null
        }
    }
}