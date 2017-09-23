class Cell(var living : Boolean = false) {
    fun updateToNextGeneration(livingNeighbours: Int) {
        living = if (!living)
            livingNeighbours == 3
        else
            livingNeighbours == 2 || livingNeighbours == 3
    }


}