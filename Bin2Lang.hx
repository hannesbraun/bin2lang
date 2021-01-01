import writers.CWriter;
import sys.io.File;

class Bin2Lang {
    static public function main() {
        final args = Sys.args();

        if (args.length != 3) {
            Sys.stderr().writeString("ERROR: wrong number of arguments\n");
            return;
        }

        final target = switch (args[0].toLowerCase()) {
            case "c": Target.C;
            default:
                Sys.stderr().writeString("ERROR: unknown target: " + args[0] + "\n");
                return;
        }

        final bytes = File.getBytes(args[1]);
        final writer = switch (target) {
            case Target.C: new CWriter(args[2], bytes);
        }
        writer.writeAndClose();
    }
}
