package com.example.r411

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.r411.ui.theme.R411Theme

class FormationListActivity : ComponentActivity() {
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
                            FormationList()
                        }
                    })
            }
        }
    }
}

@Composable
fun FormationList() {
    Column(
        Modifier
            .padding(5.dp)
            .verticalScroll(rememberScrollState())) {
        for (i in 0..30) {
            Row (
                Modifier
                    .padding(3.dp)){
                Text(text = "Niveau $i - Groupe 3",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    fontSize = 24.sp)
                Spacer(Modifier.weight(1f))
                Text(text = "Niveau 1",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 20.sp)
            }
            Divider(modifier = Modifier.height(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    R411Theme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("R4.11")
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
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
                    FormationList()
                }
            })
    }
}