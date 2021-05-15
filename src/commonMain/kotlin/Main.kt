import writers.*

expect fun readFile(name: String): UByteArray
expect fun writeFile(name: String, data: String)
expect fun printlnErr(string: String?)

fun main(args: Array<String>) {
    val args1 = try {
        ProgramArguments(args)
    } catch (e: IllegalArgumentException) {
        printlnErr(e.message)
        return
    }

    val writer = when (args1.language) {
        "c" -> CWriter(args1.target)
        "go" -> GoWriter(args1.target)
        "java" -> JavaWriter(args1.target)
        "kotlin" -> KotlinWriter(args1.target)
        else -> {
            printlnErr("ERROR: unknown target: ${args1.target}")
            return
        }
    }

    writer.bytes = readFile(args1.binaryFile)
    val langString = writer.toLang()
    writeFile(writer.filename, langString)
}
