package akw.app1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.smartsoft.bj.core.Game

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Blackjack()
        }
    }
}

@Composable
fun Blackjack() {

    val (game, setGame) = remember { mutableStateOf(Game(shuffle = true).deal()) }

    MaterialTheme {
        Surface(color = Color.LightGray, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(all = 10.dp).fillMaxWidth()) {
                Text(text = "Blackjack")

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(all = 10.dp).fillMaxWidth()
                ) {
                    Button(enabled = game.isGameOver,modifier = Modifier.padding(all = 10.dp), onClick = {
                        setGame(game.deal())
                    }) {
                        Text(text = "Deal")
                    }
                    Button(enabled = !game.isGameOver,modifier = Modifier.padding(all = 10.dp), onClick = {
                        setGame(game.hit())
                    }) {
                        Text(text = "Hit")
                    }
                    Button(enabled = !game.isGameOver,modifier = Modifier.padding(all = 10.dp), onClick = {
                        setGame(game.stay())
                    }) {
                        Text(text = "Stay")
                    }
                }
                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    HandView(game.ph)
                    HandView(game.dh)
                }

                Surface(color = Color.Yellow, modifier = Modifier.fillMaxWidth()) {
                    Text(text = game.msg, textAlign = TextAlign.Center)
                }

            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    Blackjack()
}