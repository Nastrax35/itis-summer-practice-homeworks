object DataRepository {
    // Здесь будет лежать наш сгенерированный список
    var filmsList: List<FilmModel> = emptyList()

    // Списки для генерации случайных данных
    private val names = listOf("Inception", "Shutter Island", "Blood Diamond", "Snatch", "Interstellar", "The Dark Knight", "The Matrix", "Gladiator")
    private val posters = listOf(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnnMFEOo2AQ0AMZCa2wU37jhKjeEOtzpKJpPPLuQxU6BCHUkIGOP1sz22s&s=10", // Заглушка кино
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3y19TfUrHImt1OqmEextIDaFnyFgk-gMmITlu1GsS3jN7E8a-D0ZKmLnR&s=10",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTY-cVhNFhBxeZGFNHkpytAK13hVAsFRs1wq0evuoNu8BFEyjyS83s6FTI&s=10",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvnBJauIxwKZgzRjelIIsVDssoOlvyJdEgJ3X_MqdKouKpq6mnCOkc678&s=10",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEy-y51aHUfFtHwPcxCJN4eMpnv15px54ywD1Fl40tcrraD2nzoA3tRcc&s=10"
    )

    fun generateFilms(count: Int) {
        filmsList = List(count) { index ->
            FilmModel(
                id = index.toString(),
                posterUrl = posters.random(),
                name = "${names.random()} ${index + 1}",
                description = "Описание фильма $index",
                releaseDate = (2000..2026).random()
            )
        }
    }
}
