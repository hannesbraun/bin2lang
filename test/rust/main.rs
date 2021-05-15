mod random_data;

use std::fs;

fn main() {
    fs::write("../rustOutFile", &random_data::RANDOM_DATA).unwrap();
}
