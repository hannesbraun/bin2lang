class ProgramArguments(args: Array<String>) {
    init {
        if (args.size != 3) {
            throw IllegalArgumentException("ERROR: wrong number of arguments\n")
        }
    }

    val language = args[0].lowercase()
    val binaryFile = args[1]
    val target = args[2]
}
