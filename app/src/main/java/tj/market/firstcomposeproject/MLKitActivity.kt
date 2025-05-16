package tj.market.firstcomposeproject

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import kotlin.reflect.KProperty


class MLKitActivity : ComponentActivity() {

    var mutableStateUri by mutableSetOf<Uri?>(null)

    var recognizedText by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {


            val registerSelectImage =
                registerForActivityResult(ActivityResultContracts.GetContent()) {
                    it?.let {
                        mutableStateUri = it
                    }
                }

            Column(Modifier.padding(16.dp)) {
                Image(contentScale = ContentScale.Crop)
                Button(onClick = {

                }) {

                    Text("Select Image")
                }

                Text("Detect text will appear here...", Modifier.padding(top = 16.dp))


            }


        }


    }

    private fun recognizeTextFromImage(uri: Uri) {
        try {
            val image = InputImage.fromFilePath(this, uri)
            val recognizer = TextRecognition.getClient()
            recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    recognizedText = visionText.text
                }
                .addOnFailureListener { e ->
                    recognizedText = "Error: ${e.message}"
                }
        } catch (e: Exception) {
            recognizedText = "Failed to load image."
        }
    }
}

private operator fun Any.getValue(z: Nothing?, property: KProperty<*>): Any {

}
