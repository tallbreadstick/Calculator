package com.tallbreadstick.calculator

class Stack<T>(

    private var top: Node<T>?,
    private var size: Int

) {

    constructor() : this(null, 0)

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun push(item: T) {
        val node = Node(item)
        node.setNext(top)
        top = node
        size++
    }

    fun pop(): T? {
        val temp: Node<T>? = top
        if (temp != null) {
            top = temp.getNext()
            size--
        }
        return temp?.getData()
    }

    fun peek(): T? {
        return top?.getData()
    }

}