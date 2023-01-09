class Car(_number: String, _color: String) {
    val number: String = _number
    val color: String = _color.lowercase().capitalize()
}

class Parking(_space: Int) {
    val spots: Array<Car?> = Array(_space) { null }
    fun park(uI: List<String>) {
        val currentCar = Car(uI[1], uI[2])
        val freePP = spots.indexOf(null)
        if (freePP == -1) println("Sorry, the parking lot is full.") else {
            spots[freePP] = currentCar.also { println("${currentCar.color} car parked in spot ${freePP + 1}.") }
        }
    }

    fun leave(uI: List<String>) {
        try {
            val inx = uI[1].trim().toInt() - 1
            if (spots[inx] == null) println("There is no car in spot ${uI[1]}.") else {
                println("Spot ${uI[1]} is free.").also { spots[inx] = null }
            }
        } catch (e: Exception) {
            println("There is no car in spot ${uI[1]}.")
        }
    }

    fun status() {
        if (spots.filterNotNull().isEmpty()) println("Parking lot is empty.") else
            spots.filterNotNull().forEach { println("${spots.indexOf(it) + 1} ${it.number} ${it.color}") }
    }

    fun info(uI: List<String>) {
        if (uI[0] == "reg_by_color") {
            val rbc = spots.filterNotNull().filter { it.color.equals(uI[1], true) }.joinToString { it.number }
            println(rbc.ifEmpty { "No cars with color ${uI[1]} were found." })
        } else if (uI[0] == "spot_by_color") {
            val rbc = spots.filterNotNull().filter { it.color.equals(uI[1], true) }
                .joinToString { (spots.indexOf(it) + 1).toString() }
            println(rbc.ifEmpty { "No cars with color ${uI[1]} were found." })
        } else if (uI[0] == "spot_by_reg") {
            val rbc = spots.filterNotNull().filter { it.number.equals(uI[1], true) }
                .joinToString { (spots.indexOf(it) + 1).toString() }
            println(rbc.ifEmpty { "No cars with registration number ${uI[1]} were found." })
        }
    }
}

fun main() {
    var parking = Parking(0)
    while (true) {
        val uI = readln().split(" ")
        if (uI[0] == "exit") break
        if (uI[0] != "create" && parking.spots.isEmpty()) println("Sorry, a parking lot has not been created.")
        else if (uI[0] == "status") parking.status()
        else if (uI[0] == "park") parking.park(uI)
        else if (uI[0] == "leave") parking.leave(uI)
        else if (uI[0] == "create") {
            parking = Parking(uI[1].trim().toInt())
            println("Created a parking lot with ${uI[1]} spots.")
        } else parking.info(uI)
    }
}