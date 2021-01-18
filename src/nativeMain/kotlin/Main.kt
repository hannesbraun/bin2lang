import kotlinx.cinterop.*
import writers.CWriter
import platform.posix.*


fun main(args: Array<String>) {
    if (args.size != 3) {
        fprintf(stderr, "ERROR: wrong number of arguments\n")
        return
    }

    val writer = when (args[0].toLowerCase()) {
        "c" -> CWriter(args[2])
        else -> {
            fprintf(stderr, "ERROR: unknown target: ${args[0]}\n")
            return
        }
    }

    writer.bytes = readFile(args[1])
    val langString = writer.toLang()
    writeFile(writer.filename(), langString)
}

private fun readFile(name: String): ByteArray {
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
    return input
}

private fun writeFile(name: String, data: String) {
    val file = fopen(name, "w")
    memScoped { fprintf(file, data) }
    fclose(file)
}
