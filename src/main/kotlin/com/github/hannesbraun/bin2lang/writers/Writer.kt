package com.github.hannesbraun.bin2lang.writers

abstract class Writer(name: String, protected val fileExtension: String) {
    protected val name = name.replace("\\s".toRegex(), "")
    var bytes: ByteArray = ByteArray(0)
    val filename = "$name.$fileExtension"
    abstract fun toLang(): String
}
