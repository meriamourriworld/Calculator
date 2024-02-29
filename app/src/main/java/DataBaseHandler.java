import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String DB_NAME = "gestionUtilisateurs.db";
    private static final String TABLE = "client";
    private final String COLUMN1 = "nom", COLUMN2 = "email", COLUMN3 = "motPasse";
    public DataBaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "(idClient INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT," +
                "email TEXT," +
                "motPasse " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public Client getClient(String email, String mp)
    {
        Client client=null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE,new String[] {COLUMN1, COLUMN2, COLUMN3},"email=?",new String[] {email},null,null,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
            client = new Client(cursor.getString(0),cursor.getString(1), cursor.getString(2));
        }
        return client;
    }

    public ArrayList<Client> getClients()
    {
        Client client;
        ArrayList<Client> listeClients = new ArrayList<Client>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT "+ COLUMN1 + ", " + COLUMN2 + ", " + COLUMN3 + " FROM " + TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do {
                client = new Client(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                listeClients.add(client);
                }while(cursor.moveToNext());
            }
        return listeClients;
    }

    public void ajouterClient(Client client)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN1, client.getNom());
        values.put(COLUMN2, client.getEmail());
        values.put(COLUMN3, client.getMotPasse());

        db.insert(TABLE,null, values);
        db.close();
    }
}
