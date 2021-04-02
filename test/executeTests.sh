#!/bin/sh

# C
cd c
make
./ctest
cd -

# Java
cd java
make
java Main
cd -

# Compare results (manually)
shasum -a 512 randData
shasum -a 512 cOutFile
shasum -a 512 javaOutFile
