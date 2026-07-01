package ru.itis.summerpractice.uiandroid.ui

import FilmModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage // Обрати внимание на импорт Coil в зависимости от версии


@Composable
fun SecondScreen(onNavigateToFirst: () -> Unit) {
    var filterInput by remember { mutableStateOf("") }

    var displayedFilms by remember { mutableStateOf(DataRepository.filmsList) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onNavigateToFirst) {
            Text("Назад")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = filterInput,
            onValueChange = { filterInput = it },
            label = { Text("Введите год для фильтрации (например, 2008)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val yearLimit = filterInput.toIntOrNull()

                if (filterInput.isBlank() || yearLimit == null) {
                    displayedFilms = DataRepository.filmsList
                } else {
                    displayedFilms = DataRepository.filmsList.filter { it.releaseDate <= yearLimit }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сортировать")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (displayedFilms.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Фильмы не найдены", fontSize = 18.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(displayedFilms) { film ->
                    FilmRow(film = film)
                }
            }
        }
    }
}

@Composable
fun FilmRow(film: FilmModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = film.posterUrl,
            contentDescription = "Постер фильма",
            modifier = Modifier
                .size(80.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )

        Column {
            Text(text = film.name, fontSize = 18.sp)
            Text(text = "Год: ${film.releaseDate}", fontSize = 14.sp)
        }
    }
}
