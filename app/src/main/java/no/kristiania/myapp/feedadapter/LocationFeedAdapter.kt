 package no.kristiania.myapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.new_item_layout.view.*
import no.kristiania.myapp.gsontypes.Feature
import java.lang.Exception


class LocationFeedAdapter(var onClickImage: View.OnClickListener?, var list : ArrayList<Feature> = ArrayList(), var onClickListener: View.OnClickListener? = null) : RecyclerView.Adapter<LocationFeedAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        try {
            return list.size
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }

        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindLocationItemWithViewHolder(list.get(position))

    }


    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bindLocationItemWithViewHolder(locationItem: Feature) {

            itemView.namePlace.text = locationItem.properties.name

            itemView.tag = locationItem

            itemView.setOnClickListener(onClickListener)

            val imageView : ImageView = itemView.findViewById(R.id.codeLocation)

            imageView.setOnClickListener(onClickImage)

            imageView.tag = locationItem

        }
    }

}
