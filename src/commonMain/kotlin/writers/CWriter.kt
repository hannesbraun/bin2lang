package writers

class CWriter(name: String) : Writer(name, "h") {
    override fun toLang(): String {
        val langStrBuilder = StringBuilder()

        langStrBuilder.append("#ifndef ${name.uppercase()}_${fileExtension.uppercase()}\n")
        langStrBuilder.append("#define ${name.uppercase()}_${fileExtension.uppercase()}\n\n")

        langStrBuilder.append("const int ${name}_length = ${bytes.size};\n\n")
        langStrBuilder.append("const unsigned char $name[${bytes.size}] = {\n    ")

        for ((i, byte) in bytes.withIndex()) {
            langStrBuilder.append(byte.hexString())

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
        langStrBuilder.append("#endif\n")

        return langStrBuilder.toString()
    }
}
