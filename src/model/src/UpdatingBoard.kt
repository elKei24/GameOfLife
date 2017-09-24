import java.util.*

class UpdatingBoard(
        size: Coordinate,
        coordinatesOfLivingCells: List<Coordinate> = emptyList(),
        msBetweenUpdates: Long = 1000L
) : Board(size, coordinatesOfLivingCells) {

    private var timer: Timer? = null

    var msBetweenUpdates: Long = msBetweenUpdates
    set(value) {
        field = value
        startGenerationUpdates()
    }

    fun startGenerationUpdates() {
        stopGenerationUpdates()
        val newTimer = Timer("BoardUpdaterThread", true)
        newTimer.schedule(BoardUpdateTask(), 0L, msBetweenUpdates)
        timer = newTimer
    }

    fun stopGenerationUpdates() {
        timer?.cancel()
        timer = null
    }

    private inner class BoardUpdateTask : TimerTask() {
        override fun run() {
            updateToNextGeneration()
        }
    }

}