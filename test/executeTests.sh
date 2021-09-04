#!/bin/sh

rm randData
rm cOutFile
rm javaOutFile
rm kotlinOutFile

bash randFile.sh

BIN2LANG=../build/bin/macosX64/releaseExecutable/bin2lang.kexe

# C
$BIN2LANG c randData randomData
rm c/randomData.h
mv randomData.h c
cd c
make
./ctest
cd -

# Java
$BIN2LANG java randData RandomData
rm java/RandomData.java
mv RandomData.java java
cd java
make
java Main
cd -

# Kotlin
$BIN2LANG kotlin randData RandomData
rm kotlin/RandomData.kt
mv RandomData.kt kotlin
cd kotlin
make
kotlin MainKt
cd -

# Go
$BIN2LANG go randData randomData
rm go/randomData.go
mv randomData.go go
cd go
make
./goTest
cd -

# Rust
$BIN2LANG rust randData random_data
rm rust/random_data.rs
mv random_data.rs rust
cd rust
make
./rustTest
cd -

# Crystal
$BIN2LANG crystal randData random_data
rm crystal/random_data.cr
mv random_data.cr crystal
cd crystal
make
./crystalTest
cd -

# Compare results (manually)
shasum -a 512 randData
shasum -a 512 cOutFile
shasum -a 512 javaOutFile
shasum -a 512 kotlinOutFile
shasum -a 512 goOutFile
shasum -a 512 rustOutFile
shasum -a 512 crystalOutFile
