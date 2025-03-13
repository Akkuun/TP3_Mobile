package com.example.tp3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"

        private const val COLUMN_LOGIN = "login"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_NOM = "nom"
        private const val COLUMN_PRENOM = "prenom"
        private const val COLUMN_TELEPHONE = "telephone"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_DATE_NAISSANCE = "date_naissance"
        private const val COLUMN_HOBBIES = "hobbies"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_LOGIN TEXT PRIMARY KEY,
                $COLUMN_PASSWORD TEXT,
                $COLUMN_NOM TEXT,
                $COLUMN_PRENOM TEXT,
                $COLUMN_TELEPHONE TEXT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_DATE_NAISSANCE TEXT,
                $COLUMN_HOBBIES TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun insertUser(user: User): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_LOGIN, user.login)
            put(COLUMN_PASSWORD, user.password)
            put(COLUMN_NOM, user.nom)
            put(COLUMN_PRENOM, user.prenom)
            put(COLUMN_TELEPHONE, user.telephone)
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_DATE_NAISSANCE, user.dateNaissance)
            put(COLUMN_HOBBIES, user.hobbies)
        }

        val result = db.insert(TABLE_USERS, null, values)
        db.close()
        return result != -1L
    }

    fun isLoginExists(login: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_LOGIN = ?"
        val cursor = db.rawQuery(query, arrayOf(login))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun checkUser(login: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_LOGIN = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(login, password))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists

    }

    fun insertPlaning(planing: Planing): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("slot1", planing.slot1)
            put("slot2", planing.slot2)
            put("slot3", planing.slot3)
            put("slot4", planing.slot4)
        }

        val result = db.insert("planing", null, values)
        db.close()
        return result != -1L

    }
}
