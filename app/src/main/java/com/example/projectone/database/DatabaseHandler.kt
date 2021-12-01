package com.example.projectone.database

import Details
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.projectone.Util
import java.lang.Integer.parseInt
import java.sql.SQLException

class DatabaseHandler(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {


            db?.execSQL(CREATE_DETAILS_TABLE)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db)
    }


    fun addItem(details: Details): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
    //    var string = details._id
   //     var result = string.filter { it.isDigit() }

     //  var id:Int=(details.mrp+details.subId)*details.price
        contentValues.put("id",details.IDMine )
        contentValues.put("name", details.productName)
        contentValues.put("image", details.image)
        contentValues.put("description", details.description)
        contentValues.put("quantity", details.QuantityMine)
        contentValues.put("price", details.price)
        contentValues.put("detailId", details._id)

        //insert one row
        val success = db.insert(TABLE_NAME, null, contentValues)
        //close the db
        db.close()
        return success
    }
    //method to delete data
    //   val contentValues = ContentValues()
    //     contentValues.put("detailsID", details.subId)
    //delete a row
    fun deleteItem(details: Details): Int {
        val db = this.writableDatabase
     //   var string = details._id
   //     var result = string.filter { it.isDigit() }
    //    var id:Int=(details.mrp+details.subId)*details.price
        val success = db.delete(TABLE_NAME, "id = ${details.IDMine}",null )
        db.close()
        return success
    }

    //method to update data
    fun updateEItem(details: Details): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
    //    var string = details._id
   //     var result = string.filter { it.isDigit() }
        var id:Int=(details.mrp+details.subId)*details.price
        contentValues.put("id", details.IDMine)
        contentValues.put("name", details.productName)
        contentValues.put("image", details.image)
        contentValues.put("description", details.description)
        contentValues.put("quantity", details.QuantityMine)
        contentValues.put("price", details.price)
        contentValues.put("detailId", details._id)

        //update row
        val success = db.update(TABLE_NAME, contentValues, "id = " + details.IDMine, null)
        db.close()
        return success
    }

    var total:Int=0
    fun getItems(): ArrayList<Details> {
        val details = ArrayList<Details>()
        val db =this.writableDatabase
        val cursor: Cursor? = db.query(TABLE_NAME, null, null, null, null, null, null)

        cursor?.let {
            while(it.moveToNext()) {
                val id = it.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = it.getString(cursor.getColumnIndexOrThrow("name"))
                val image = it.getString(cursor.getColumnIndexOrThrow("image"))
                val description =it.getString(cursor.getColumnIndexOrThrow("description"))
                val quantity =  it.getInt(cursor.getColumnIndexOrThrow("quantity"))
                val price =  it.getInt(cursor.getColumnIndexOrThrow("price"))
                    total+=price*quantity
                val detailID =  it.getString(cursor.getColumnIndexOrThrow("detailId"))
                val emp = Details(0,detailID?:"0",Util.catId.toInt(),"",description,image,0,0,price,name, quantity,true,
                    Util.subCatId.toInt(),"",id,quantity)
                details.add(emp)
            }

            it.close()
        }


        return details
    }
    @JvmName("getTotal1")
    fun getTotal():Int{
        return total
    }
    companion object{

       const val DATABASE_NAME="ProjectOne"
        const val TABLE_NAME="details"
        const val CREATE_DETAILS_TABLE="""
            CREATE TABLE details( 
            id INTEGER PRIMARY KEY AUTOINCREMENT ,
            name TEXT ,
            image TEXT,
            description TEXT,
            quantity INTEGER,
            price INTEGER ,
            detailId TEXT
            
            )
        """


    }

}
