package vcmsa.ci.musicplaylistmanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private val song = mutableListOf<String>()
    private val title = mutableListOf<String>()
    private val rating = mutableListOf<Int>()
    private val comment = mutableListOf<String>()

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val songButton = findViewById<Button>(R.id.btn_add)
        val nextButton = findViewById<Button>(R.id.btn_nxt)
        val exitButton = findViewById<Button>(R.id.btn_ext)

        songButton.setOnClickListener {
            // Show the dialog to add a song
            showAddSongDialog()
        }

        nextButton.setOnClickListener {
            if (song.isNotEmpty()) {
                val intent = Intent(this, main()::class.java)
                intent.putStringArrayListExtra("song", ArrayList(song))
                intent.putStringArrayListExtra("title", ArrayList(title))
                intent.putIntegerArrayListExtra("rate", ArrayList(rating))
                intent.putStringArrayListExtra("comments", ArrayList(comment))
                startActivity(intent)
            } else {
                Snackbar.make(songButton, "Playlist is empty. Add songs first.", Snackbar.LENGTH_SHORT).show()
            }
        }

        exitButton.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }

    private fun showAddSongDialog() {
        // Inflate the dialog layout
        val dialogView = layoutInflater.inflate(R.layout.playlist_dialogue, null)

        val songTitleEditText: EditText = dialogView.findViewById(R.id.txt_tittle)
        val artistEditText: EditText = dialogView.findViewById(R.id.txt_artist)
        val rateEditText: EditText = dialogView.findViewById(R.id.txt_rate)
        val commentEditText: EditText = dialogView.findViewById(R.id.txt_comments)

        // Create and show the dialog
        val dialog = android.app.AlertDialog.Builder(this)
            .setTitle("Add Song")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val songTitle = songTitleEditText.text.toString()
                val artist = artistEditText.text.toString()
                val rate = rateEditText.text.toString()
                val commentText = commentEditText.text.toString()

                if (songTitle.isNotEmpty() && artist.isNotEmpty() && rate.isNotEmpty() && commentText.isNotEmpty()) {
                    song.add(songTitle)
                    title.add(artist)
                    rating.add(rate.toInt())
                    comment.add(commentText)
                } else {
                    Toast.makeText(this, "Can't add empty fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }
}
