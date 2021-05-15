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

# Compare results (manually)
shasum -a 512 randData
shasum -a 512 cOutFile
shasum -a 512 javaOutFile
shasum -a 512 kotlinOutFile
shasum -a 512 goOutFile
