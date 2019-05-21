package com.coding.applicationchallenge.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.coding.applicationchallenge.models.Login



class DatabaseHandler (context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSIOM) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($ID Integer PRIMARY KEY, $USER_NAME TEXT, $PASSWORD TEXT, $COUNTRY TEXT)"
        db?.execSQL(CREATE_TABLE)

        setDefaultUser(db)

    }

    fun setDefaultUser(db: SQLiteDatabase?) {
        // create default label
        val values = ContentValues()
        values.put(USER_NAME, "username")
        values.put(PASSWORD, "123456")
        values.put(COUNTRY, "Singapore")
        var _success = db?.insert(TABLE_NAME, null, values)
        Log.v("InsertedID", "Add login user = $_success")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Called when the database needs to be upgraded
    }

    //Inserting (Creating) data
    fun addLogin(login: Login): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(USER_NAME, login.userName)
        values.put(PASSWORD, login.password)
        values.put(COUNTRY, login.country)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun isLogin(userName: String, password: String): Boolean {
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE  $USER_NAME = '${userName}' AND $PASSWORD = '${password}'"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null && cursor.count > 0) {
            Log.v("isLogin", "count ==== >${cursor.count}")
            return true
        }
        return false
    }
    companion object {
        private val DB_NAME = "LoginDB"
        private val DB_VERSIOM = 1;
        private val TABLE_NAME = "login"
        private val ID = "id"
        private val USER_NAME = "user_name"
        private val PASSWORD = "password"
        private val COUNTRY = "country"
    }
}