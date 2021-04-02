package writers

abstract class Writer(name: String, protected val fileExtension: String) {
    protected val name = name.replace("\\s".toRegex(), "")
    var bytes = ByteArray(0)
    val filename = "$name.$fileExtension"
    abstract fun toLang(): String

    protected fun uBytes() = bytes.toUByteArray()
}
