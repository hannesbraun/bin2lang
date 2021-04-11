import platform.posix.*
import kotlinx.cinterop.*

actual fun readFile(name: String): UByteArray {
    val file = fopen(name, "rb")
    fseek(file, 0, SEEK_END)
    val fileSize = ftell(file).toInt()
    fseek(file, 0, SEEK_SET)
    var input = ByteArray(0)
    memScoped {
        val buffer = allocArray<ByteVar>(fileSize)
        fread(buffer, fileSize.toULong(), 1, file)
        input = buffer.readBytes(fileSize)
    }
    fclose(file)
    return input.toUByteArray()
}

actual fun writeFile(name: String, data: String) {
    val file = fopen(name, "w")
    memScoped { fprintf(file, data) }
    fclose(file)
}
