#!/bin/sh

# C
cd c
make
./ctest
cd -

# Compare results (manually)
shasum -a 512 randData
shasum -a 512 cOutFile
