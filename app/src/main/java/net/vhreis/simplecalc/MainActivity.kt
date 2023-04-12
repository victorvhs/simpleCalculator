package net.vhreis.simplecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var txtInput: TextView? = null
    private var lastNumeric : Boolean = true
    private var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtInput = findViewById(R.id.txtInput)
    }
    fun onDigit(view: View){
        val btn = view as Button
        txtInput?.append(btn.text)
        lastNumeric = true
    }

    fun onOperator(view: View){
        txtInput?.text?.let{
            if(lastNumeric && !isOperationAdded(it.toString())){
                txtInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false


            }
        }
    }
    private fun isOperationAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{ value.contains("/")
                || value.contains("*")
                || value.contains("+")
                || value.contains("-")
        }
    }
    fun onEqual(view: View) {
        val txtValue = txtInput?.text.toString()

        if(txtValue.contains("Ininito")){
            txtInput?.text = "0"
            Toast.makeText(this,"Resultado Zerado, Não se pode dividir por zero",Toast.LENGTH_LONG).show()
        }
        if(lastNumeric){
            when {
                txtValue.contains("-") -> subtract(txtValue)
                txtValue.contains("+") -> sum(txtValue)
                txtValue.contains("*") -> multiply(txtValue)
                txtValue.contains("/") -> divide(txtValue)
            }
        }
    }

    fun onClear(view: View) {
        txtInput?.text = ""
        lastDot = false
        lastNumeric = false
    }
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            txtInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    private fun subtract(txtValue: String){
        var prefix = ""
        var txtValue2:String = txtValue
        try {
            if(txtValue2.startsWith("-")){
                prefix = "-"
                txtValue2 = txtValue.substring(1)
            }
            if (txtValue.contains("-")){
                val split = txtValue2.split("-")
                var one = split[0]
                val two = split[1]
                if(prefix.isNotEmpty()){
                    one = prefix + one
                }
                txtInput?.text = (one.toDouble() - two.toDouble()).toString()
            }

        }catch (e: ArithmeticException){
            e.printStackTrace()
        }
    }
    private fun sum(txtValue: String){
        var prefix = ""
        var txtValue2:String = txtValue
        try {
            if(txtValue2.startsWith("-")){
                prefix = "-"
                txtValue2 = txtValue.substring(1)
            }
            if (txtValue.contains("+")){
                val split = txtValue2.split("+")
                var one = split[0]
                val two = split[1]
                if(prefix.isNotEmpty()){
                    one = prefix + one
                }
                (one.toDouble() + two.toDouble()).toString().also { txtInput?.text = it }
            }

        }catch (e: ArithmeticException){
            e.printStackTrace()
        }
    }
    private fun multiply(txtValue: String){
        var prefix = ""
        var txtValue2:String = txtValue
        try {
            if(txtValue2.startsWith("-")){
                prefix = "-"
                txtValue2 = txtValue.substring(1)
            }
            if (txtValue.contains("*")){
                val split = txtValue2.split("*")
                var one = split[0]
                val two = split[1]
                if(prefix.isNotEmpty()){
                    one = prefix + one
                }
                txtInput?.text = (one.toDouble() * two.toDouble()).toString()
            }

        }catch (e: ArithmeticException){
            e.printStackTrace()
        }
    }
    private fun divide(txtValue: String){
        var prefix = ""
        var txtValue2:String = txtValue
        try {
            if(txtValue2.startsWith("-")){
                prefix = "-"
                txtValue2 = txtValue.substring(1)
            }
            if (txtValue.contains("/")){
                val split = txtValue2.split("/")
                var one = split[0]
                val two = split[1]
                if(two.toDouble() == 0.0){
                    txtInput?.text="Ininito"
                    lastNumeric= false
                    return
                }
                if(prefix.isNotEmpty()){
                    one = prefix + one
                }
                txtInput?.text = (one.toDouble() / two.toDouble()).toString()
            }

        }catch (e: ArithmeticException){
            e.printStackTrace()
        }
    }
}