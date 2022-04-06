fun main() {
    val board = Board()
    while (true){
        if (gameplaySP()){
            board.reset()
            println("Do you want to play again? (Y/N)")
            val d = readLine()
            if (d == "N" || d == "n"){
                break
            }
        }
    }
}

fun gameplaySP():Boolean{
    val board = Board()
    board.draw()
    println("Please enter your side (Type X for X side and type O for O side): ")
    val mode: Boolean
    while (true) {
        val readALine = readLine()
        if (readALine == "X" || readALine == "x") {
            mode = true
            break
        }
        else if (readALine == "O" || readALine == "o"){
            mode = false
            break
        }
        else{
            println("Invalid selection. Please try again")
            println("Please enter your side: (Type X for X side and type O for O side): ")
        }
    }
    while (true) {
        if (!mode){
            val emptySquares = board.scanEmptySquares()
            val twoEqualSquares = board.scanTwoSameSquares()
            if (board.scanEmptySquares().size == 0) {
                println("Draw")

                break
            }
            if (twoEqualSquares.size != 0) {
                board.setSquareValue(twoEqualSquares[(0 until twoEqualSquares.size).random()], true)
            } else {
                if (emptySquares.size != 0) {
                    board.setSquareValue(emptySquares[(0 until emptySquares.size).random()], true)
                } else {
                    println("Draw")
                    
                    break
                }
            }
            board.draw()
            var j = board.scanBoardForTheWinner()
            if (j != "") {
                println("$j won")
                
                break
            }
            if (board.scanEmptySquares().size == 0) {
                println("Draw")
                
                break
            }
            println("Enter the coordinate: ")
            var b = readLine()
            while (!board.setSquareValue(b!!, false)) {
                println("Invalid coordinate")
                b = readLine()!!
            }
            board.draw()
            j = board.scanBoardForTheWinner()
            if (j != "") {
                println("$j won")
                
                break
            }
            if (board.scanEmptySquares().size == 0) {
                board.draw()
                println("Draw")
                
                break
            }
        }
        else{
            println("Enter the coordinate: ")
            var b = readLine()
            while (!board.setSquareValue(b!!, true)) {
                println("Invalid coordinate")
                b = readLine()!!
            }
            board.draw()
            var j = board.scanBoardForTheWinner()
            if (j != "") {
                println("$j won")
                
                break
            }
            if (board.scanEmptySquares().size == 0) {
                println("Draw")
                
                break
            }
            val emptySquares = board.scanEmptySquares()
            val twoEqualSquares = board.scanTwoSameSquares()
            if (twoEqualSquares.size != 0) {
                board.setSquareValue(twoEqualSquares[(0 until twoEqualSquares.size).random()], false)
            } else {
                if (emptySquares.size != 0) {
                    board.setSquareValue(emptySquares[(0 until emptySquares.size).random()], false)
                } else {
                    board.draw()
                    println("Draw")
                    
                    break
                }
            }
            board.draw()
            j = board.scanBoardForTheWinner()
            if (j != "") {
                println("$j won")
                
                break
            }
            if (board.scanEmptySquares().size == 0) {
                println("Draw")
                
                break
            }
        }
        
    }
    return true
}