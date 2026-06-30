package ru.itis.summerpractice.uiandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.itis.summerpractice.uiandroid.ui.ShowScreen
import ru.itis.summerpractice.uiandroid.ui.UserContent
import ru.itis.summerpractice.uiandroid.ui.ShowScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Вызываем созданную Composable-функцию с параметрами
            ShowScreen()
        }
    }


}


