package com.example.jetpackcomposebasics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    // 状態をホイストします。ホイストは、状態にアクセスする必要がある共通の祖先に状態を移動
    val shouldShowOnboarding = rememberSaveable { mutableStateOf(true) }

    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        if (shouldShowOnboarding.value) OnboardingScreen {
            shouldShowOnboarding.value = false
        }
        else Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) {
            Greeting(name = it)
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            Spring.DampingRatioMediumBouncy,
            Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            // weightをつけるとコンテンツが使用する領域を全て割り当てる、ついてないものは適度に寄せられる
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(
                    text = name,
                    // copyで定義ずみのスタイルをOverrideできる
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold)
                )
            }
            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "show more" else "show less")
            }
        }

    }

}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = { onContinueClicked() }
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetpackComposeBasicsTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetpackComposeBasicsTheme {
        OnboardingScreen {}
    }
}