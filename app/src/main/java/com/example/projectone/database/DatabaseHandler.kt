package com.example.projectone.database

import Details
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.projectone.Util
import java.sql.SQLException

class DatabaseHandler(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {


            db?.execSQL(CREATE_DETAILS_TABLE)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun addItem(details: Details): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
  //     contentValues.put("detailsID", details._id)
        contentValues.put("name", details.productName)
        contentValues.put("image", details.image)
        contentValues.put("description", details.description)
        contentValues.put("quantity", details.quantity/100)
        contentValues.put("price", details.price)

        //insert one row
        val success = db.insert(TABLE_NAME, null, contentValues)
        //close the db
        db.close()
        return success
    }
    //method to delete data
    fun deleteItem(details: Details): Int {
        val db = this.writableDatabase
     //   val contentValues = ContentValues()
   //     contentValues.put("detailsID", details.subId)
        //delete a row
        val success = db.delete(TABLE_NAME, "name = " + details.productName, null)
        db.close()
        return success
    }
    //method to update data
    fun updateEItem(details: Details): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
   //     contentValues.put("detailsID", details._id)
        contentValues.put("name", details.productName)
        contentValues.put("image", details.image)
        contentValues.put("description", details.description)
        contentValues.put("quantity", details.quantity/100)
        contentValues.put("price", details.price)

        //update row
        val success = db.update(TABLE_NAME, contentValues, "name = " + details.productName, null)
        db.close()
        return success
    }

    fun getItems(): ArrayList<Details> {
        val details = ArrayList<Details>()
        val db =this.writableDatabase
        val cursor: Cursor? = db.query(TABLE_NAME, null, null, null, null, null, null)

        cursor?.let {
            while(it.moveToNext()) {
         //       val detailsID = it.getString(cursor.getColumnIndexOrThrow("detailsID"))
                val name = it.getString(cursor.getColumnIndexOrThrow("name"))
                val image = it.getString(cursor.getColumnIndexOrThrow("image"))
                val description =it.getString(cursor.getColumnIndexOrThrow("description"))
                val quantity =  it.getInt(cursor.getColumnIndexOrThrow("quantity"))
                val price =  it.getInt(cursor.getColumnIndexOrThrow("price"))

                val emp = Details(0,"",Util.catId.toInt(),"",description,image,0,0,price,name, quantity,true,
                    Util.subCatId.toInt(),"")
                details.add(emp)
            }
            it.close()
        }


        return details
    }
    companion object{

       const val DATABASE_NAME="ProjectOne"
        const val TABLE_NAME="details"
        const val CREATE_DETAILS_TABLE="""
            CREATE TABLE details( 
            name TEXT PRIMARY KEY,
            image TEXT,
            description TEXT,
            quantity INTEGER,
            price INTEGER 
            
            )
        """


    }

}
