var row1a654651 = mutableListOf("   ", "   ", "   ")
var row2a654651 = mutableListOf("   ", "   ", "   ")
var row3a654651 = mutableListOf("   ", "   ", "   ")

class Board {

    //Reset the board
    fun reset() {
        var i = 0
        while (i < 3) {
            var j = 0
            while (j < 3) {
                editArrayValues(i, j, "   ")
                j++
            }
            i++
        }

    }

    //Draws the board on the command line
    fun draw() {
        //this for loop is to get the row numbers
        for (i in (0..2)) {
            var str = ""
            //this for is to get the column number
            for (j in (0..2)) {
                //check whether the column is the last column and print '|' if it isn't the last column
                str = if (j != 2) {
                    //Set the values of the row strings according to the arrays ( X  O  X )
                    "$str${getArrayValues(i, j)} | "
                } else {
                    //Set the values of the row strings according to the arrays and add the number of the row ( X  O  X   3)
                    "$str${getArrayValues(i, j)}   ${3 - i}"
                }
            }
            println(str)
            if (i != 2) {
                println("- - - - - - - -")
            }
        }
        //Print the letters of the column of the TicTacToe board
        println(" C     B     A")
    }

    //set the value of a TicTacToe board. In here the mode is whether you add an X or O to the board. mode = true;
    //playing symbol = X
    fun setSquareValue(coordinate: String, mode: Boolean): Boolean {
        val mode1 = if (mode) {
            " X "
        } else {
            " O "
        }
        var rowNumber = 0
        var returnValue = true
        val chars = coordinate.split("")
        val columnNumberForLetter = when (chars[1]) {
            "A", "a" -> 2
            "B", "b" -> 1
            "C", "c" -> 0
            else -> -1
        }
        try {
            rowNumber = 3 - chars[2].toInt()
        } catch (e: Exception) {
            returnValue = false
        }
        if (columnNumberForLetter == -1 || !returnValue) {
            returnValue = false
        } else {
            if (getArrayValues(rowNumber, columnNumberForLetter) == "   ") {
                editArrayValues(rowNumber, columnNumberForLetter, mode1)
            } else {
                returnValue = false
            }
        }
        return returnValue
    }

    //Set the value of the certain square of the board
    private fun editArrayValues(row: Int, column: Int, data: String) {
        when (row) {
            0 -> row1a654651[column] = data
            1 -> row2a654651[column] = data
            2 -> row3a654651[column] = data
        }
    }

    //Get the value of a certain square of the board
    private fun getArrayValues(row: Int, column: Int): String {
        var k = ""
        when (row) {
            0 -> k = row1a654651[column]
            1 -> k = row2a654651[column]
            2 -> k = row3a654651[column]
        }
        return k
    }

    //Scan the board for the winner and returns the symbol of the winner
    fun scanBoardForTheWinner(): String {
        var returnValue = ""
        for (i in (0..2)) {
            //
            val everyRow = getArrayValues(0, i) == getArrayValues(1, i) && getArrayValues(1, i) == getArrayValues(2, i)
            val everyColumn =
                getArrayValues(i, 0) == getArrayValues(i, 1) && getArrayValues(i, 1) == getArrayValues(i, 2)
            val noSpaceBoolColumn = getArrayValues(i, 2) != "   "
            val noSpaceBoolRow = getArrayValues(0, i) != "   "
            val diagonalLeft =
                getArrayValues(0, 0) == getArrayValues(1, 1) && getArrayValues(1, 1) == getArrayValues(2, 2)
            val noSpaceDiagonalBool = getArrayValues(1, 1) != "   "
            val diagonalRight =
                getArrayValues(0, 2) == getArrayValues(1, 1) && getArrayValues(2, 0) == getArrayValues(1, 1)
            if (everyColumn && noSpaceBoolColumn) {
                returnValue = getArrayValues(i, 1)
                break
            } else if (everyRow && noSpaceBoolRow) {
                returnValue = getArrayValues(1, i)
                break
            } else if (diagonalRight && noSpaceDiagonalBool) {
                returnValue = getArrayValues(1, 1)
                break
            } else if (diagonalLeft && noSpaceDiagonalBool) {
                returnValue = getArrayValues(1, 1)
                break
            }

        }
        return returnValue
    }

    //Scan squares with two same data and returns the remaining squares as a list
    fun scanTwoSameSquares(): MutableList<String> {
        val list = mutableListOf<String>()
        var spaceCount = 0
        var spaceCountOutside = 0
        var temporaryCoordinateStore = ""
        val otherTwoSquares = mutableListOf<String>()
        var temporaryCoordinateStoreOutside = ""
        val otherTwoSquaresOutside = mutableListOf<String>()
        if (scanBoardForTheWinner() == "") {
            //For the row
            for (i in (0..2)) {
                //For the column ------------------------------------------------------------
                for (j in (0..2)) {
                    if (getArrayValues(i, j) == "   ") {
                        spaceCount++
                        temporaryCoordinateStore = coordinateConverter(i, j)
                    } else {
                        otherTwoSquares.add(getArrayValues(i, j))
                    }
                }
                if (spaceCount == 1) {
                    if (otherTwoSquares[0] == otherTwoSquares[1]) {
                        list.add(temporaryCoordinateStore)
                    }
                }
                spaceCount = 0
                temporaryCoordinateStore = ""
                otherTwoSquares.clear()
                //column code ended ----------------------------------------------------------
                //for the row ----------------------------------------------------------------
                for (j in (0..2)) {
                    if (getArrayValues(j, i) == "   ") {
                        spaceCount++
                        temporaryCoordinateStore = coordinateConverter(j, i)
                    } else {
                        otherTwoSquares.add(getArrayValues(j, i))
                    }
                }
                if (spaceCount == 1) {
                    //println(otherTwoSquares[0] + " :" + otherTwoSquares[1])
                    if (otherTwoSquares[0] == otherTwoSquares[1]) {
                        list.add(temporaryCoordinateStore)
                    }
                }
                spaceCount = 0
                temporaryCoordinateStore = ""
                otherTwoSquares.clear()
                //row code ended --------------------------------------------------------------
                //for diagonal left ----------------------------------------------------------
                if (getArrayValues(i, i) == "   "){
                    spaceCountOutside++
                    temporaryCoordinateStoreOutside = coordinateConverter(i, i)
                } else {
                    otherTwoSquaresOutside.add(getArrayValues(i, i))
                }

            }
            if (spaceCountOutside == 1){

                if (otherTwoSquaresOutside[0] == otherTwoSquaresOutside[1]){
                    list.add(temporaryCoordinateStoreOutside)
                }
            }

            spaceCountOutside = 0
            temporaryCoordinateStoreOutside = ""
            otherTwoSquaresOutside.clear()
            //diagonal left code ended -------------------------------------------------------
            //for diagonal right
            for (i in (0..2)){
                if (getArrayValues(i, 2-i) == "   "){
                    spaceCountOutside++
                    temporaryCoordinateStoreOutside = coordinateConverter(i, 2-i)
                } else {
                    otherTwoSquaresOutside.add(getArrayValues(i, 2-i))
                }
            }
            if (spaceCountOutside == 1){
                if (otherTwoSquaresOutside[0] == otherTwoSquaresOutside[1]){
                    list.add(temporaryCoordinateStoreOutside)
                }
            }
            otherTwoSquaresOutside.clear()
            //diagonal right code ended -----------------------------------------------------
        }
        return list
    }

    //convert given mutable list coordinate into a board coordinate
    private fun coordinateConverter(row: Int, column: Int): String {
        val letter =
            when (column) {
                0 -> "C"
                1 -> "B"
                2 -> "A"
                else -> ""
            }
        return letter + (3 - row)
    }

    //Scans the empty squares and returns a mutable list with their board coordinates
    fun scanEmptySquares(): MutableList<String> {
        val list = mutableListOf<String>()
        for (i in (0..2)) {
            //for loop for the rows
            for (j in (0..2)) {
                //For loop for the column
                if (getArrayValues(i, j) == "   ") {
                    list.add(coordinateConverter(i, j))
                }
            }
        }
        return list
    }
}