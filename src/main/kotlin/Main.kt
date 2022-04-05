fun main() {
    val board = Board()
    board.draw()
    while (true) {
        val emptySquares = board.scanEmptySquares()
        val twoEqualSquares = board.scanTwoSameSquares()
        if (board.scanEmptySquares().size == 0){
            println("Draw")
            break
        }
        if (twoEqualSquares.size != 0) {
            board.setSquareValue(twoEqualSquares[(0 until twoEqualSquares.size).random()], true)
        } else {
            if (emptySquares.size != 0) {
                board.setSquareValue(emptySquares[(0 until emptySquares.size).random()], true)
            }
            else {
                println("Draw")
                break
            }
        }
        board.draw()
        var j = board.scanBoardForTheWinner()
        if (board.scanEmptySquares().size == 0){
            println("Draw")
            break
        }
        if (j != "") {
            println("$j won")
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
        if (board.scanEmptySquares().size == 0){
            println("Draw")
            break
        }
    }
}
