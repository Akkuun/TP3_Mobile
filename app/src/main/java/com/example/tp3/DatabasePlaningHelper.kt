package com.example.tp3//
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabasePlaningHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "planing.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_PLANING = "planing"
        private const val COLUMN_USER_LOGIN = "user_login"
        private const val COLUMN_SLOT1 = "slot1"
        private const val COLUMN_SLOT2 = "slot2"
        private const val COLUMN_SLOT3 = "slot3"
        private const val COLUMN_SLOT4 = "slot4"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_PLANING + "("
                + COLUMN_USER_LOGIN + " TEXT,"
                + COLUMN_SLOT1 + " TEXT,"
                + COLUMN_SLOT2 + " TEXT,"
                + COLUMN_SLOT3 + " TEXT,"
                + COLUMN_SLOT4 + " TEXT" + ")")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PLANING")
        onCreate(db)
    }

    fun insertPlaning(planing: Planing): Boolean {
        val db = this.writableDatabase
        db.delete(TABLE_PLANING, "$COLUMN_USER_LOGIN = ?", arrayOf(planing.userLogin))
        val contentValues = ContentValues().apply {
            put(COLUMN_USER_LOGIN, planing.userLogin)
            put(COLUMN_SLOT1, planing.slot1)
            put(COLUMN_SLOT2, planing.slot2)
            put(COLUMN_SLOT3, planing.slot3)
            put(COLUMN_SLOT4, planing.slot4)
        }
        val result = db.insert(TABLE_PLANING, null, contentValues)
        db.close()
        return result != -1L
    }




    @SuppressLint("Range")
    fun getPlaning(userLogin: String): Planing {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_PLANING WHERE $COLUMN_USER_LOGIN = '$userLogin'"
        val result = db.rawQuery(query, null)
        result.moveToFirst()
        val planing = Planing(
            result.getString(result.getColumnIndex(COLUMN_USER_LOGIN)),
            result.getString(result.getColumnIndex(COLUMN_SLOT1)),
            result.getString(result.getColumnIndex(COLUMN_SLOT2)),
            result.getString(result.getColumnIndex(COLUMN_SLOT3)),
            result.getString(result.getColumnIndex(COLUMN_SLOT4))
        )
        result.close()
        db.close()
        return planing
    }


}
