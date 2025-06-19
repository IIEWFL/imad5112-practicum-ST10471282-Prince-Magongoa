package vcmsa.ci.musicplaylistmanager

data class Song(val title: String, val ratings: MutableList<Int> = mutableListOf()) {
    fun addRating(rating: Int) {
        if (rating in 1..5) {
            ratings.add(rating)
        } else {
            println("Rating must be between 1 and 5.")
        }
    }

    fun averageRating(): Double {
        return if (ratings.isNotEmpty()) {
            ratings.average()
        } else {
            0.0
        }
    }
}

fun main() {
    val song1 = Song("Song A")
    val song2 = Song("Song B")

    // Simulating user ratings
    song1.addRating(4)
    song1.addRating(5)
    song1.addRating(3)

    song2.addRating(2)
    song2.addRating(5)

    println("Average rating for ${song1.title}: ${song1.averageRating()}")
    println("Average rating for ${song2.title}: ${song2.averageRating()}")
}
