var row1a654651 = mutableListOf("","","")
var row2a654651 = mutableListOf("","","")
var row3a654651 = mutableListOf("","","")
class Board{

    fun init(){
        var i = 0
        while(i < 3){
            var j = 0
            while (j < 3){
                editArrayValues(i,j,"   ")
                j++
            }
            i++
        }
    }
    fun draw() {
        //this for loop is to get the row numbers
        for (i in (0..2)){
            var str = ""
            //this for is to get the column number
            for(j in (0..2)){
                //check whether the column is the last column and print '|' if it isn't the last column
                str = if(j != 2){
                    //Set the values of the row strings according to the arrays ( X  O  X )
                    "$str${getArrayValues(i,j)} | "
                }
                else{
                    //Set the values of the row strings according to the arrays and add the number of the row ( X  O  X   3)
                    "$str${getArrayValues(i,j)}   ${3 - i}"
                }
            }
            println(str)
            if(i != 2) {
                println("- - - - - - - -")
            }
        }
        //Print the letters of the column of the TicTacToe board
        println(" C     B     A")
    }

    //set the value of a TicTacToe board. In here the mode is whether you add an X or O to the board. mode = true;
    //playing symbol = X
    fun setSquareValue(coordinate:String, mode:Boolean):Boolean{
        val mode1 = if(mode){
            " X "
        }
        else{
            " O "
        }
        var rowNumber = 0
        var returnValue = true
        val chars = coordinate.split("")
        val columnNumberForLetter = when(chars[1]){
            "A", "a" -> 2
            "B", "b" -> 1
            "C", "c" -> 0
            else -> -1
        }
        try {
            rowNumber = 3 - chars[2].toInt()
        }
        catch (e: Exception){
            returnValue = false
        }
        if(columnNumberForLetter == -1 || !returnValue){
            returnValue = false
        }
        else{
            editArrayValues(rowNumber, columnNumberForLetter, mode1)
        }
        return returnValue
    }

    //Set the value of the certain square of the board
    private fun editArrayValues(row:Int, column:Int, data:String){
        when(row){
            0 -> row1a654651[column] = data
            1 -> row2a654651[column] = data
            2 -> row3a654651[column] = data
        }
    }

    //Get the value of a certain square of the board
    private fun getArrayValues(row:Int, column:Int):String{
        var k = ""
        when(row){
            0 -> k = row1a654651[column]
            1 -> k = row2a654651[column]
            2 -> k = row3a654651[column]
        }
        return k
    }

    fun scanBoardForTheWinner():String{
        var returnValue = ""
        for (i in (0..2)){
            //
            val everyRow = getArrayValues(0,i) == getArrayValues(1, i) && getArrayValues(1, i) == getArrayValues(2, i)
            val everyColumn = getArrayValues(i,0) == getArrayValues(i, 1) && getArrayValues(i, 1) == getArrayValues(i, 2)
            val noSpaceBoolColumn = getArrayValues(i, 2) != "   "
            val noSpaceBoolRow = getArrayValues(0, i) != "   "
            val diagonalLeft = getArrayValues(0,0) == getArrayValues(1,1) && getArrayValues(1,1) == getArrayValues(2,2)
            val noSpaceDiagonalBool = getArrayValues(1,1) != "   "
            val diagonalRight = getArrayValues(0,2) == getArrayValues(1,1) && getArrayValues(2,0) == getArrayValues(1,1)
            if(everyColumn && noSpaceBoolColumn){
                returnValue = getArrayValues(i, 1)
                break
            }
            else if(everyRow && noSpaceBoolRow){
                returnValue = getArrayValues(1, i)
                break
            }
            else if(diagonalRight && noSpaceDiagonalBool){
                returnValue = getArrayValues(1,1)
                break
            }
            else if(diagonalLeft && noSpaceDiagonalBool){
                returnValue = getArrayValues(1,1)
                break
            }

        }
        return returnValue
    }

    fun scanTwoSameSquares(){
        
    }
}