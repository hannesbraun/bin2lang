package writers

class GoWriter(name: String) : Writer(name, "go") {
    override fun toLang(): String {
        val langStrBuilder = StringBuilder()

        langStrBuilder.append("package main\n\n")
        langStrBuilder.append("var $name = []byte {\n    ")
        for ((i, byte) in bytes.withIndex()) {
            langStrBuilder.append(byte.hexString())
            langStrBuilder.append(",")

            if (i + 1 < bytes.size) {
                if ((i + 1) % 12 == 0) {
                    langStrBuilder.append("\n    ")
                } else {
                    langStrBuilder.append(" ")
                }
            }
        }
        langStrBuilder.append("\n}\n")

        return langStrBuilder.toString()
    }
}
