fun main() {
    val board = Board()
    board.draw()
    while(true) {
        val emptySquares = board.scanEmptySquares()
        board.setSquareValue(emptySquares[(0 until emptySquares.size).random()], true)
        var j = board.scanBoardForTheWinner()
        if (j != ""){
            println("$j won")
            break
        }
        board.draw()
        var b = readLine()
        while (!board.setSquareValue(b!!,false)){
            println("Invalid coordinate")
            b = readLine()!!
        }
        board.draw()
        j = board.scanBoardForTheWinner()
        if (j != ""){
            println("$j won")
            break
        }
    }
}