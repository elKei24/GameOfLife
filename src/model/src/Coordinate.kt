data class Coordinate(val x : Int, val y : Int) : Comparable<Coordinate> {

    fun area() = x * y

    operator fun plus(movement: Coordinate): Coordinate = Coordinate(x + movement.x, y + movement.y)
    override operator fun compareTo(other: Coordinate) : Int {
        return when {
            this.y < other.y -> -2
            this.y > other.y -> 2
            else -> when {
                this.x < other.x -> -1
                this.x > other.x -> 1
                else -> 0
            }
        }
    }
    operator fun rem(by: Coordinate): Coordinate {
        return Coordinate(nonNegativeRem(this.x, by.x), nonNegativeRem(this.y, by.y))
    }

    private fun nonNegativeRem(left: Int, right: Int): Int {
        return (left % right + right) % right
    }


}