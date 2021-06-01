package no.kristiania.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import no.kristiania.myapp.gsontypes.Feature


class MainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var adapter: LocationFeedAdapter

    lateinit var database : Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Database(this, null, null)

        adapter = LocationFeedAdapter(View.OnClickListener {
            val locationItem = it?.tag as Feature
            Intent(this, MapsActivity::class.java).also { intent ->
                val extra = Bundle()
                extra.putString("X", locationItem.sofa.coordinates[0].toString())
                extra.putString("Y", locationItem.sofa.coordinates[1].toString())
                intent.putExtras(extra)
                startActivity(intent)
            }
        })
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        adapter.list = database.getLocations(this)
        adapter.notifyDataSetChanged()

        adapter.onClickListener = this



    }

    private fun filter(text: String) {

        val placeList = database.getLocations(this, text)

        adapter.list = placeList
        adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {

        val locationItem = v?.tag as Feature

        val id = locationItem.properties.id.toString()

        if(id.isNotEmpty()) {
            Intent(this, LocationActivity::class.java).also { intent ->
                val extra = Bundle()
                extra.putString("ID", id)
                intent.putExtras(extra)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, "No info available", Toast.LENGTH_SHORT).show()
        }
    }
}
