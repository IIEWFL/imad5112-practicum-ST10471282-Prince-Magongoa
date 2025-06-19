package vcmsa.ci.musicplaylistmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ViewActivity : AppCompatActivity() {
    private val song = mutableListOf<String>()
    private val title = mutableListOf<String>()
    private val rating = mutableListOf<Int>()
    private val comment = mutableListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view)

        song.addAll(intent.getStringArrayListExtra("song") ?: arrayListOf())
        title.addAll(intent.getStringArrayListExtra("title") ?: arrayListOf())
        rating.addAll(intent.getIntegerArrayListExtra("rate") ?: arrayListOf())
        comment.addAll(intent.getStringArrayListExtra("comments") ?: arrayListOf())

        val displayTextView = findViewById<TextView>(R.id.txt_displaysong)
        val exitButton = findViewById<Button>(R.id.btn_ext2)

        exitButton.setOnClickListener {
            finish()
        }

        updateDisplay(displayTextView)
    }

    private fun updateDisplay(displayTextView: TextView) {
        val stringBuilder = StringBuilder()
        var found = false

        for (i in song.indices) {
            if (song[i] == title[i]) {
                found = true
                stringBuilder.append("Song: ${title[i]}\n")
                stringBuilder.append("Artist: ${song[i]}\n")
                stringBuilder.append("Rating: ${rating[i]}\n")
                stringBuilder.append("Comment: ${comment[i]}\n\n")
            }
        }

        if (found) {
            displayTextView.text = stringBuilder.toString()
        } else {
            displayTextView.text = "No songs found"
        }
    }
}
