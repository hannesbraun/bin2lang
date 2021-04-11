package writers

class JavaWriter(name: String) : Writer(name, "java") {
    override fun toLang(): String {
        val langStrBuilder = StringBuilder()

        langStrBuilder.append("public class $name {\n    ")
        langStrBuilder.append("public static final byte[] BYTES = new byte[] {\n        ")
        for ((i, byte) in bytes.toByteArray().withIndex()) {
            langStrBuilder.append("${byte.toString(10)}")

            if (i + 1 < bytes.size) {
                langStrBuilder.append(",")

                if ((i + 1) % 12 == 0) {
                    langStrBuilder.append("\n        ")
                } else {
                    langStrBuilder.append(" ")
                }
            }
        }
        langStrBuilder.append("\n    };\n}\n")

        return langStrBuilder.toString()
    }
}
