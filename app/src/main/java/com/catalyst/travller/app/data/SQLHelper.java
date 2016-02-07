package com.catalyst.travller.app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rankush.agrawal on 06-02-2016.
 */
public class SQLHelper extends SQLiteOpenHelper {

    private static final String TAG = SQLHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "Traveller.db";

    public static final String EVENT_TABLE_NAME = "EventTable";
    public static final String COL_EVENT_ID = "ID";
    public static final String COL_EVENT_START_TIME = "START_TIME";
    public static final String COL_EVENT_END_TIME = "END_TIME";
    public static final String COL_EVENT_STATUS = "STATUS";
    public static final String COL_EVENT_CREATOR_ID = "CREATOR_ID";
    public static final String COL_EVENT_NAME = "NAME";
    public static final String COL_EVENT_DESCRIPTION = "DESCRIPTION";

    public static final String EVENT_USER_TABLE_NAME = "UserAuthenticationTable";
    public static final String COL_USER_ID = "USER";
    public static final String COL_USER_PASSWORD = "PASSWORD";
    public static final String COL_USER_NAME = "NAME";
    public static final String COL_USER_PHONE = "PHONE";

    public static final String EVENT_LOCATION_TABLE_NAME = "EventLocationTable";
    public static final String COL_LOCATION_LONGITUDE = "LONGITUDE";
    public static final String COL_LOCATION_LATITUDE = "LATITUDE";
    public static final String COL_LOCATION_NAME = "NAME";
    public static final String COL_LOCATION_DESCRIPTION = "DESC";


    public static final String EVENT_ATTENDEES_TABLE_NAME = "EventAttendeesTable";

    private static final String WHERE_EQUALS = " =? ";
    private static final String WHERE_GREATER= " >=? ";


    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EVENT_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "START_TIME INTEGER ," +
                "END_TIME INTEGER ," +
                "CREATOR_ID TEXT," +
                "NAME TEXT," +
                "DESCRIPTION TEXT," +
                "STATUS INTEGER)");

        db.execSQL("create table " + EVENT_USER_TABLE_NAME + " (USER TEXT PRIMARY KEY ," +
                "NAME TEXT," +
                "PHONE INTEGER," +
                "PASSWORD TEXT)");

        db.execSQL("create table " + EVENT_LOCATION_TABLE_NAME + " (ID INTEGER ," +
                "LONGITUDE DOUBLE," +
                "LATITUDE DOUBLE," +
                "NAME TEXT," +
                "DESC TEXT)");


        db.execSQL("create table " + EVENT_ATTENDEES_TABLE_NAME + " (ID INTEGER  ," +
                "USER TEXT," +
                "NAME TEXT," +
                "PHONE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_LOCATION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_ATTENDEES_TABLE_NAME);
        onCreate(db);
    }


    // Event CRUD Operation

    public boolean insertEvent(EventInfoBean event) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_EVENT_START_TIME, event.getEventStartTime());
            values.put(COL_EVENT_END_TIME, event.getEventEndTime());
            values.put(COL_EVENT_CREATOR_ID, event.getEventCreatorId());
            values.put(COL_EVENT_NAME, event.getEventName());
            values.put(COL_EVENT_DESCRIPTION, event.getEventDesc());
            values.put(COL_EVENT_STATUS, event.getEventStatus());
            db.insert(EVENT_TABLE_NAME, null, values);
            db.close();

            return true;
        } catch (SQLiteException se) {
            Log.e(TAG, Log.getStackTraceString(se));
            return false;
        } catch (Exception e) {
            Log.v(TAG, Log.getStackTraceString(e));
            return false;
        } finally {
            db.close();
        }
    }


    public List<EventInfoBean> getNewEvents(long time){
        SQLiteDatabase db = this.getReadableDatabase();
        List<EventInfoBean> eventList = new ArrayList<EventInfoBean>();
        try {

            Cursor cursor = db.query(EVENT_TABLE_NAME, null, COL_EVENT_CREATOR_ID + WHERE_GREATER, new String[]{String.valueOf(time)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    EventInfoBean event = new EventInfoBean(
                            cursor.getInt(cursor.getColumnIndex(COL_EVENT_START_TIME)),
                            cursor.getInt(cursor.getColumnIndex(COL_EVENT_END_TIME)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_CREATOR_ID)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_NAME)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_DESCRIPTION))
                    );
                    event.setEventStatus(cursor.getInt(cursor.getColumnIndex(COL_EVENT_STATUS)));

                    eventList.add(event);
                }while(cursor.moveToNext());
            }
        } catch (SQLiteException se) {
            Log.v(TAG,
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v(TAG,
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }

        return eventList;

    }
    public List<EventInfoBean> getAllEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<EventInfoBean> eventList = new ArrayList<EventInfoBean>();
        try {

            Cursor cursor = db.query(EVENT_TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    EventInfoBean event = new EventInfoBean(
                            cursor.getInt(cursor.getColumnIndex(COL_EVENT_START_TIME)),
                            cursor.getInt(cursor.getColumnIndex(COL_EVENT_END_TIME)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_CREATOR_ID)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_NAME)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_DESCRIPTION))
                    );
                    event.setEventStatus(cursor.getInt(cursor.getColumnIndex(COL_EVENT_STATUS)));

                    eventList.add(event);
                }while(cursor.moveToNext());
            }
        } catch (SQLiteException se) {
            Log.v(TAG,
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v(TAG,
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }

        return eventList;

    }


    // User CRUD Operation

    public boolean insertUser(UserInfoBean user) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_USER_ID, user.getUser());
            values.put(COL_USER_PASSWORD, user.getPassword());
            values.put(COL_USER_NAME, user.getName());
            values.put(COL_USER_PHONE, user.getPhone());
            db.insert(EVENT_USER_TABLE_NAME, null, values);
            db.close();

            return true;
        } catch (SQLiteException se) {
            Log.e(TAG, Log.getStackTraceString(se));
            return false;
        } catch (Exception e) {
            Log.v(TAG, Log.getStackTraceString(e));
            return false;
        } finally {
            db.close();
        }
    }

    public List<EventInfoBean> getEventsCreatedByUser(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        List<EventInfoBean> eventList = new ArrayList<EventInfoBean>();
        try {

            Cursor cursor = db.query(EVENT_TABLE_NAME, null, COL_EVENT_CREATOR_ID + WHERE_EQUALS, new String[]{user}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    EventInfoBean event = new EventInfoBean(
                            cursor.getInt(cursor.getColumnIndex(COL_EVENT_START_TIME)),
                            cursor.getInt(cursor.getColumnIndex(COL_EVENT_END_TIME)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_CREATOR_ID)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_NAME)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_DESCRIPTION))
                    );
                    event.setEventStatus(cursor.getInt(cursor.getColumnIndex(COL_EVENT_STATUS)));

                    eventList.add(event);
                }while(cursor.moveToNext());
            }
        } catch (SQLiteException se) {
            Log.v(TAG,
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v(TAG,
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }

        return eventList;
    }

    public List<EventInfoBean> getEventsAttendingByUser(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        List<EventInfoBean> eventList = new ArrayList<EventInfoBean>();
        try {

            String query = "SELECT * FROM "+EVENT_TABLE_NAME +
                    " WHERE "+ COL_EVENT_ID +" IN ( "+
                    "SELECT DISTINCT "+ COL_EVENT_ID + " FROM " + EVENT_ATTENDEES_TABLE_NAME +
                    "WHERE " + COL_USER_ID +" = " + user + " )";

            Cursor cursor = db.rawQuery(query,null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    EventInfoBean event = new EventInfoBean(
                            cursor.getInt(cursor.getColumnIndex(COL_EVENT_START_TIME)),
                            cursor.getInt(cursor.getColumnIndex(COL_EVENT_END_TIME)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_CREATOR_ID)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_NAME)),
                            cursor.getString(cursor.getColumnIndex(COL_EVENT_DESCRIPTION))
                    );
                    event.setEventStatus(cursor.getInt(cursor.getColumnIndex(COL_EVENT_STATUS)));

                    eventList.add(event);
                }while(cursor.moveToNext());
            }
        } catch (SQLiteException se) {
            Log.v(TAG,
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v(TAG,
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }

        return eventList;

    }



    public boolean validateUser(String user, String password) {
        boolean retValue = false;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.query(EVENT_USER_TABLE_NAME, new String[]{COL_USER_PASSWORD}, COL_USER_ID + WHERE_EQUALS, new String[]{user}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                if (password.equals(cursor.getString(cursor.getColumnIndex(COL_USER_PASSWORD)))) {
                    retValue = true;
                }
            }
        } catch (SQLiteException se) {
            Log.v(TAG,
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v(TAG,
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }
        return retValue;
    }

    // Location CRUD Operation

    public boolean insertLocation(LocationBean location) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_EVENT_ID, location.getEventId());
            values.put(COL_LOCATION_LONGITUDE, location.getLongitude());
            values.put(COL_LOCATION_LATITUDE, location.getLatitude());
            values.put(COL_LOCATION_NAME, location.getName());
            values.put(COL_LOCATION_DESCRIPTION, location.getDesc());
            db.insert(EVENT_LOCATION_TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (SQLiteException se) {
            Log.e(TAG, Log.getStackTraceString(se));
            return false;
        } catch (Exception e) {
            Log.v(TAG, Log.getStackTraceString(e));
            return false;
        } finally {
            db.close();
        }
    }
    public List<UserInfoBean> getLocation(int eventid){
        SQLiteDatabase db = this.getReadableDatabase();
        List<UserInfoBean> userList = new ArrayList<UserInfoBean>();
        try {

            Cursor cursor = db.query(EVENT_ATTENDEES_TABLE_NAME, null, COL_EVENT_ID + WHERE_EQUALS, new String[]{String.valueOf(eventid)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    UserInfoBean user = new UserInfoBean(
                            cursor.getString(cursor.getColumnIndex(COL_USER_ID)),
                            null,
                            cursor.getString(cursor.getColumnIndex(COL_USER_NAME)),
                            cursor.getLong(cursor.getColumnIndex(COL_USER_PHONE))
                    );
                    userList.add(user);
                }while(cursor.moveToNext());
            }
        } catch (SQLiteException se) {
            Log.v(TAG,
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v(TAG,
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }

        return userList;
    }

    // Attendee CRUD Operation

    public boolean insertAttendee(AttendeeBean attendee) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            UserInfoBean userInfo = attendee.getUserInfo();
            ContentValues values = new ContentValues();
            values.put(COL_EVENT_ID, attendee.getEventId());
            values.put(COL_USER_ID, userInfo.getUser());
            values.put(COL_USER_PASSWORD, userInfo.getPassword());
            values.put(COL_USER_NAME, userInfo.getName());
            values.put(COL_USER_PHONE, userInfo.getPhone());
            db.insert(EVENT_LOCATION_TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (SQLiteException se) {
            Log.e(TAG, Log.getStackTraceString(se));
            return false;
        } catch (Exception e) {
            Log.v(TAG, Log.getStackTraceString(e));
            return false;
        } finally {
            db.close();
        }
    }

    public List<UserInfoBean> getEventAttendies(int eventid){
        SQLiteDatabase db = this.getReadableDatabase();
        List<UserInfoBean> userList = new ArrayList<UserInfoBean>();
        try {

            Cursor cursor = db.query(EVENT_ATTENDEES_TABLE_NAME, null, COL_EVENT_ID + WHERE_EQUALS, new String[]{String.valueOf(eventid)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    UserInfoBean user = new UserInfoBean(
                            cursor.getString(cursor.getColumnIndex(COL_USER_ID)),
                            null,
                            cursor.getString(cursor.getColumnIndex(COL_USER_NAME)),
                            cursor.getLong(cursor.getColumnIndex(COL_USER_PHONE))
                            );
                    userList.add(user);
                }while(cursor.moveToNext());
            }
        } catch (SQLiteException se) {
            Log.v(TAG,
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v(TAG,
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }

        return userList;
    }

}