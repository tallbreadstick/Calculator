package com.tallbreadstick.calculator

import java.util.StringTokenizer

fun tokenizeExpression(exp: String): ArrayList<String> {
    val tokenizer = StringTokenizer(exp, "+-×÷()", true)
    val tokens = ArrayList<String>()
    while (tokenizer.hasMoreTokens()) {
        tokens.add(tokenizer.nextToken())
    }
    return tokens
}

fun derivePostfix(infix: ArrayList<String>): ArrayList<String> {
    val stack = Stack<String>()
    val postfix = ArrayList<String>()
    for (token in infix) {
        when {
            token[0].isDigit() -> {
                postfix.add(token)
            }
            token == "(" -> {
                stack.push(token)
            }
            token == ")" -> {
                while (!stack.isEmpty() && stack.peek() != "(") {
                    postfix.add(stack.pop()!!)
                }
                stack.pop()
            }
            token[0] in "+-×÷" -> {
                while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek()!!)) {
                    postfix.add(stack.pop()!!)
                }
                stack.push(token)
            }
        }
    }
    while (!stack.isEmpty()) {
        postfix.add(stack.pop()!!)
    }
    return postfix
}

fun evaluatePostfix(postfix: ArrayList<String>): Double {
    val stack = Stack<Double>()
    for (token in postfix) {
        if (token[0].isDigit()) {
            stack.push(token.toDouble())
        } else {
            val num1 = stack.pop()!!.toDouble()
            val num2 = stack.pop()!!.toDouble()
            stack.push(when (token) {
                "+" -> num2 + num1
                "-" -> num2 - num1
                "×" -> num2 * num1
                "÷" -> num2 / num1
                else -> throw Exception("Invalid Operator Exception")
            })
        }
    }
    return stack.pop()!!
}

fun precedence(operator: String): Int {
    return when (operator) {
        "×", "÷" -> 2
        "+", "-" -> 1
        else -> 0
    }
}