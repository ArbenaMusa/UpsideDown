package com.am.upsidedown;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.am.upsidedown.models.AppUser;
import com.am.upsidedown.models.AppUserModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "internalDbr";
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
                AppUser.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AppUser.Name + " TEXT NOT NULL," +
                AppUser.Surname + " TEXT NOT NULL," +
                AppUser.Email + " TEXT NOT NULL" +
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
     * @param appUserModel
     */
    void addAppUser(AppUserModel appUserModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AppUser.Name, appUserModel.getName());
        values.put(AppUser.Surname, appUserModel.getSurname());
        values.put(AppUser.Email, appUserModel.getEmail());

        db.insert(TABLE_APP_USERS, null, values);
        db.close();
    }

    /**
     * This method gets a specific app user from internal database.
     * @param id
     * @return
     */
    AppUserModel getAppUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_APP_USERS, new String[] { AppUser.ID,
                        AppUser.Name, AppUser.Surname, AppUser.Email}, AppUser.ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        AppUserModel appUserModel = new AppUserModel(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return appUserModel;
    }

    /**
     * This method get all app user from the internal database.
     * @return
     */
    public List<AppUserModel> getAllAppUsers() {
        List<AppUserModel> appUsersList = new ArrayList<AppUserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_APP_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                AppUserModel appUserModel = new AppUserModel();
                appUserModel.setId(Integer.parseInt(cursor.getString(0)));
                appUserModel.setName(cursor.getString(1));
                appUserModel.setSurname(cursor.getString(2));
                appUserModel.setEmail(cursor.getString(3));

                appUsersList.add(appUserModel);
            } while (cursor.moveToNext());
        }

        return appUsersList;
    }

    /**
     * THis method updates a specific app user.
     * @param appUserModel
     * @return
     */
    public int updateAppUser(AppUserModel appUserModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AppUser.Name, appUserModel.getName());
        values.put(AppUser.Surname, appUserModel.getSurname());
        values.put(AppUser.Email, appUserModel.getEmail());

        return db.update(TABLE_APP_USERS, values, AppUser.ID + " = ?",
                new String[] { String.valueOf(appUserModel.getId()) });
    }

    /**
     * THis method deletes a specific app user from internal database.
     * @param appUserModel
     */
    public void deleteAppUser(AppUserModel appUserModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_APP_USERS, AppUser.ID + " = ?",
                new String[] { String.valueOf(appUserModel.getId()) });
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
