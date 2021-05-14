package writers

abstract class Writer(name: String, protected val fileExtension: String) {
    protected val name = name.replace("\\s".toRegex(), "")
    var bytes = UByteArray(0)
    val filename = "$name.$fileExtension"
    abstract fun toLang(): String

    fun UByte.hexString() = "0x${this.toString(16).padStart(2, '0')}"
}
