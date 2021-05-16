import platform.posix.*
import kotlinx.cinterop.*

actual fun readFile(name: String): UByteArray {
    val file = fopen(name, "rb") ?: throw ReadException("Failed to open file $name")
    var tmp = fseek(file, 0, SEEK_END)
    if (tmp != 0) throw ReadException("fseek error")
    val fileSize = ftell(file).toInt()
    tmp = fseek(file, 0, SEEK_SET)
    if (tmp != 0) throw ReadException("fseek error")
    var input = ByteArray(0)
    memScoped {
        val buffer = allocArray<ByteVar>(fileSize)
        tmp = fread(buffer, fileSize.toULong(), 1, file).toInt()
        if (tmp == 0) throw ReadException("fread error")
        input = buffer.readBytes(fileSize)
    }
    fclose(file)
    return input.toUByteArray()
}

actual fun writeFile(name: String, data: String) {
    val file = fopen(name, "w") ?: throw WriteException("Failed to open file $name")
    memScoped { fprintf(file, data) }
    fclose(file)
}
