package com.github.hannesbraun.bin2lang.writers

class KotlinWriter(name: String) : Writer(name, "kt") {
    override fun toLang(): String {
        val langStrBuilder = StringBuilder()

        langStrBuilder.append("object $name {\n    ")
        langStrBuilder.append("val bytes = listOf(\n        ")
        for ((i, byte) in bytes.withIndex()) {
            val hexString = byte.toString(16).padStart(2, '0')
            langStrBuilder.append("0x$hexString")

            if (i + 1 < bytes.size) {
                langStrBuilder.append(",")

                if ((i + 1) % 12 == 0) {
                    langStrBuilder.append("\n        ")
                } else {
                    langStrBuilder.append(" ")
                }
            }
        }
        langStrBuilder.append("\n    )\n}\n")

        return langStrBuilder.toString()
    }
}
