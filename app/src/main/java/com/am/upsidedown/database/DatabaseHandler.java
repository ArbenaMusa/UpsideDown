package com.am.upsidedown.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.am.upsidedown.models.AppUser;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "internalDb";
    private static final String TABLE_APP_USERS = "appUsers";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method created appUsers table.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String strQuery = "CREATE TABLE " + TABLE_APP_USERS + " (" +
                DatabaseUser.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseUser.Name + " TEXT NOT NULL," +
                DatabaseUser.Surname + " TEXT NOT NULL," +
                DatabaseUser.Email + " TEXT NOT NULL" +
                ")";

        db.execSQL(strQuery);
    }

    /**
     * THis methods upgrades appUsers table.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APP_USERS);

        // Create tables again
        onCreate(db);
    }

    /**
     * This method adds a new app user on internal database.
     * @param appUser
     */
    public void addAppUser(AppUser appUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseUser.Name, appUser.getName());
        values.put(DatabaseUser.Surname, appUser.getSurname());
        values.put(DatabaseUser.Email, appUser.getEmail());

        db.insert(TABLE_APP_USERS, null, values);
        db.close();
    }

    /**
     * This method gets a specific app user from internal database.
     * @param id
     * @return
     */
    public AppUser getAppUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_APP_USERS, new String[] { DatabaseUser.ID,
                        DatabaseUser.Name, DatabaseUser.Surname, DatabaseUser.Email}, DatabaseUser.ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        AppUser appUser = new AppUser(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return appUser;
    }

    /**
     * This method get all app user from the internal database.
     * @return
     */
    public List<AppUser> getAllAppUsers() {
        List<AppUser> appUsersList = new ArrayList<AppUser>();

        String selectQuery = "SELECT  * FROM " + TABLE_APP_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                AppUser appUser = new AppUser();
                appUser.setId(Integer.parseInt(cursor.getString(0)));
                appUser.setName(cursor.getString(1));
                appUser.setSurname(cursor.getString(2));
                appUser.setEmail(cursor.getString(3));

                appUsersList.add(appUser);
            } while (cursor.moveToNext());
        }

        return appUsersList;
    }

    /**
     * THis method updates a specific app user.
     * @param appUser
     * @return
     */
    public int updateAppUser(AppUser appUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseUser.Name, appUser.getName());
        values.put(DatabaseUser.Surname, appUser.getSurname());
        values.put(DatabaseUser.Email, appUser.getEmail());

        return db.update(TABLE_APP_USERS, values, DatabaseUser.ID + " = ?",
                new String[] { String.valueOf(appUser.getId()) });
    }

    /**
     * THis method deletes a specific app user from internal database.
     * @param appUser
     */
    public void deleteAppUser(AppUser appUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_APP_USERS, DatabaseUser.ID + " = ?",
                new String[] { String.valueOf(appUser.getId()) });
        db.close();
    }

    /**
     * This method gets the total number of app users that internal database contains.
      * @return
     */
    public int getAppUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_APP_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
