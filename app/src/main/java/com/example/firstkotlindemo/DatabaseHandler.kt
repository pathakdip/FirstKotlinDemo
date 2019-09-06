package com.example.firstkotlindemo

import android.accounts.AccountManager.KEY_PASSWORD
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.firstkotlindemo.model.EmpModelClass

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "EmployeeDatabase"
        private val TABLE_CONTACTS = "EmployeeTable"
        private val KEY_ID = "id"
        private val KEY_EMAIL = "email"
        private val KEY_PASSWORD = "password"
        private val KEY_NAME = "name"
        private val KEY_CONTACT = "contact"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT," + KEY_NAME + " TEXT," + KEY_CONTACT + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addEmployee(emp: EmpModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_EMAIL, emp.userEmail) // EmpModelClass email
        contentValues.put(KEY_PASSWORD,emp.userPassword ) // EmpModelClass password
        contentValues.put(KEY_NAME,emp.userName ) // EmpModelClass name
        contentValues.put(KEY_CONTACT,emp.userContact ) // EmpModelClass contact
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun viewEmployee():List<EmpModelClass>{
        val empList:ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        //var userId: Int
        var userEmail: String
        var userPassword: String
        var userName: String
        var userContact: String
        if (cursor.moveToFirst()) {
            do {
                //userId = cursor.getInt(cursor.getColumnIndex("id"))
                userEmail = cursor.getString(cursor.getColumnIndex("email"))
                userPassword = cursor.getString(cursor.getColumnIndex("password"))
                userName=cursor.getString(cursor.getColumnIndex("name"))
                userContact=cursor.getString(cursor.getColumnIndex("contact"))
                val emp= EmpModelClass( userEmail = userEmail, userPassword = userPassword,userName = userName, userContact = userContact)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }
    //method to update data
    fun updateEmployee(emp: EmpModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //contentValues.put(KEY_ID, emp.userId)
        contentValues.put(KEY_EMAIL, emp.userEmail) // EmpModelClass email
        contentValues.put(KEY_PASSWORD,emp.userPassword ) // EmpModelClass password

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues,"email="+emp.userEmail,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun deleteEmployee(emp: EmpModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //contentValues.put(KEY_ID, emp.userId) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS,"email="+emp.userEmail,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }


    fun checkUser(email: String, password: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(KEY_ID)

        val db = this.readableDatabase

        // selection criteria
        val selection = "$KEY_EMAIL = ? AND $KEY_PASSWORD = ?"

        // selection arguments
        val selectionArgs = arrayOf(email, password)

        // query user table with conditions
        val cursor = db.query(TABLE_CONTACTS, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0)
            return true

        return false

    }

}