package com.programmerworld.choruschecker.Object

import com.programmerworld.choruschecker.Object.Variables.Colgandoentusmanos
import com.programmerworld.choruschecker.Object.Variables.Cuandomeenamoro
import com.programmerworld.choruschecker.Object.Variables.Lethergo
import com.programmerworld.choruschecker.Object.Variables.Loveinthedark
import com.programmerworld.choruschecker.Object.Variables.Mifidodite
import com.programmerworld.choruschecker.Object.Variables.Nonmelospiegare
import com.programmerworld.choruschecker.Object.Variables.Photograph

object BotResponse {

    fun basicResponses(_message: String, song:String, question:Int): String {

        val message = _message.toLowerCase()

        when (question) {
            1 -> {
                when (song) {
                    "Let Her Go" -> {
                        if (message.compareTo("light") == 0){
                            Lethergo++
                            return good()
                        }
                        else return wrong()
                    }
                    "Love in the dark" -> {
                        if (message.compareTo("kind") == 0){
                            Loveinthedark++
                            return good()
                        }
                        else return wrong()
                    }
                    "Photograph" -> {
                        if (message.compareTo("photograph") == 0 || message.compareTo("in a photograph") == 0){
                            Photograph++
                            return good()
                        }
                        else return wrong()
                    }
                    "Colgando en tus manos" -> {
                        if (message.compareTo("poemas") == 0){
                            Colgandoentusmanos++
                            return good()
                        }
                        else return wrong()
                    }
                    "Cuando me enamoro" -> {
                        if (message.compareTo("desespero") == 0){
                            Cuandomeenamoro++
                            return good()
                        }
                        else return wrong()
                    }
                    "Mi fido di te" -> {
                        if (message.compareTo("male") == 0){
                            Mifidodite++
                            return good()
                        }
                        else return wrong()
                    }
                    "Non me lo so spiegare" -> {
                        if (message.compareTo("farneticare") == 0){
                            Nonmelospiegare++
                            return good()
                        }
                        else return wrong()
                    }
                    else -> {
                        return "Sorry, there must be a system error, song $song"
                    }
                }
            }
            2 -> {
                when (song) {
                    "Let Her Go" -> {
                        if (message.compareTo("snow") == 0){
                            Lethergo++
                            return good()
                        }
                        else return wrong()
                    }
                    "Love in the dark" -> {
                        if (message.compareTo("space") == 0){
                            Loveinthedark++
                            return good()
                        }
                        else return wrong()
                    }
                    "Photograph" -> {
                        if (message.compareTo("ourselves") == 0){
                            Photograph++
                            return good()
                        }
                        else return wrong()
                    }
                    "Colgando en tus manos" -> {
                        if (message.compareTo("marbella") == 0){
                            Colgandoentusmanos++
                            return good()
                        }
                        else return wrong()
                    }
                    "Cuando me enamoro" -> {
                        if (message.compareTo("espero") == 0){
                            Cuandomeenamoro++
                            return good()
                        }
                        else return wrong()
                    }
                    "Mi fido di te" -> {
                        if (message.compareTo("d'un fiato") == 0){
                            Mifidodite++
                            return good()
                        }
                        else return wrong()
                    }
                    "Non me lo so spiegare" -> {
                        if (message.compareTo("inverno") == 0){
                            Nonmelospiegare++
                            return good()
                        }
                        else return wrong()
                    }
                    else -> {
                        return "Sorry, there must be a system error, song $song"
                    }
                }
            }
            3 -> {
                when (song) {
                    "Let Her Go" -> {
                        if (message.compareTo("road") == 0){
                            Lethergo++
                            return good()
                        }
                        else return wrong()
                    }
                    "Love in the dark" -> {
                        if (message.compareTo("oceans") == 0){
                            Loveinthedark++
                            return good()
                        }
                        else return wrong()
                    }
                    "Photograph" -> {
                        if (message.compareTo("frozen") == 0){
                            Photograph++
                            return good()
                        }
                        else return wrong()
                    }
                    "Colgando en tus manos" -> {
                        if (message.compareTo("venezuela") == 0){
                            Colgandoentusmanos++
                            return good()
                        }
                        else return wrong()
                    }
                    "Cuando me enamoro" -> {
                        if (message.compareTo("tiempo") == 0 || message.compareTo("el tiempo") == 0){
                            Cuandomeenamoro++
                            return good()
                        }
                        else return wrong()
                    }
                    "Mi fido di te" -> {
                        if (message.compareTo("burrone") == 0 || message.compareTo("un burrone") == 0){
                            Mifidodite++
                            return good()
                        }
                        else return wrong()
                    }
                    "Non me lo so spiegare" -> {
                        if (message.compareTo("viaggi") == 0){
                            Nonmelospiegare++
                            return good()
                        }
                        else return wrong()
                    }
                    else -> {
                        return "Sorry, there must be a system error, song $song"
                    }
                }
            }
            4 -> {
                when (song) {
                    "Let Her Go" -> {
                        if (message.compareTo("low") == 0){
                            Lethergo++
                            return good()
                        }
                        else return wrong()
                    }
                    "Love in the dark" -> {
                        if (message.compareTo("everything") == 0){
                            Loveinthedark++
                            return good()
                        }
                        else return wrong()
                    }
                    "Photograph" -> {
                        if (message.compareTo("meet") == 0){
                            Photograph++
                            return good()
                        }
                        else return wrong()
                    }
                    "Colgando en tus manos" -> {
                        if (message.compareTo("manos") == 0){
                            Colgandoentusmanos++
                            return good()
                        }
                        else return wrong()
                    }
                    "Cuando me enamoro" -> {
                        if (message.compareTo("alma") == 0){
                            Cuandomeenamoro++
                            return good()
                        }
                        else return wrong()
                    }
                    "Mi fido di te" -> {
                        if (message.compareTo("cadere") == 0){
                            Mifidodite++
                            return good()
                        }
                        else return wrong()
                    }
                    "Non me lo so spiegare" -> {
                        if (message.compareTo("sognare") == 0 || message.compareTo("di sognare") == 0){
                            Nonmelospiegare++
                            return good()
                        }
                        else return wrong()
                    }
                    else -> {
                        return "Sorry, there must be a system error, song $song"
                    }
                }
            }
            5 -> {
                when (song) {
                    "Let Her Go" -> {
                        if (message.compareTo("go") == 0){
                            Lethergo++
                            return good()
                        }
                        else return wrong()
                    }
                    "Love in the dark" -> {
                        if (message.compareTo("defeated") == 0){
                            Loveinthedark++
                            return good()
                        }
                        else return wrong()
                    }
                    "Photograph" -> {
                        if (message.compareTo("alone") == 0){
                            Photograph++
                            return good()
                        }
                        else return wrong()
                    }
                    "Colgando en tus manos" -> {
                        if (message.compareTo("cuidado") == 0){
                            Colgandoentusmanos++
                            return good()
                        }
                        else return wrong()
                    }
                    "Cuando me enamoro" -> {
                        if (message.compareTo("sonrío") == 0){
                            Cuandomeenamoro++
                            return good()
                        }
                        else return wrong()
                    }
                    "Mi fido di te" -> {
                        if (message.compareTo("volare") == 0){
                            Mifidodite++
                            return good()
                        }
                        else return wrong()
                    }
                    "Non me lo so spiegare" -> {
                        if (message.compareTo("finire") == 0){
                            Nonmelospiegare++

                            return good()
                        }
                        else return wrong()
                    }
                    else -> {
                        return "Sorry, there must be a system error, song $song"
                    }
                }
            }
            6 -> {
                return "You've finished the questions for this song"
            }
            else -> {
                return "Sorry, there must be a system error number of question: number $question"
            }
        }
    }

    fun good() :String {
        val random = (0..2).random()
        return when(random) {
            0 -> "Perfect!"
            1 -> "Right, good!"
            2 -> "Lets go, right!"
            else -> "Error"
        }
    }

    fun wrong() :String {
        val random = (0..2).random()
        return when(random) {
            0 -> "Oh no, that's not correct"
            1 -> "It's not correct but the next one will get better"
            2 -> "Not right, but don't get down on yourself"
            else -> "Error"
        }
    }

    fun basicQuestion( song:String, question:Int): String {
        when (question) {
            1 -> {
                when (song) {
                    "Let Her Go" -> {
                        return "1 - What do you need when it's burning low?"
                    }
                    "Love in the dark" -> {
                        return "1 - I'm being cruel to be ...?"
                    }
                    "Photograph" -> {
                        return "1 - Where do we keep this love?"
                    }
                    "Colgando en tus manos" -> {
                        return "1 - Te envio ... de mi puño y letra"
                    }
                    "Cuando me enamoro" -> {
                        return "1 - A veces ..."
                    }
                    "Mi fido di te" -> {
                        return "1- Forse fa ... eppure mi va"
                    }
                    "Non me lo so spiegare" -> {
                        return "1 - Pensava a quanto è inutile fare cosa?"
                    }
                    else -> {
                        return "Sorry, there must be a system error, question $question "
                    }
                }
            }
            2 -> {
                when (song) {
                    "Let Her Go" -> {
                        return "2 - You only miss the sun when it starts to ..."
                    }
                    "Love in the dark" -> {
                        return "2 - What's between us?"
                    }
                    "Photograph" -> {
                        return "2 - We made these memories for ..."
                    }
                    "Colgando en tus manos" -> {
                        return "2 - En que lugar estamos cenando en las fotos que te envio?"
                    }
                    "Cuando me enamoro" -> {
                        return "2 - Cuando menos me lo ... me enamoro"
                    }
                    "Mi fido di te" -> {
                        return "2 - Come vuole vivere?"
                    }
                    "Non me lo so spiegare" -> {
                        return "2 - E credere di stare bene quando è ... e te"
                    }
                    else -> {
                        return "Sorry, there must be a system error, question $question "
                    }
                }
            }
            3 -> {
                when (song) {
                    "Let Her Go" -> {
                        return "3 - Only hate the ... when you're missing home"
                    }
                    "Love in the dark" -> {
                        return "3 - It feels like we're ... apart"
                    }
                    "Photograph" -> {
                        return "3 - How is time?"
                    }
                    "Colgando en tus manos" -> {
                        return "3 - Y cuando estuvimos por ..."
                    }
                    "Cuando me enamoro" -> {
                        return "3 - Que es lo que se detiene?"
                    }
                    "Mi fido di te" -> {
                        return "3 - Sopra cosa vuole stendersi?"
                    }
                    "Non me lo so spiegare" -> {
                        return "3 - Case, libri, auto, ... , fogli di giornale"
                    }
                    else -> {
                        return "Sorry, there must be a system error, question $question "
                    }
                }
            }
            4 -> {
                when (song) {
                    "Let Her Go" -> {
                        return "4- How do you feel when you know you've been high? "
                    }
                    "Love in the dark" -> {
                        return "4 - What changed me?"
                    }
                    "Photograph" -> {
                        return "4- Holding me closer 'til our eyes ..."
                    }
                    "Colgando en tus manos" -> {
                        return "4 - Mi corazón esta colgando en tus ..."
                    }
                    "Cuando me enamoro" -> {
                        return "4 - Me viene el ... al cuerpo"
                    }
                    "Mi fido di te" -> {
                        return "4 - La vertigine non è paura di ..."
                    }
                    "Non me lo so spiegare" -> {
                        return "4 - Cosa gli permette di fare?"
                    }
                    else -> {
                        return "Sorry, there must be a system error, question $question "
                    }
                }
            }
            5 -> {
                when (song) {
                    "Let Her Go" -> {
                        return "5 - And you let her ..."
                    }
                    "Love in the dark" -> {
                        return "5 - Baby, we're already ..."
                    }
                    "Photograph" -> {
                        return "5 - You won't ever be ..., wait for me to come home"
                    }
                    "Colgando en tus manos" -> {
                        return "5 - Que tienes que tener?"
                    }
                    "Cuando me enamoro" -> {
                        return "5 - Y luego que hago?"
                    }
                    "Mi fido di te" -> {
                        return "5 - Ma voglia di ..."
                    }
                    "Non me lo so spiegare" -> {
                        return "5 - Ma vuoi dirmi come questo può ...?"
                    }
                    else -> {
                        return "Sorry, there must be a system error, question $question "
                    }
                }
            }
            6 -> {
                return "You've already finished the questions for this song"
            }
            else -> {
                return "Sorry, there must be a system error number question: number $question "
            }
        }
    }
}