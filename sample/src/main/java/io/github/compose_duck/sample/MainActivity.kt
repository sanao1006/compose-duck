package io.github.compose_duck.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import io.github.compose_duck.sample.theme.ComposeduckTheme
import io.github.duck.compose.RandomDuckGif
import io.github.duck.compose.RandomDuckJpg

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeduckTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Sample(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Sample(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(128.dp), contentAlignment = Alignment.Center) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(3) {
                SampleItem()
            }
        }
    }
}

@Composable
fun SampleItem() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        RandomDuckGif(modifier = Modifier.size(128.dp))
        RandomDuckJpg(modifier = Modifier.size(128.dp))
    }
}