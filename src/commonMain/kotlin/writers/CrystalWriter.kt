package writers

class CrystalWriter(name: String) : Writer(name, "cr") {
    override fun toLang(): String {
        val langStrBuilder = StringBuilder()

        langStrBuilder.append("${name.uppercase()} = [\n  ")
        for ((i, byte) in bytes.withIndex()) {
            langStrBuilder.append(byte.hexString())

            if (i + 1 < bytes.size) {
                langStrBuilder.append(",")

                if ((i + 1) % 13 == 0) {
                    langStrBuilder.append("\n  ")
                } else {
                    langStrBuilder.append(" ")
                }
            }
        }
        langStrBuilder.append("\n] of UInt8\n")

        return langStrBuilder.toString()
    }
}
