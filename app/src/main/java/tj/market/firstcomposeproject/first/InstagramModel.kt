package tj.market.firstcomposeproject.first

import tj.market.firstcomposeproject.R

data class InstagramModel(
    val image: Int = R.drawable.ic_launcher_background,
    val postName: String = "Posts",
    val postCount: String = "6.950",
    val followersName: String = "Followers",
    val followersCount: String = "436M",
    val followingName: String = "Following",
    val followingCount: String = "76",
    val count: Int = 14
)