package com.paierjulia.knuckelbones

open class Player(
    val name: String
){
    val diceField = DiceFieldController()
    var totalPoints: Int = 0
}