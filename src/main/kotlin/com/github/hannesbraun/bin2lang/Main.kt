package com.github.hannesbraun.bin2lang


import com.github.hannesbraun.bin2lang.writers.CWriter
import com.github.hannesbraun.bin2lang.writers.JavaWriter
import com.github.hannesbraun.bin2lang.writers.KotlinWriter
import java.io.File
import java.nio.file.Files


fun main(args: Array<String>) {
    if (args.size != 3) {
        System.err.println("ERROR: wrong number of arguments\n")
        return
    }

    val language = args[0].toLowerCase()
    val binaryFile = args[1]
    val target = args[2]

    val writer = when (language) {
        "c" -> CWriter(target)
        "java" -> JavaWriter(target)
        "kotlin" -> KotlinWriter(target)
        else -> {
            System.err.println("ERROR: unknown target: ${args[0]}\n")
            return
        }
    }

    writer.bytes = Files.readAllBytes(File(binaryFile).toPath())
    val langString = writer.toLang()
    Files.writeString(File(writer.filename).toPath(), langString)
}
