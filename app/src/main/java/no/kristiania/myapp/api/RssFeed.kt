 package no.kristiania.myapp.api

import android.os.AsyncTask
import com.google.gson.Gson
import no.kristiania.myapp.gsontypes.LocationDetail
import no.kristiania.myapp.gsontypes.SailLocations
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception

class RssFeed(var listener: ItemList?) : AsyncTask<String, Any, MutableList<Any>>() {

    override fun doInBackground(vararg params: String?): MutableList<Any> {

        publishProgress(0)

        try {
            lateinit var newsItem: MutableList<Any>

            var jsonResponse = callWebRequest(params.get(0)!!)
            newsItem = parseLocations(jsonResponse)

            return newsItem
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return arrayListOf()
    }


    override fun onPostExecute(result: MutableList<Any>) {
        super.onPostExecute(result)

        if (result.isEmpty())
            listener?.onError()
        else
            listener?.onSucsess(result)
    }

    private fun callWebRequest(url: String): String {

        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url(url)
            .build()

        println("#loader, please wait")
        var response = client.newCall(request).execute()
        println("# thanks!")
        return response.body!!.string()

    }

    private fun parseLocations(json: String): MutableList<Any> {

        var gson = Gson()

        if(json.startsWith("{\"snapshots\"")) {
            val type = object : TypeToken<LocationDetail>(){}.type

            val location = gson.fromJson<LocationDetail>(json, type)

            return mutableListOf(location)
        } else {

            val type = object : TypeToken<SailLocations>() {}.type

            println("#Starting fetching")
            val locations = gson.fromJson<SailLocations>(json, type)

            println("#Ending fetcing")
            return mutableListOf(locations.features)
        }

        return mutableListOf()
    }


}