#!/bin/sh

rm randData
rm cOutFile
rm javaOutFile

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

# Compare results (manually)
shasum -a 512 randData
shasum -a 512 cOutFile
shasum -a 512 javaOutFile
