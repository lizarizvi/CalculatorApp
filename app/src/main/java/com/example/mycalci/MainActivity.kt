package com.example.mycalci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView?=null
    //private var btnOne: Button?=null
    var lastNum: Boolean=false
    var lastDot: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput= findViewById(R.id.tvInput)
        //btnOne= findViewById(R.id.btnOne)
        //btnOne.setOnClickListener {
//            tvInput?.append("1")
//        }
    }

    fun onDigit(view: View){
       tvInput?.append((view as Button).text)
        lastNum=true

    }
    fun onClear(view: View){
        tvInput?.text=""
        lastDot=false
        lastNum=false
    }
    fun onPoint(view: View){
        if(lastNum && !lastDot){
            tvInput?.append(".")

            lastDot=true
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNum && !isOperatorAdd(it.toString())){
                tvInput?.append((view as Button).text)
                lastNum=false
                lastDot=false
            }
        }
    }

    fun onEqual(view: View){
        if(lastNum){
            var tvValue= tvInput?.text.toString()
            var prefix= ""
            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    var one= splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    var result=one.toDouble()-two.toDouble()
                    tvInput?.text=removeZero(result.toString())
                }
                else if (tvValue.contains("+")){
                    val splitValue=tvValue.split("+")
                    var one= splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    var result=one.toDouble()+two.toDouble()
                    tvInput?.text=removeZero(result.toString())
                }
                else if (tvValue.contains("/")){
                    val splitValue=tvValue.split("/")
                    var one= splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    var result=one.toDouble()/two.toDouble()
                    tvInput?.text=removeZero(result.toString())
                }
                else if (tvValue.contains("*")){
                    val splitValue=tvValue.split("*")
                    var one= splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    var result=one.toDouble()*two.toDouble()
                    tvInput?.text=removeZero(result.toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZero(result: String): String{
        var value= result
        if (result.contains("0")){
            value=result.substring(0,result.length-2)
        }
        return value
    }


    private fun isOperatorAdd(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        } else{
            value.contains("+")  || value.contains("-")
                    || value.contains("*") || value.contains("/")
        }
    }
}

