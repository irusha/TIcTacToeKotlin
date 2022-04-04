fun main() {
    val board = Board()
    board.init()
    board.draw()
    while(true) {
        val b = readLine()
        board.setSquareValue(b!!, false)
        board.draw()
        println(board.scanBoardForTheWinner())
    }
}
