import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import writers.*

object Metadata {
    const val NAME = "bin2lang"
    const val VERSION = "1.1.0"
}

fun main(args: Array<String>) {
    val parser = ArgParser(Metadata.NAME)
    val language by parser.argument(ArgType.String, description = "Language to convert the binary to")
    val binaryFile by parser.argument(ArgType.String, description = "Binary file to convert")
    val target by parser.argument(ArgType.String, description = "Target data structure name")
    val about by parser.option(ArgType.Boolean, description = "Show about message (includes the version of bin2lang)").default(false)
    parser.parse(args)

    // It's maybe kind of inconvenient to require specifying the usual arguments for printing the about message.
    // But since this option won't effectively be used a lot anyway... I don't care for now. Maybe kotlinx-cli will provide a solution in the future.
    if (about) {
        println(
            """
            ${Metadata.NAME} ${Metadata.VERSION}
            Copyright (C) 2021 Hannes Braun
                
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
        "crystal" -> CrystalWriter(target)
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
