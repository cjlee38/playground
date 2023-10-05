package syntax.asterisk



fun main() {
    val moveStrategy = { (0..9).random() >= 4 }
    val listOf =
        listOf(Car(moveStrategy = moveStrategy), Car(moveStrategy = moveStrategy), Car(moveStrategy = moveStrategy))
    val toTypedArray = listOf.toTypedArray()
    listOf(toTypedArray)
    val cars = Cars((0..3).map { Car(moveStrategy = moveStrategy) })
}

data class Car(private var position: Int = 0, private var moveStrategy: () -> Boolean) {
    fun move() {
        if (moveStrategy()) {
            position += 1
        }
    }
}

class Cars(cars: List<Car> = emptyList()) {
    private val cars: List<Car> = listOf(*cars.toTypedArray())

    fun move() {
        for (car in cars) {
            car.move()
        }
    }

    fun values(): List<Car> = cars
}
