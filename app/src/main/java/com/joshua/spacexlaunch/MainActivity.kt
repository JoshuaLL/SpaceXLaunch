package com.joshua.spacexlaunch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joshua.spacexlaunch.ui.Navigation
import com.joshua.spacexlaunch.ui.theme.AppTheme

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContent {
            AppTheme{
                MainScreen()
            }
        }


    }
}

@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainScreen(){
    Spacer(Modifier.sizeIn(8.dp))
    Navigation()
}

