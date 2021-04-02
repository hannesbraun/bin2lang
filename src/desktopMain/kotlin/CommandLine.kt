import platform.posix.fprintf
import platform.posix.stderr

actual fun printlnErr(string: String?) {
    if (string != null)
        fprintf(stderr, "$string\n")
}
