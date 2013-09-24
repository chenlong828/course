/**
 * User: ChenLong
 * Created Date: 8/11/13 5:30 下午
 * Description: 
 */
package main

import "fmt"

var x, y, z int = 1, 2, 3
var c, python, java = true, false, "no!"

func variable_test() {
	x = y + z
	fileName := "hello, world"
	fmt.Println(x)
	fmt.Println(y)
	fmt.Println(fileName)
}

func array_test() {
	array := [10]byte{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'}
	fmt.Println(array[:5])
	fmt.Println(len(array))

	var my_slice []byte
	my_slice = array[2:]
	fmt.Println(my_slice)

}

func main() {

	variable_test()
	array_test()
}
