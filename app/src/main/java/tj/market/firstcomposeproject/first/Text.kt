package tj.market.firstcomposeproject.first

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun TextExample(modifier: Modifier = Modifier) {

    Text(

        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    color = Color.Blue
                )
            ) {
                append("Hello")
            }
            append(" ")
            withStyle(SpanStyle(fontWeight = FontWeight.Black, color = Color.Red)) {
                append("World")
            }

        }


    )

}