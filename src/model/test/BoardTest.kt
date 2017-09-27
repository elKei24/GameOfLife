/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

import com.elkei.gol.model.Board
import com.elkei.gol.model.Coordinate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BoardTest {
    private lateinit var board : Board

    @BeforeEach
    internal fun initializeBoard() {
        board = generateTestBoard()
    }

    private fun generateTestBoard(): Board {
        return Board(Coordinate(4, 5), listOf(Coordinate(0, 0), Coordinate(1, 0), Coordinate(0, 1),
                Coordinate(1, 1), Coordinate(2, 1), Coordinate(1, 3), Coordinate(2, 3))::contains)
    }

    @Test
    internal fun testIterationBirth() {
        board.updateToNextGeneration()
        assertLivingCellAt(Coordinate(2, 0))
        assertLivingCellAt(Coordinate(3, 0))
        assertLivingCellAt(Coordinate(3, 1))
        assertLivingCellAt(Coordinate(0, 2))
        assertLivingCellAt(Coordinate(3, 2))
        assertLivingCellAt(Coordinate(0, 4))
        assertLivingCellAt(Coordinate(2, 4))
    }

    @Test
    internal fun testIterationOvercrowdedDeath() {
        board.updateToNextGeneration()
        assertDeadCellAt(Coordinate(1, 1))
        assertDeadCellAt(Coordinate(1, 0))

    }

    @Test
    internal fun testIterationUndercrowdedDeath() {
        board.updateToNextGeneration()
        assertDeadCellAt(Coordinate(1, 3))
        assertDeadCellAt(Coordinate(2, 3))
    }

    @Test
    internal fun testIterationStayDead() {
        board.updateToNextGeneration()
        assertDeadCellAt(Coordinate(1, 2))
        assertDeadCellAt(Coordinate(2, 2))
        assertDeadCellAt(Coordinate(0, 3))
        assertDeadCellAt(Coordinate(3, 3))
        assertDeadCellAt(Coordinate(1, 4))
        assertDeadCellAt(Coordinate(3, 4))
    }

    @Test
    internal fun testIterationStayAlive() {
        board.updateToNextGeneration()
        assertLivingCellAt(Coordinate(0, 0))
        assertLivingCellAt(Coordinate(0, 1))
        assertLivingCellAt(Coordinate(2, 1))
    }

    private fun assertLivingCellAt(coordinate: Coordinate) {
        assertTrue(livingCellAt(coordinate))
    }

    private fun assertDeadCellAt(coordinate: Coordinate) {
        assertFalse(livingCellAt(coordinate))
    }

    private fun livingCellAt(coordinate: Coordinate): Boolean {
        return board.getItemForCoordinate(coordinate).living
    }

    @Test
    internal fun testNumberOfLivingNeighboursInCenter() {
        assertEquals(5, board.getNumberOfLivingNeighbours(Coordinate(1, 2)))
    }

    @Test
    internal fun testNumberOfLivingNeighboursAtBorder() {
        assertEquals(2, board.getNumberOfLivingNeighbours(Coordinate(3, 4)))
        assertEquals(4, board.getNumberOfLivingNeighbours(Coordinate(1, 4)))
    }

}