package com.example.jetpackcomposebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBasicsTheme {
                MyApp(names)
            }
        }
    }
}
val names = listOf("Android", "Compose")

@Composable
fun MyApp(names: List<String>) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            names.forEach {
                Greeting(name = it)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            // weightをつけるとコンテンツが使用する領域を全て割り当てる、ついてないものは適度に寄せられる
            Column(modifier = Modifier.weight(1F)) {
                Text(text = "Hello,")
                Text(text = name)
            }
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "show more")
            }
        }
        
    }

}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetpackComposeBasicsTheme {
        MyApp(names)
    }
}