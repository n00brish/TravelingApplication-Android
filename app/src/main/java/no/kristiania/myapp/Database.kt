package no.kristiania.myapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import no.kristiania.myapp.gsontypes.Feature
import no.kristiania.myapp.gsontypes.Properties
import java.lang.Exception


class Database (
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?
) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "firstDB.db"
        private val DATABASE_VERSION = 1

        val TABLE_NAME = "Locations"
        val COLUMN_ID = "locationID"
        val COLUMN_NAME = "name"
        val COLUMN_TYPE = "type"
        val COLUMN_ICON = "icon"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PLACE_TABLE = ("CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$COLUMN_ID LONG PRIMARY KEY," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_TYPE TEXT," +
                "$COLUMN_ICON TEXT,")
        db?.execSQL(CREATE_PLACE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getLocations(mCtx: Context): ArrayList<Feature> {
        val qry = "Select * From $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val locations = ArrayList<Feature>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No Records Found", Toast.LENGTH_SHORT).show() else {
            while (cursor.moveToNext()) {
                val properties = Properties()
                val location = Feature()
                properties.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                properties.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                location.type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE))
                location.properties = properties
                locations.add(location)
            }
            Toast.makeText(mCtx, "${cursor.count} Records found", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return locations
    }

    fun getLocations(mCtx: Context, text: String): ArrayList<Feature> {

        val qry = "SELECT * FROM $TABLE_NAME  WHERE $COLUMN_NAME LIKE '$text%'"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val locations = ArrayList<Feature>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No Records Found", Toast.LENGTH_SHORT).show() else {
            while (cursor.moveToNext()) {
                val properties = Properties()
                val location = Feature()
                properties.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                properties.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                location.type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE))
                location.properties = properties
                locations.add(location)
            }
            Toast.makeText(mCtx, "${cursor.count} Records found", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return locations
    }

    fun addLocation(locations: MutableList<Feature>) {

        val db = this.writableDatabase

        db.beginTransaction()
        for (place in locations) {
            val values = ContentValues()
            values.put(COLUMN_ID, place.properties.id)
            values.put(COLUMN_NAME, place.properties.name)
            values.put(COLUMN_TYPE, place.type)
            values.put(COLUMN_ICON, place.properties.icon)
            try {
                db.insertOrThrow(TABLE_NAME, null, values)
            } catch (e: Exception) {
                println("#asfa failed to input data")
            }
        }
        db.setTransactionSuccessful()
        db.endTransaction()
        db.close()
    }
}