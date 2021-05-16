import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import writers.*

object Metadata {
    const val NAME = "bin2lang"
    const val VERSION = "1.0.0-SNAPSHOT"
}

fun main(args: Array<String>) {
    val parser = ArgParser(Metadata.NAME)
    val language by parser.argument(ArgType.String, description = "Language to convert the binary to")
    val binaryFile by parser.argument(ArgType.String, description = "Binary file to convert")
    val target by parser.argument(ArgType.String, description = "Target data structure name")
    val verbose by parser.option(ArgType.Boolean, shortName = "v", description = "Turn on verbose mode").default(false)
    parser.parse(args)

    if (verbose) {
        println("${Metadata.NAME} ${Metadata.VERSION}")
        println("Copyright (C) 2021 Hannes Braun")
        println()
        println(
            """
            This program is free software: you can redistribute it and/or modify
            it under the terms of the GNU General Public License as published by
            the Free Software Foundation, either version 3 of the License, or
            (at your option) any later version.

            This program is distributed in the hope that it will be useful,
            but WITHOUT ANY WARRANTY; without even the implied warranty of
            MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
            GNU General Public License for more details.

            You should have received a copy of the GNU General Public License
            along with this program.  If not, see <https://www.gnu.org/licenses/>.
            
        """.trimIndent()
        )
    }

    val writer = when (language) {
        "c" -> CWriter(target)
        "go" -> GoWriter(target)
        "java" -> JavaWriter(target)
        "kotlin" -> KotlinWriter(target)
        "rust" -> RustWriter(target)
        else -> {
            printlnErr("ERROR: unknown language: $language")
            return
        }
    }

    writer.bytes = readFile(binaryFile)
    val langString = writer.toLang()
    writeFile(writer.filename, langString)
}

expect fun readFile(name: String): UByteArray
expect fun writeFile(name: String, data: String)
expect fun printlnErr(string: String?)

class ReadException(msg: String): Exception(msg)
class WriteException(msg: String): Exception(msg)
