package net.vhreis.simplecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
        Toast.makeText(this, "Você clicou",Toast.LENGTH_LONG ).show()
    }

    fun onOperator(view: View){
        Toast.makeText(this,"Você clicou em uma operação",Toast.LENGTH_LONG).show()
    }
}