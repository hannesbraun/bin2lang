package writers

class CWriter(name: String) : Writer(name, "h") {
    override fun toLang(): String {
        val langStrBuilder = StringBuilder()

        langStrBuilder.append("#pragma once\n\n")
        langStrBuilder.append("const unsigned char $name[${bytes.size}] = {\n    ")

        for ((i, byte) in bytes.withIndex()) {
            val hexString = byte.toString(16)
            langStrBuilder.append("0x$hexString")

            if (i + 1 < bytes.size) {
                langStrBuilder.append(",")

                if ((i + 1) % 12 == 0) {
                    langStrBuilder.append("\n    ")
                } else {
                    langStrBuilder.append(" ")
                }
            }
        }

        langStrBuilder.append("\n};\n\n")
        langStrBuilder.append("const int ${name}_length = ${bytes.size};\n")

        return langStrBuilder.toString()
    }
}