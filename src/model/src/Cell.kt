import java.util.concurrent.CopyOnWriteArrayList

class Cell(var living : Boolean = false) {
    private val listeners = CopyOnWriteArrayList<CellListener>()

    fun updateToNextGeneration(livingNeighbours: Int) {
        val newLiving = getNextGenerationLiving(livingNeighbours)
        if (living != newLiving) {
            living = newLiving
            notifyCellLivingChanged()
        }
    }
    private fun getNextGenerationLiving(livingNeighbours: Int) : Boolean {
        return if (!living)
            livingNeighbours == 3
        else
            livingNeighbours == 2 || livingNeighbours == 3
    }

    fun addListener(cellListener: CellListener) = listeners.add(cellListener)
    fun removeListener(cellListener: CellListener) = listeners.remove(cellListener)
    private fun notifyCellLivingChanged() = listeners.forEach{it.cellLivingChanged(this)}
}

interface CellListener {
    fun cellLivingChanged(cell: Cell)
}