package ru.itis.summerpractice.myfirstapp




fun main(){
    val example: List<Int> = listOf(1,5,2,7,2,1,7,10)
    println(analizeIntList(example))
    println(passwordAnalize("TestPassword13134!441"))
}
fun analizeIntList(input: List<Int>) : String{
    var maximum = input[0]
    var minimum = input[0]
    var sum = 0
    var chet = 0
    var nechet = 0
    for(number in input){
        if (number%2 ==0){
            chet++
        }
        else{
            nechet++
        }
    }

    for (number in input){
        sum+=number
        if (number < minimum){
            minimum = number
        }
        if (number > maximum){
            maximum = number
        }
    }
    return "Max: " + maximum +
            "\nMin: " + minimum + "\nSum: " + sum + "\nchet: " + chet + "\nnechet: " + nechet
}
fun passwordAnalize(password: String) : String{
    var conditions = 0

    if (password.length >= 8){
        conditions++
    }
    if (password.any { it.isDigit() }){
        conditions++
    }
    if(password.any {it.isUpperCase()}){
        conditions++
    }
    if (password.any(){it.isLowerCase()}){
        conditions++
    }
    if(password.any(){!it.isLetter() && !it.isDigit()}){
        conditions++
    }
    if (conditions == 5){
        return "Password: reliable"
    }
    if (conditions == 4){
        return "Password: good"
    }
    if(conditions >= 2){
        return "Password: medium"
    }
    return "Password is not reliable"
}





