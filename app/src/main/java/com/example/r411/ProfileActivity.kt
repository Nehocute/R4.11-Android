package com.example.r411

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.r411.persistance.database.AppDatabase
import com.example.r411.persistance.entity.Formation
import com.example.r411.ui.theme.R411Theme

class ProfileActivity : ComponentActivity() {

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
                            ProfileView()
                        }
                    })
            }
        }
    }
}

@Composable
fun ProfileView() {
    val sharedPref: SharedPreferences = LocalContext.current.getSharedPreferences("LOGGED_USER", Context.MODE_PRIVATE)
    val username = sharedPref.getString("name", "name")
    val levelId = sharedPref.getInt("levelId", -1)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Profil", fontSize = MaterialTheme.typography.h4.fontSize)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Text("Nom: ", modifier = Modifier.padding(top=16.dp))
            Text(text = username!!, modifier = Modifier.padding(top=16.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Text("Level: ", modifier = Modifier.padding(top=16.dp))
            Text(text = levelId.toString(), modifier = Modifier.padding(top=16.dp))
        }
    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    R411Theme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("R4.11")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
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
                    ProfileView()
                }
            })
    }
}