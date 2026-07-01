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
    // Храним текст, который вводит пользователь в поле фильтра
    var filterInput by remember { mutableStateOf("") }

    // Состояние списка, который сейчас отображается на экране (по умолчанию — весь список)
    var displayedFilms by remember { mutableStateOf(DataRepository.filmsList) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Кнопка Назад, чтобы можно было вернуться на первый экран
        Button(onClick = onNavigateToFirst) {
            Text("Назад")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Поле ввода для фильтрации по году
        OutlinedTextField(
            value = filterInput,
            onValueChange = { filterInput = it },
            label = { Text("Введите год для фильтрации (например, 2008)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка "Сортировать" (по ТЗ она выполняет роль применения фильтра)
        Button(
            onClick = {
                val yearLimit = filterInput.toIntOrNull()

                if (filterInput.isBlank() || yearLimit == null) {
                    // Если поле пустое или ввели не число — возвращаем первоначальный список
                    displayedFilms = DataRepository.filmsList
                } else {
                    // Фильтруем: оставляем фильмы, у которых год выпуска <= введенного
                    displayedFilms = DataRepository.filmsList.filter { it.releaseDate <= yearLimit }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сортировать")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Отображение списка или заглушки, если ничего не найдено
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

// Отдельный Composable для одного элемента списка (картинка + текст справа)
@Composable
fun FilmRow(film: FilmModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Картинка с использованием Coil AsyncImage
        AsyncImage(
            model = film.posterUrl,
            contentDescription = "Постер фильма",
            modifier = Modifier
                .size(80.dp) // Фиксированный размер для картинки обязательно по ТЗ
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )

        // Название и год выпуска справа от картинки
        Column {
            Text(text = film.name, fontSize = 18.sp)
            Text(text = "Год: ${film.releaseDate}", fontSize = 14.sp)
        }
    }
}
