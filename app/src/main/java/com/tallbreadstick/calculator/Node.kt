package com.tallbreadstick.calculator

class Node<T>(

    private var data: T,
    private var next: Node<T>?

) {

    constructor(data: T) : this(data, null)

    fun getData(): T {
        return data
    }

    fun getNext(): Node<T>? {
        return next
    }

    fun setNext(next: Node<T>?) {
        this.next = next
    }

}