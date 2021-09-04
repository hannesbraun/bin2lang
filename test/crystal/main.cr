require "./random_data.cr"

file = File.new("../crystalOutFile", "w")
ptr = RANDOM_DATA.to_unsafe.as(UInt8*)
file.write(ptr.to_slice(RANDOM_DATA.size))
file.close
