package main

import "io/ioutil"

func main() {
    ioutil.WriteFile("../goOutFile", randomData, 0644)
}
