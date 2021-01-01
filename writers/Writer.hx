package writers;

import haxe.io.Bytes;
import sys.io.File;
import sys.io.FileOutput;

class Writer {
    private final fileExtension: String;
    private final fileWriter: FileOutput;
    private final name: String;
    private final bytes: Bytes;
    
    public function new(name: String, bytes: Bytes, fileExtension: String) {
        this.fileExtension = fileExtension;
        this.name = name;
        this.bytes = bytes;
        this.fileWriter = File.write(name + "." + fileExtension, true);
    }

    public function writeAndClose() {
        fileWriter.flush();
        fileWriter.close();
    }
}
