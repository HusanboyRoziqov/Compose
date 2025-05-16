package tj.market.firstcomposeproject.first

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(innerPadding: PaddingValues) {

    val oldItem = remember { mutableStateOf(InstagramModel()) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(Modifier.padding(innerPadding)) {

            LazyColumn(
                contentPadding = PaddingValues(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 72.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(50, key = { it.toString() }) {

                    val state = rememberSwipeToDismissBoxState(
                        confirmValueChange = {
                            if (it == SwipeToDismissBoxValue.EndToStart) {
                                true
                            } else {
                                false
                            }
                        }
                    )

                    SwipeToDismissBox(
                        modifier = Modifier.animateItemPlacement(),
                        state = state,
                        enableDismissFromStartToEnd = false,
                        backgroundContent = {

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color.Red),
                                contentAlignment = Alignment.CenterEnd
                            ) {

                                Text(
                                    "Item removed",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Black
                                )

                            }
                        }) {

                        CloneInstagram(instagramModel = oldItem.value) {
                            oldItem.value =
                                oldItem.value.copy(count = oldItem.value.count + 1)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    val content by remember { mutableStateOf<String>("Parviz") }
    var searchQuery by remember { mutableStateOf<String>("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            }
        )

        if (isMatch(content, searchQuery)) {
            println("Match found!")
        }
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Settings Screen")
    }
}

fun normalizeText(text: String): String {
    return text.lowercase(Locale.getDefault()).trim()
}

fun transliterateCyrillicToLatin(text: String): String {
    val map = mapOf(
        'А' to "A", 'а' to "a", 'Б' to "B", 'б' to "b",
        'В' to "V", 'в' to "v", 'Г' to "G", 'г' to "g",
        'Д' to "D", 'д' to "d", 'Е' to "E", 'е' to "e",
        'Ж' to "Zh", 'ж' to "zh", 'З' to "Z", 'з' to "z",
        'И' to "I", 'и' to "i", 'Й' to "Y", 'й' to "y",
        'К' to "K", 'к' to "k", 'Л' to "L", 'л' to "l",
        'М' to "M", 'м' to "m", 'Н' to "N", 'н' to "n",
        'О' to "O", 'о' to "o", 'П' to "P", 'п' to "p",
        'Р' to "R", 'р' to "r", 'С' to "S", 'с' to "s",
        'Т' to "T", 'т' to "t", 'У' to "U", 'у' to "u",
        'Ф' to "F", 'ф' to "f", 'Х' to "Kh", 'х' to "kh",
        'Ц' to "Ts", 'ц' to "ts", 'Ч' to "Ch", 'ч' to "ch",
        'Ш' to "Sh", 'ш' to "sh", 'Щ' to "Sch", 'щ' to "sch",
        'Ы' to "Y", 'ы' to "y", 'Э' to "E", 'э' to "e",
        'Ю' to "Yu", 'ю' to "yu", 'Я' to "Ya", 'я' to "ya"
    )
    val builder = StringBuilder()
    for (char in text) {
        builder.append(map[char] ?: char)
    }
    return builder.toString()
}

fun isMatch(content: String, query: String): Boolean {
    val normalizedContent = normalizeText(content)
    val normalizedQuery = normalizeText(query)

    val translitContent = transliterateCyrillicToLatin(normalizedContent)
    val translitQuery = transliterateCyrillicToLatin(normalizedQuery)

    return normalizedContent.contains(normalizedQuery) ||
            translitContent.contains(normalizedQuery) ||
            normalizedContent.contains(translitQuery)
}