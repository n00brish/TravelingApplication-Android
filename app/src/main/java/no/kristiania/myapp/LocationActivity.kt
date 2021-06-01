package no.kristiania.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import no.kristiania.myapp.api.ItemList
import no.kristiania.myapp.api.RssFeed
import no.kristiania.myapp.gsontypes.LocationDetail
import no.kristiania.myapp.util.Util
import kotlinx.android.synthetic.main.activity_location.*
import com.squareup.picasso.Picasso

@Suppress("NAME_SHADOWING")
class LocationActivity : AppCompatActivity(), ItemList{

    companion object{
        const val ID = ""
        const val X = ""
        const val Y = ""
    }

    lateinit var location : LocationDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)



        val extra = this.intent.extras
        val id = extra?.getString("ID").toString()


        val endpoint = "https://www.noforeignland.com/home/api/v1/place?id=$id"

        if(Util.isNetworkAvailable(this)){
            RssFeed(this).execute(endpoint)
        } else {
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_LONG).show()
        }




    }

    override fun onSucsess(newItem: MutableList<Any>?) {
        val item = newItem?.first()

        item as LocationDetail

        location = item

        location_textView.loadData(location.place.name, "text/html", "UTF-8")
        location_placename.text = location.place.name
        if(location.place.name.isNotEmpty()) {
            Picasso.get()
                .load(location.place.name)
                .resize(400, 200)
                .centerCrop()
                .into(location_imageView)
        }

    }

    override fun onError() {
        println("on Error")
    }
}