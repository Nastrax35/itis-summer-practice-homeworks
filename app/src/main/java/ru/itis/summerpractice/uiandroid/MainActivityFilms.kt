package ru.itis.summerpractice.uiandroid


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import ru.itis.summerpractice.uiandroid.ui.*


class MainActivityFilms : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Состояние, которое хранит текущий экран
            var currentScreen by remember { mutableStateOf(Screen.FIRST) }

            // Простая навигация: в зависимости от state показываем нужный экран
            when (currentScreen) {
                Screen.FIRST -> {
                    // Передаем лямбду, которая переключит экран на SECOND
                    FirstScreen(onNavigateToSecond = { currentScreen = Screen.SECOND })
                }
                Screen.SECOND -> {
                    // Передаем лямбду для возврата назад на FIRST
                    SecondScreen(onNavigateToFirst = { currentScreen = Screen.FIRST })
                }
            }
        }
    }
}
enum class Screen {
    FIRST, SECOND
}

