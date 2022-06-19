package minesweeper
import kotlin.random.Random

var hod = 0 // a variable that stores the number of player moves
var vzorMins = 0 // a variable that stores the number of detonated mines
var lishZvez = 0 // a variable that stores the number of cells marked by the player that do not contain a mine
var fundMins = 0 // a variable that stores the number of mines found by the player
var mes1 = 0 // a variable needed for a list that stores cells that already have a mine
var mes2 = 0 // a variable needed for a list that stores cells that already have a mine
var ustMins = 0 // a variable that stores the number of installed mines
var minsVokr = 0 // a variable that stores the number of mines that are around the cell
var mins = 0 // a variable that stores the number of mines the player wants to plant
val mesto = Random.Default

val mesList = mutableListOf<String>() // a list that stores the coordinates where the mine is placed

val pole = mutableListOf( // a list that stores the state of the playing field
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", ".")
)

var poleCopy = mutableListOf( // list that is displayed to the player
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", "."),
    mutableListOf<String>(".", ".", ".", ".", ".", ".", ".", ".", ".")
)

fun start(): Int { // function to start the game
    print("How many mines do you want on the field?")
    mins = readln().toInt()
    return mins
}

fun ustanovka(mins: Int, y: Int , x: Int) { // a function that distributes mines across the field in random order
    if (mins >= 40) {
        mesList.clear()
        mesList.add("$y $x")
        while (ustMins !== mins) {
            mes1 = mesto.nextInt(0,9)
            mes2 = mesto.nextInt(0,9)
            if ("$mes1 $mes2" !in mesList) {
                ustMins++
                pole[mes1][mes2] = "X"
            }
            mesList.add("$mes1 $mes2")
        }
    }
    else {
        while (ustMins !== mins) {
            mes1 = mesto.nextInt(0,9)
            mes2 = mesto.nextInt(0,9)
            if ("$mes1 $mes2" !in mesList) {
                ustMins++
                pole[mes1][mes2] = "X"
            }
            mesList.add("$mes1 $mes2")
        }
    }
    ustMins = 0
}

fun okrug() { // a function that counts the number of mines around each field cell
    for (y in 0..8) {
        for (x in 0..8){
            when {
                "X" in pole[y][x] -> continue
                y == 0 && x ==0 -> {
                    if ("X" in pole[1][0]) minsVokr++
                    if ("X" in pole[1][1]) minsVokr++
                    if ("X" in pole[0][1]) minsVokr++
                    if (minsVokr == 0) continue else pole[0][0] = minsVokr.toString()
                }
                y == 0 && x ==8 -> {
                    if ("X" in pole[1][8]) minsVokr++
                    if ("X" in pole[1][7]) minsVokr++
                    if ("X" in pole[0][7]) minsVokr++
                    if (minsVokr == 0) continue else pole[0][8] = minsVokr.toString()
                }
                y == 8 && x ==0 -> {
                    if ("X" in pole[7][0]) minsVokr++
                    if ("X" in pole[7][1]) minsVokr++
                    if ("X" in pole[8][1]) minsVokr++
                    if (minsVokr == 0) continue else pole[8][0] = minsVokr.toString()
                }
                y == 8 && x ==8 -> {
                    if ("X" in pole[8][7]) minsVokr++
                    if ("X" in pole[7][7]) minsVokr++
                    if ("X" in pole[7][8]) minsVokr++
                    if (minsVokr == 0) continue else pole[8][8] = minsVokr.toString()
                }
                y == 0 && x > 0 && x < 8 -> {
                    if ("X" in pole[0][x - 1]) minsVokr++
                    if ("X" in pole[1][x - 1]) minsVokr++
                    if ("X" in pole[1][x]) minsVokr++
                    if ("X" in pole[1][x + 1]) minsVokr++
                    if ("X" in pole[0][x + 1]) minsVokr++
                    if (minsVokr == 0) continue else pole[0][x] = minsVokr.toString()
                }
                y == 8 && x > 0 && x < 8 -> {
                    if ("X" in pole[8][x - 1]) minsVokr++
                    if ("X" in pole[7][x - 1]) minsVokr++
                    if ("X" in pole[7][x]) minsVokr++
                    if ("X" in pole[7][x + 1]) minsVokr++
                    if ("X" in pole[8][x + 1]) minsVokr++
                    if (minsVokr == 0) continue else pole[8][x] = minsVokr.toString()
                }
                y > 0 && y < 8 && x == 0 -> {
                    if ("X" in pole[y - 1][0]) minsVokr++
                    if ("X" in pole[y - 1][1]) minsVokr++
                    if ("X" in pole[y][1]) minsVokr++
                    if ("X" in pole[y + 1][1]) minsVokr++
                    if ("X" in pole[y + 1][0]) minsVokr++
                    if (minsVokr == 0) continue else pole[y][0] = minsVokr.toString()
                }
                y > 0 && y < 8 && x == 8 -> {
                    if ("X" in pole[y - 1][8]) minsVokr++
                    if ("X" in pole[y - 1][7]) minsVokr++
                    if ("X" in pole[y][7]) minsVokr++
                    if ("X" in pole[y + 1][7]) minsVokr++
                    if ("X" in pole[y + 1][8]) minsVokr++
                    if (minsVokr == 0) continue else pole[y][8] = minsVokr.toString()
                }
                else -> {
                    if ("X" in pole[y][x - 1]) minsVokr++
                    if ("X" in pole[y - 1][x - 1]) minsVokr++
                    if ("X" in pole[y - 1][x]) minsVokr++
                    if ("X" in pole[y - 1][x + 1]) minsVokr++
                    if ("X" in pole[y][x + 1]) minsVokr++
                    if ("X" in pole[y + 1][x + 1]) minsVokr++
                    if ("X" in pole[y + 1][x]) minsVokr++
                    if ("X" in pole[y + 1][x - 1]) minsVokr++
                    if (minsVokr == 0) continue else pole[y][x] = minsVokr.toString()
                }
            }
            minsVokr = 0
        }
    }
}

fun slesch() { // a function that fills a field with "empty cells"
    for (y in 0..8) {
        for (x in 0..8){
            if (pole[y][x] == ".") pole[y][x] = "/"
        }
    }
}

fun copyMins() { // a function that reveals the coordinates of mines
    for (y in 0..8) {
        for (x in 0..8){
            if (pole[y][x] == "X") poleCopy[y][x] = "X"
        }
    }
}

fun clearPole() { // a function that clears a field
    for (y in 0..8) {
        for (x in 0..8){
            pole[y][x] = "."
        }
    }
}

fun output() { // a function that outputs a field for the player
    println(" │123456789│")
    println("—│—————————│")
    for (y in 0..8) {
        print(y+1)
        print("|")
        print(poleCopy[y].joinToString(""))
        println("|")
    }
    println("—│—————————│")
}

fun end() { // a function that prints the result of the game
    if (vzorMins == 1) println("You stepped on a mine and failed!") else println("Congratulations! You found all the mines!")
}

fun game() { // a function that contains the main algorithm of the game
    while ((fundMins !== mins || lishZvez !== 0) && (vzorMins == 0)) {
        print("Set/unset mines marks or claim a cell as free:")
        var (xn, yn, z) = readln().split(' ')

        hod++

        var x = xn.toInt() - 1
        var y = yn.toInt() - 1

        when {
            poleCopy[y][x] !== "." && poleCopy[y][x] !== "*" -> println("There is a number here!")
            poleCopy[y][x] == "." && pole[y][x] == "X" && z == "mine" -> {
                poleCopy[y][x] = "*"
                fundMins++
            }
            poleCopy[y][x] == "." && (pole[y][x] == "/" || (pole[y][x] !== "/" && pole[y][x] !== "X"))  && z == "mine" -> {
                poleCopy[y][x] = "*"
                lishZvez++
            }
            poleCopy[y][x] == "*" && pole[y][x] == "X" && z == "mine" -> {
                poleCopy[y][x] = "."
                fundMins--
            }
            poleCopy[y][x] == "*" && (pole[y][x] == "/" || (pole[y][x] !== "/" && pole[y][x] !== "X")) && z == "mine" -> {
                poleCopy[y][x] = "."
                lishZvez--
            }
            poleCopy[y][x] == "." && pole[y][x] == "X" && z == "free" && hod == 1 -> {
                clearPole()
                ustanovka(mins, y, x)
                okrug()
                slesch()
                poleCopy[y][x] = pole[y][x]
                if (pole[y][x] == "/") {
                    proyav(x, y)
                    repeat(2) {
                        scan()
                    }
                }
            }
            poleCopy[y][x] == "." && pole[y][x] == "X" && z == "free" && hod > 1 -> {
                poleCopy[y][x] = "X"
                vzorMins = 1
                copyMins()
            }
            poleCopy[y][x] == "." && pole[y][x] !== "/" && pole[y][x] !== "X" && z == "free" -> {
                poleCopy[y][x] = pole[y][x]
            }
            poleCopy[y][x] == "." && pole[y][x] == "/" && z == "free" -> {
                poleCopy[y][x] = "/"
                proyav(x, y)
                repeat(2) {
                    scan()
                }
            }
        }

        output()

    }
}

fun scan() { // a function that scans and uncovers the remaining cells
    for (y in 0..8) {
        for (x in 0..8){
            if ((poleCopy[y][x] == "." || poleCopy[y][x] == "*") && y - 1 >= 0) {
                if (pole[y - 1][x] == "/") {
                    poleCopy[y][x] = pole[y][x]
                }
            }
            if ((poleCopy[y][x] == "." || poleCopy[y][x] == "*") && y + 1 <= 8) {
                if (pole[y + 1][x] == "/") {
                    poleCopy[y][x] = pole[y][x]
                }
            }
            if ((poleCopy[y][x] == "." || poleCopy[y][x] == "*") && x - 1 >= 0) {
                if (pole[y][x - 1] == "/") {
                    poleCopy[y][x] = pole[y][x]
                }
            }
            if ((poleCopy[y][x] == "." || poleCopy[y][x] == "*") && x + 1 <= 8) {
                if (pole[y][x + 1] == "/") {
                    poleCopy[y][x] = pole[y][x]
                }
            }
            if ((poleCopy[y][x] == "." || poleCopy[y][x] == "*") && y - 1 >= 0 && x - 1 >= 0) {
                if (pole[y - 1][x - 1] == "/") {
                    poleCopy[y][x] = pole[y][x]
                }
            }
            if ((poleCopy[y][x] == "." || poleCopy[y][x] == "*") && y - 1 >= 0 && x + 1 <= 8) {
                if (pole[y - 1][x + 1] == "/") {
                    poleCopy[y][x] = pole[y][x]
                }
            }
            if ((poleCopy[y][x] == "." || poleCopy[y][x] == "*") && y + 1 <= 8 && x - 1 >= 0) {
                if (pole[y + 1][x - 1] == "/") {
                    poleCopy[y][x] = pole[y][x]
                }
            }
            if ((poleCopy[y][x] == "." || poleCopy[y][x] == "*") && y + 1 <= 8 && x + 1 <= 8) {
                if (pole[y + 1][x + 1] == "/") {
                    poleCopy[y][x] = pole[y][x]
                }
            }

        }
    }
}

fun proyav(x: Int, y: Int) { // a function that reveals all empty cells and cells containing a number next to the cell the player has selected

    if (x - 1 >= 0 && poleCopy[y][x - 1] !== "/") {
        if (pole[y][x - 1] !== "X") {
            poleCopy[y][x - 1] = pole[y][x - 1]
            if (pole[y][x - 1] == "/") {
                var x1 = x -1
                var y1 = y
                proyav(x1, y1)
            }
        }
    }
    if (y - 1 >= 0 && x - 1 >= 0 && poleCopy[y - 1][x - 1] !== "/") {
        if (pole[y - 1][x - 1] !== "X") {
            poleCopy[y - 1][x - 1] = pole[y - 1][x - 1]
            if (pole[y - 1][x - 1] == "/") {
                var x2 = x - 1
                var y2 = y - 1
                proyav(x2, y2)
            }
        }
    }
    if (y - 1 >= 0 && poleCopy[y - 1][x] !== "/") {
        if (pole[y - 1][x] !== "X") {
            poleCopy[y - 1][x] = pole[y - 1][x]
            if (pole[y - 1][x] == "/") {
                var x3 = x
                var y3 = y - 1
                proyav(3, 3)
            }
        }
    }
    if (y - 1 >= 0 && x + 1 <= 8 && poleCopy[y - 1][x + 1] !== "/") {
        if (pole[y - 1][x + 1] !== "X") {
            poleCopy[y - 1][x + 1] = pole[y - 1][x + 1]
            if (pole[y - 1][x + 1] == "/") {
                var x4 = x + 1
                var y4 = y - 1
                proyav(x4, y4)
            }
        }
    }
    if (x + 1 <= 8 && poleCopy[y][x + 1] !== "/") {
        if (pole[y][x + 1] !== "X") {
            poleCopy[y][x + 1] = pole[y][x + 1]
            if (pole[y][x + 1] == "/") {
                var x5 = x + 1
                var y5 = y
                proyav(x5, y5)
            }
        }
    }
    if (y + 1 <= 8 && x + 1 <= 8 && poleCopy[y + 1][x + 1] !== "/") {
        if (pole[y + 1][x + 1] !== "X") {
            poleCopy[y + 1][x + 1] = pole[y + 1][x + 1]
            if (pole[y + 1][x + 1] == "/") {
                var x6 = x + 1
                var y6 = y + 1
                proyav(x6, y6)
            }
        }
    }
    if (y + 1 <= 8 && poleCopy[y + 1][x] !== "/") {
        if (pole[y + 1][x] !== "X") {
            poleCopy[y + 1][x] = pole[y + 1][x]
            if (pole[y + 1][x] == "/") {
                var x7 = x
                var y7 = y + 1
                proyav(x7, y7)
            }
        }
    }
    if (y + 1 <= 8 && x - 1 >= 0 && poleCopy[y + 1][x - 1] !== "/") {
        if (pole[y + 1][x - 1] !== "X") {
            poleCopy[y + 1][x - 1] = pole[y + 1][x - 1]
            if (pole[y + 1][x - 1] == "/") {
                var x8 = x - 1
                var y8 = y + 1
                proyav(x8, y8)
            }
        }
    }
}

fun main() {
    ustanovka(start(), 0, 0)

    okrug()

    slesch()

    output()

    game()

    end()
}