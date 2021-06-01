package no.kristiania.myapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash_screen.*
import no.kristiania.myapp.api.ItemList
import no.kristiania.myapp.api.RssFeed
import no.kristiania.myapp.gsontypes.Feature
import no.kristiania.myapp.util.Util

class SplashScreenActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        iv_note.alpha = 0f
        iv_note.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
