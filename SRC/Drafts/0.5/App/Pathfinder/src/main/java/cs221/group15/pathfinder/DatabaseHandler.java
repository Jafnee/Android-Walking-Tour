/*
 *@(#)LocationListen.java 0.5 2014-01-31
 * 
 * Copyright (c)2014 Aberystwyth University.
 * All rights reserved.
 */


package cs221.group15.pathfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.location.Location;
import android.util.Log;

/**
 * This class is responsible for the creation and implementation
 * of the database stored on the phone.
 * 
 * The database will store the walking tour objects {@link Route} and the 
 * waypoint objects {@link Waypoint} associated with them.
 * 
 * @author 	owd2
 * @since 	0.4
 * @version	1.0 2014-01-31 9ed077e148cf94320295b5f4553fb380cb6c0c43
 *
 */
class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "routeManager";

    private static final String TABLE_ROUTE = "walks";
    private static final String TABLE_WAYPOINT = "location";
    private static final String TABLE_DESC = "description";
    private static final String TABLE_PHOTO = "photo";
    private static final String KEY_ID = "id";


    private static final String KEY_DISTANCE = "distance";
    private static final String KEY_HOURS = "hours";
    //Table and common names for routes
    private static final String KEY_TITLE = "title";
    private static final String KEY_SHORT = "shortDesc";
    private static final String KEY_LONG = "longDesc";

    private static final String KEY_LNG = "longitude";
    private static final String KEY_LAT = "latitude";

    //Table and column names for waypoints
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_IMAGE = "photoName";

    private static final String KEY_WALK_ID = "walkID";
    private static final String KEY_LOCATION_ID = "locationID";

    
    /**
     * Constructor for initializing the database
     *
     * @param context
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Implements table for routes
     */
    private static final String CREATE_ROUTE_TABLE = "CREATE TABLE " + TABLE_ROUTE + "("
            +KEY_ID+ " INTEGER PRIMARY KEY, " + KEY_TITLE + " TEXT, "
            +KEY_SHORT+ " TEXT, " +KEY_LONG+ " TEXT, " + KEY_DISTANCE + " FLOAT, " + KEY_HOURS + " FLOAT" +  ")";

    /**
     * Implements table for waypoints
     */
    private static final String CREATE_WAYPOINT_TABLE = "CREATE TABLE " +TABLE_WAYPOINT+ "("
            +KEY_ID+ " INTEGER PRIMARY KEY, " + KEY_WALK_ID + " INTEGER, " + KEY_LAT + " FLOAT, " + KEY_LNG + " FLOAT, "+KEY_TIMESTAMP+ " FLOAT "+
            " TEXT" + ")";

    /**
     * Implements table for descriptions
     */
    private static final String CREATE_DESCRIPTION_TABLE = "CREATE TABLE " + TABLE_DESC + "(" +
            KEY_ID+" INTEGER PRIMARY KEY, " + KEY_LOCATION_ID + " INTEGER, " + KEY_DESCRIPTION + " TEXT)";

    private static final String CREATE_PHOTO_TABLE = "CREATE TABLE " + TABLE_PHOTO + "(" +
            KEY_ID+" INTEGER PRIMARY KEY, " + KEY_LOCATION_ID + " INTEGER, " + KEY_IMAGE + " TEXT)";

    /**
     * Called when the database connection is being configured, to enable
     * features such as write-ahead logging or foreign key support.
     * Creates tables for route and waypoints.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ROUTE_TABLE);
        db.execSQL(CREATE_WAYPOINT_TABLE);
        db.execSQL(CREATE_DESCRIPTION_TABLE);
        db.execSQL(CREATE_PHOTO_TABLE);
    }

    /**
     * Called when the database needs to be upgraded.
     * Checks versions of tables and on upgrade drop older tables and the creates new tables
     */
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WAYPOINT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO);
        onCreate(db);

    }



    /** 
     * Adds the content from the route variables to the relevant column in the database
     * Database then inserts a new row and closes the database connection
     *
     * @param 	route is the specified walking tour that needs it's contents to be stored
     * 			in the database
     * @return 	the id of the route
     */
    public long addRoute(Route route){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, route.getTitle());
        values.put(KEY_SHORT, route.getShortDescription());
        values.put(KEY_LONG, route.getLongDescription());
        values.put(KEY_DISTANCE, route.getDistance());
        long id = db.insert(TABLE_ROUTE, null, values);

        db.close();
        return id;

    }


    /**
     * Returns the a walking tour from the database, by using it's ID
     * in the database
     * 
     * @param id of the route
     * @return the Route object
     */
     public Route getRoute(int id) {
         SQLiteDatabase db = this.getWritableDatabase();

         String query = "SELECT * FROM " + TABLE_ROUTE + " WHERE " + KEY_ID + " = " + String.valueOf(id) + " LIMIT 1";
         Cursor res = db.rawQuery(query, null);

         Route route = null;

         if(res.getCount() > 0) {
             String desc = res.getString(res.getColumnIndex(KEY_SHORT));
             String title = res.getString(res.getColumnIndex(KEY_NAME));
             String longDesc = res.getString(res.getColumnIndex(KEY_LONG));

             route = new Route(title,desc,longDesc);
         }

         return route;
     }
     
   
     /**
      * Removes a route from the database.
      * It matches the ID from the database and the ID from the route and
      * then deletes that row.
      * Connection to the database is then closed.
      * 
      * @param route the specified route to be removed from the database
      */
    public void removeRoute(Route route){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ROUTE, KEY_ID + " = ?",
                new String[] { String.valueOf(route.getId())});
        db.close();

    }

    
    /**
     * Updates a route entry in the database
     * Matches ID from the database to the ID from the route then updates the row with the new values
     * 
     * @param route the specified route to be updated
     * @return
     */
    public int updateRoute(Route route){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, route.getTitle());
        values.put(KEY_SHORT,route.getShortDescription());
        values.put(KEY_LONG, route.getLongDescription());
        values.put(KEY_DISTANCE,route.getDistance());
        return db.update(TABLE_ROUTE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(route.getId())});
    }

 
    /**
     * Adds the content from the waypoint variables to the relevant column in the database.
     * Database then inserts a new row and closes the database connection
     * 
     * @param waypoint
     * @return
     */
    public long addWaypoint(Waypoint waypoint){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LAT,waypoint.getLat());
        values.put(KEY_LNG,waypoint.getLng());
        values.put(KEY_WALK_ID,waypoint.getWalkID());
        values.put(KEY_TIMESTAMP, waypoint.getTimestamp());


        long id = db.insert(TABLE_WAYPOINT, null, values);
        db.close();

        this.insertImages(id, waypoint.getPhotos());
        this.addDescription(id,waypoint.getDescription());

        return id;

    }


    /**
     * Returns all the waypoints of a specified route in the database.
     * 
     * @param walkID is the ID of the route
     * @return an array that of all the waypoints in a route
     */
    public Waypoint[] getAllWaypoints(long walkID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_WAYPOINT + " WHERE " + KEY_WALK_ID + "=" + String.valueOf(walkID);
        Cursor res = db.rawQuery(query, null);
        Waypoint[] data = null;
        int i = 0;
        if(res != null) {
            int count = res.getCount();

            data = new Waypoint[res.getCount()];
            if(res.moveToFirst()) {
                 for(i = 0; i < res.getCount();i++) {


                        String[] img = this.getImages(res.getInt(res.getColumnIndex(KEY_ID)));


                        String desc = this.getDescription(res.getInt(res.getColumnIndex(KEY_ID)));
                        int id = res.getInt(res.getColumnIndex(KEY_ID));

                        data[i] = new Waypoint(id,walkID);
                        data[i].setDescription(desc);
                        data[i].setPhoto(img);
                        data[i].setLat(res.getFloat(res.getColumnIndex(KEY_LAT)));
                        data[i].setLng(res.getFloat(res.getColumnIndex(KEY_LNG)));
                        res.moveToNext();
                }
            }
        }
        return data;
    }
  
    
    /**
     * Removes a waypoint from the database.
     * Matches the ID from the database and the ID from the route and then deletes that row.
     * Connection to the database is the closed.
     * 
     * @param waypoint is the waypoint to be removed from the database.
     */
    public void removeWaypoint(Waypoint waypoint){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WAYPOINT, KEY_ID + " = ?",
                new String[] { String.valueOf(waypoint.getId())});
        db.close();
    }

   
    /**
     * Updates a waypoint entry in the database.
     * Matches ID from the database to the ID from the route
     * then updates the row with the new values.
     * 
     * @param waypoint is the waypoint to be updated
     * @return
     */
    public int updateWaypoint(Waypoint waypoint){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_LNG,String.valueOf(waypoint.getLng()));
        values.put(KEY_LAT,String.valueOf(waypoint.getLat()));
        values.put(KEY_TIMESTAMP, String.valueOf(waypoint.getTimestamp()));


        return db.update(TABLE_WAYPOINT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(waypoint.getId())});
    }


    /**
     * Returns the description of a walking tour. By using it's ID to
     * look for it in the database.
     * 
     * @param LocationID is the ID of the Route
     * @return the description as a String
     */
    public String getDescription(int LocationID) {
        String desc = "";
        SQLiteDatabase db  = this.getWritableDatabase();
        String query = "SELECT " + KEY_DESCRIPTION + " FROM " + TABLE_DESC + " WHERE " + KEY_LOCATION_ID + " = " + String.valueOf(LocationID);

        Cursor result = db.rawQuery(query,null);

        if(result.getCount() > 0 && result != null && result.moveToFirst()) {

            desc = result.getString(result.getColumnIndex(KEY_DESCRIPTION));
        }

        result.close();
        db.close();
        return desc;
    }

    /**
     * Sets the description of a specified walking tour, it is selected by searching
     * the database for it's ID.
     * 
     * @param locationID is the ID of the Route
     * @param description is the description to be set
     */
    public void addDescription(long locationID, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOCATION_ID,String.valueOf(locationID));
        values.put(KEY_DESCRIPTION,description);

        db.insert(TABLE_DESC,KEY_DESCRIPTION,values);
    }

    /**
     * Returns the image of a waypoint from the database.
     * 
     * @param LocationID is the ID of the Route
     * @return is an image that has been converted to a String
     */
    public String[] getImages(int LocationID) {
        String[] images = null;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT " + KEY_IMAGE + " FROM " + TABLE_PHOTO + " WHERE " + KEY_LOCATION_ID + " = " + String.valueOf(LocationID);
        Cursor result = db.rawQuery(query,null);

        if(result.getCount() > 0 && result != null) {
            images = new String[result.getCount()];
            for(int i = 0; i < result.getCount(); i++) {
                if(result.moveToNext()) {
                    images[i] = result.getString(result.getColumnIndex(KEY_IMAGE));
                }
            }
        }
        result.close();
        db.close();
        return images;
    }

    /**
     * Sets an image of a specified waypoint in the database.
     * 
     * @param LocationID is the ID of the Waypoint
     * @param images is the image in the form of a String to be set
     */
    public void insertImages(long LocationID, String[] images) {
        SQLiteDatabase db = this.getWritableDatabase();

        for(int i = 0; i < images.length; i++) {
            ContentValues values = new ContentValues();

            values.put(KEY_IMAGE,images[i]);
            values.put(KEY_LOCATION_ID,LocationID);

            db.insert(TABLE_PHOTO,KEY_IMAGE,values);
        }
    }

    /**
     * Gets the pointer to the first result of the database. The Cursor
     * can be used to get results
     * relative to it, such as getting the next result.
     * 
     * @return a cursor which points to the first result of the table
     */
    Cursor getroutes() {
            //gets data from routes table
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT "+KEY_TITLE+" as _id, from "+TABLE_ROUTE,new String [] {});

        return cur;
    }

}