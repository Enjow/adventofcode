data class Position2D(var x: Int, var y: Int) {
    operator fun minus (pos: Position2D) :Position2D =  Position2D(this.x-pos.x, this.y-pos.y)
    fun divisibleBy(pos: Position2D): Boolean {
        if ((this.x != 0 && pos.x == 0) ||
            (this.y != 0 && pos.y == 0)
        ) {
            return false
        } else if (
            (this.x == 0 && pos.x == 0) ||
            (this.y == 0 && pos.y == 0) ||
            this.x % pos.x == 0 && this.y % pos.y == 0 && this.x / pos.x == this.y / pos.y
        ) {
            return true
        } else {
            Error("Position2D divisibleBy function unknown case")
        }
        return false
    }
}