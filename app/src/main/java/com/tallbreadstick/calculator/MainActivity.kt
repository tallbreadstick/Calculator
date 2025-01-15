package com.tallbreadstick.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttons : HashMap<String, Button>
    private lateinit var textView : TextView
    private lateinit var expression: StringBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeButtons()
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        if (v?.id == R.id.txt_main) {
            return
        }
        if (v?.id == R.id.btn_eq) {
            Log.d("CALCULATOR", "Converting infix to postfix...")
            val infix = tokenizeExpression(expression.toString())
            val postfix = derivePostfix(infix)
            Log.d("CALCULATOR", "Evaluating postfix expression...")
            try {
                val result = evaluatePostfix(postfix)
                textView.text = "$result"
            } catch (e: Exception) {
                textView.text = "Syntax Error"
                Log.d("CALCULATOR", e.toString())
            }
            return
        }
        if (v?.id == R.id.btn_clear) {
            expression.clear()
            updateTextView()
            return
        }
        if (v?.id == R.id.btn_back) {
            expression.deleteAt(expression.length - 1)
            updateTextView()
            return
        }
        expression.append(
            when (v?.id) {
                R.id.btn_0 -> 0
                R.id.btn_1 -> 1
                R.id.btn_2 -> 2
                R.id.btn_3 -> 3
                R.id.btn_4 -> 4
                R.id.btn_5 -> 5
                R.id.btn_6 -> 6
                R.id.btn_7 -> 7
                R.id.btn_8 -> 8
                R.id.btn_9 -> 9
                R.id.btn_dot -> '.'
                R.id.btn_add -> '+'
                R.id.btn_sub -> '-'
                R.id.btn_mul -> '×'
                R.id.btn_div -> '÷'
                R.id.btn_open -> '('
                R.id.btn_close -> ')'
                else -> throw Exception("Invalid ID Exception")
            }
        )
        Log.d("CALCULATOR", "Pressed button -> ${v.id}")
        updateTextView()
    }

    private fun updateTextView() {
        textView.text = expression.toString()
    }

    private fun initializeButtons() {
        buttons = HashMap(20)
        buttons["btn_add"] = findViewById(R.id.btn_add)
        buttons["btn_sub"] = findViewById(R.id.btn_sub)
        buttons["btn_mul"] = findViewById(R.id.btn_mul)
        buttons["btn_div"] = findViewById(R.id.btn_div)
        buttons["btn_0"] = findViewById(R.id.btn_0)
        buttons["btn_1"] = findViewById(R.id.btn_1)
        buttons["btn_2"] = findViewById(R.id.btn_2)
        buttons["btn_3"] = findViewById(R.id.btn_3)
        buttons["btn_4"] = findViewById(R.id.btn_4)
        buttons["btn_5"] = findViewById(R.id.btn_5)
        buttons["btn_6"] = findViewById(R.id.btn_6)
        buttons["btn_7"] = findViewById(R.id.btn_7)
        buttons["btn_8"] = findViewById(R.id.btn_8)
        buttons["btn_9"] = findViewById(R.id.btn_9)
        buttons["btn_open"] = findViewById(R.id.btn_open)
        buttons["btn_close"] = findViewById(R.id.btn_close)
        buttons["btn_dot"] = findViewById(R.id.btn_dot)
        buttons["btn_back"] = findViewById(R.id.btn_back)
        buttons["btn_clear"] = findViewById(R.id.btn_clear)
        buttons["btn_eq"] = findViewById(R.id.btn_eq)
        textView = findViewById(R.id.txt_main)
        expression = StringBuilder()
        for (buttonId in buttons.keys) {
            buttons[buttonId]?.setOnClickListener(this)
        }
    }

}