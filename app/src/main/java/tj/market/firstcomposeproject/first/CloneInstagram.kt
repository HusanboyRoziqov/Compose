package tj.market.firstcomposeproject.first

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("RememberReturnType")
@Composable
fun CloneInstagram(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    instagramModel: InstagramModel,
    onCLick: () -> Unit
) {

    val isFollowing by viewModel.isFollowing.observeAsState(initial = false)



    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Color.Black)
    )
    {

        Column(
            modifier
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = modifier
                        .padding(4.dp)
                        .weight(1f)
                        .size(60.dp)
                        .clip(CircleShape),

                    painter = painterResource(instagramModel.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = if (instagramModel.count % 2 == 0) ColorFilter.tint(Color.Black) else ColorFilter.tint(
                        Color.Blue
                    )
                )

                Statics(
                    title = instagramModel.postCount,
                    value = instagramModel.postName,
                    modifier = modifier.weight(1f)
                )
                Statics(
                    title = instagramModel.followersCount,
                    value = instagramModel.followersName,
                    modifier = modifier.weight(1f)
                )
                Statics(
                    title = instagramModel.followingCount,
                    value = instagramModel.followingCount,
                    modifier = modifier.weight(1f)
                )

            }
            Spacer(modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                FollowButton(isFollowing) {
                    viewModel.changeFollowingStatus()
                    onCLick()
                }
                Spacer(Modifier.width(16.dp))
                val color = if (instagramModel.count % 2 == 0) Color.Black else Color.Blue
                Text("Count: ${instagramModel.count}", color = color)
            }
        }
    }

}

@Composable
fun FollowButton(isFollowing: Boolean, onCLick: () -> Unit) {
    Button(

        onClick = { onCLick() },
        colors = if (isFollowing) ButtonDefaults.buttonColors(containerColor = Color.Blue)
        else ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        if (isFollowing) {
            Text("Followed")
        } else {
            Text("Unfollowing")
        }
    }
}

@Composable
fun Statics(modifier: Modifier = Modifier, title: String, value: String) {
    Column {
        Text(title, fontStyle = FontStyle.Italic, fontSize = 22.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(value, fontWeight = FontWeight.Black, fontSize = 14.sp)
    }
}