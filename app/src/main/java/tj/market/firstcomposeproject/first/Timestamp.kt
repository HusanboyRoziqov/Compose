package tj.market.firstcomposeproject.first

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Timestamp(modifier: Modifier = Modifier) {

    Column(modifier) {

        for (i in 1..9) {

            Row(
                modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                for (j in 1..9) {

                    val backgroundBox = if ((i + j) % 2 == 0) Color.Yellow else Color.White

                    Box(
                        modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(backgroundBox),
                        contentAlignment = Alignment.Center
                    ) {

                        Text((i * j).toString())
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTimestamp() {

    Timestamp()
}