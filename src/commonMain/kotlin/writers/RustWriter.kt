package writers

class RustWriter(name: String) : Writer(name, "rs") {
    override fun toLang(): String {
        val langStrBuilder = StringBuilder()

        langStrBuilder.append("pub const ${name.uppercase()}: [u8; ${bytes.size}] = [\n    ")

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

        langStrBuilder.append("\n];\n")

        return langStrBuilder.toString()
    }
}
