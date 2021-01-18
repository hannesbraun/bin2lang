package writers

abstract class Writer(protected val name: String, private val fileExtension: String) {
    var bytes: ByteArray = ByteArray(0)
    fun filename() = "$name.$fileExtension"
    abstract fun toLang(): String
}