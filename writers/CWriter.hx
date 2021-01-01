package writers;

import haxe.io.Bytes;

class CWriter extends Writer {
    public function new(name: String, bytes: Bytes) {
        super(name, bytes, "h");
    }

    override public function writeAndClose() {
        fileWriter.writeString("#pragma once\n\n");
        fileWriter.writeString("const unsigned char " + name + "[" + bytes.length + "] = {\n    ");

        for (i in 0...bytes.length) {
            final byte = bytes.getUInt16(i);
            final hexString = StringTools.hex(byte, 2);
            fileWriter.writeString("0x" + hexString.substring(hexString.length - 2, hexString.length));

            if (i + 1 < bytes.length) {
                fileWriter.writeString(",");

                if ((i + 1) % 12 == 0) {
                    fileWriter.writeString("\n    ");
                } else {
                    fileWriter.writeString(" ");
                }
            }
        }

        fileWriter.writeString("\n};\n\n");
        fileWriter.writeString("const int " + name + "_length = " + bytes.length + ";\n");
        super.writeAndClose();
    }
}
