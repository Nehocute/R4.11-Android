package com.example.r411

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.r411.ui.theme.R411Theme

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            R411Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("R4.11")
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    finish()
                                }) {
                                    Icon(Icons.Filled.ArrowBack, "backIcon")
                                }
                            },
                            elevation = 10.dp
                        )
                    }, content = {
                        Surface(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize()
                        ) {
                            StudentsView()
                        }
                    })
            }
        }
    }
}

@Composable
fun StudentsView() {
    Text(text = "Hello !")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    R411Theme {

    }
}