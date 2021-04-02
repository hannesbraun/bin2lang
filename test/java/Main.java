import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
	public static void main(String[] args) {
		try {
			Files.write(new File("../javaOutFile").toPath(), RandomData.BYTES);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

