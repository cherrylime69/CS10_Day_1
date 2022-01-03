fun main(args: Array<String>) {

    val half = halfAdder(true,false)
    println("반쪽 계산 자리올림: ${half[0]}, 합: ${half[1]}")

    val full = fullAdder(true,false,true)
    println("전체 덧셈 자리올림: ${full[0]}, 합: ${full[1]}")

    val byteA = arrayOf(true,true,false,true,true,false,true,false)
    val byteB = arrayOf(true,false,true,true,false,false,true,true)
    println(byteAdder(byteA,byteB))
    val byteC = arrayOf(true,true,false,false,true,false,true,false)
    val byteD = arrayOf(true,true,false,true,true,false,false,true)
    println(byteAdder(byteC,byteD))
}

//논리 게이트 역할을 하는 함수
fun and(bitA:Boolean, bitB:Boolean) = (bitA) && (bitB)

fun or(bitA:Boolean, bitB:Boolean) = (bitA) || (bitB)

fun nand(bitA:Boolean, bitB:Boolean) = !((bitA) && (bitB))

fun xor(bitA:Boolean, bitB:Boolean) = (bitA != bitB)

//반쪽덧셈
fun halfAdder(bitA:Boolean, bitB:Boolean): Array<Boolean> {
    return arrayOf(and(bitA, bitB), xor(bitA, bitB))
}

//전체덧셈
fun fullAdder(bitA:Boolean, bitB:Boolean, carry:Boolean) = arrayOf(or(or(and(bitA,bitB),and(bitB,carry)),and(bitA,carry)),xor(xor(bitA,bitB),carry))


//1바이트 덧셈기
fun byteAdder(byteA:Array<Boolean>, byteB:Array<Boolean>): MutableList<Boolean> {
    var temArray: Array<Boolean>
    var temCarry = false
    val resultList = MutableList<Boolean>(9){false}

    for (index in byteA.indices) {
        temArray = fullAdder(byteA[index], byteB[index], temCarry)
        resultList[index] = temArray[1]
        temCarry = temArray[0]
        if (index == byteA.size-1) resultList[index+1] = temCarry
    }
    return resultList
}