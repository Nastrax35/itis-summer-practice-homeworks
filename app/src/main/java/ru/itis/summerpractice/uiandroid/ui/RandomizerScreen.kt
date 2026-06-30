package ru.itis.summerpractice.uiandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kotlin.random.Random

@Composable
fun ShowScreen() {
    var userInput by remember { mutableStateOf("") }
    val secretByNumber = remember { Random.nextInt(1, 101) }

    // Переменная состояния для проверки по кнопке
    var checkedNumber by remember { mutableStateOf<Int?>(null) }
    val isGuessed = checkedNumber != null && checkedNumber == secretByNumber

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Угадай число",
            fontSize = 32.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = "https://play-lh.googleusercontent.com/l5ac-JC-Ptp4iv0H-2a9faaW2-PFb-xNLdcrWFzoYUzmaVUtn_xXzbV3bFMTvEVdlEDjA_N9gQsIjd6TXe81wA",
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Если число еще НЕ угадано, показываем поле ввода и кнопку под ним
        if (!isGuessed) {
            TextField(
                value = userInput,
                onValueChange = { newInput ->
                    userInput = newInput.filter { it.isDigit() }
                },
                label = { Text("Введите число") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    checkedNumber = userInput.toIntOrNull()
                }
            ) {
                Text(text = "Проверить", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(40.dp))
        }

        // Логика вывода результатов и подсказок
        val number = checkedNumber
        if (number != null && number != 0) {
            if (isGuessed) {
                Text(
                    text = "Поздравляю, вы угадали число!",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = "https://img.magnific.com/premium-vector/face-with-festive-horn-cap-large-size-yellow-emoji-smile_599062-5610.jpg",
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            else if (number < secretByNumber) {
                Text(
                    text = "Попробуйте еще раз, загаданное число больше вашего",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
            else if (number > secretByNumber) {
                Text(
                    text = "Попробуйте еще раз, загаданное число меньше вашего",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
