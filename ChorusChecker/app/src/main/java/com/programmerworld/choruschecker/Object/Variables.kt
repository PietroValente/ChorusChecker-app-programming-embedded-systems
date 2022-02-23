package com.programmerworld.choruschecker.Object

object Variables {

    //MediaControlsActions
    val ACTION_FORWARD = "FORWARD"
    val ACTION_BACKWARD = "BACKWARD"
    val ACTION_PLAY = "PLAY"

    //QuestionsIntVariables
    var Loveinthedark: Int = -1
    var Lethergo: Int = -1
    var Photograph: Int = -1
    var Colgandoentusmanos: Int = -1
    var Cuandomeenamoro: Int = -1
    var Mifidodite: Int = -1
    var Nonmelospiegare: Int = -1

    fun QuestionsIntVariables(name: String) :Int {
        when(name){
            "Let Her Go" ->{
                return Lethergo
            }
            "Love in the dark" ->{
                return Loveinthedark
            }
            "Photograph" ->{
                return Photograph
            }
            "Colgando en tus manos" ->{
                return Colgandoentusmanos
            }
            "Cuando me enamoro" ->{
                return Cuandomeenamoro
            }
            "Mi fido di te" ->{
                return Mifidodite
            }
            "Non me lo so spiegare" ->{
                return Nonmelospiegare
            }
            else ->{
                return -1
            }
        }
    }

}