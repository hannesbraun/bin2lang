# bin2lang

bin2lang is a command line tool to turn a binary file into a language specific data structure (e.g. a constant C array).
## Building

Run `./gradlew linkReleaseExecutableMacosX64` to build the executable for macOS.
Use `linkReleaseExecutableLinuxX64` or `linkReleaseExecutableMingwX64` if you want to build for Linux or Windows instead.
You will find the generated executable in the `build/bin/<target platform>/releaseExecutable` directory.

## Usage

Basic syntax:
```bash
bin2lang <target language> <binary file> <target name>
```

- `<target language>` specifies the language you want to convert the binary file to
- `<binary file>` is the binary file you want to convert
- `<target name>` is the name of the data structure to generate

## Supported languages

| Language | Language key | Notes |
| -------- | ------------ | ----- |
| C        | `c`          |       |
| Go       | `go`         | The default package is `main`. Change this according to your needs. |
| Java     | `java`       |       |
| Kotlin   | `kotlin`     |       |
| Rust     | `rust`       |       |

*Note: the language keys are not case-sensitive.*

## Contributing

Want to contribute a feature? Cool!

1. Fork it (<https://github.com/hannesbraun/bin2lang/fork>)
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create a new Pull Request

## Contributors
- [Hannes Braun](https://github.com/hannesbraun) - creator and maintainer
