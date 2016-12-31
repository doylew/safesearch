/**
 * Basic grid world implementation
 * Created by willi on 12/16/2016.
 */


package edu.unh.cs.ai

import java.util.*

data class Pair(val x: Int, val y: Int)
data class State(val width: Int, val height: Int, val agentX: Int, val agentY: Int, val goalX: Int,
                 val goalY: Int, val obstacles: ArrayList<Pair>, val bunkers: ArrayList<Pair>)

fun readDomain(input: Scanner): State {
    val width = input.nextInt()
    val height = input.nextInt()
    var row = 0
    var col = 0
    var agentX = 0
    var agentY = 0
    var goalX = 0
    var goalY = 0
    val obstacles = ArrayList<Pair>()
    val bunkers = ArrayList<Pair>()
    input.nextLine()
    while (input.hasNextLine()) {
        val nextLine = input.nextLine()
        nextLine.forEach {
            if (it == '@') {
                agentX = col
                agentY = row
            } else if (it == '*') {
                goalX = col
                goalY = row
            } else if (it == '#') {
                val newObstacle = Pair(col, row)
                obstacles.add(newObstacle)
            } else if (it == '$') {
                val newBunker = Pair(col, row)
                bunkers.add(newBunker)
            }
            ++col
        }
        ++row
        col = 0
    }
    return State(width, height, agentX, agentY, goalX, goalY, obstacles, bunkers)
}

fun heuristic(state: State): Double {
    return Math.sqrt(Math.pow((state.agentX.toDouble() - state.goalX.toDouble()), 2.0) +
            Math.pow(state.agentY.toDouble() - state.goalY.toDouble(), 2.0))
}
