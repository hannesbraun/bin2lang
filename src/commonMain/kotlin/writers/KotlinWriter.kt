package writers

class KotlinWriter(name: String) : Writer(name, "kt") {
    override fun toLang(): String {
        val langStrBuilder = StringBuilder()

        langStrBuilder.append("object $name {\n    ")
        langStrBuilder.append("val bytes = listOf(\n        ")
        for ((i, byte) in bytes.withIndex()) {
            langStrBuilder.append("${byte.hexString()}.toByte()")

            if (i + 1 < bytes.size) {
                langStrBuilder.append(",")

                if ((i + 1) % 5 == 0) {
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
