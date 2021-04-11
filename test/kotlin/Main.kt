import java.io.File
import java.nio.file.Files

fun main() {
	Files.write(File("../kotlinOutFile").toPath(), RandomData.bytes.toByteArray())
}
