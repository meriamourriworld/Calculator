package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.crypto.spec.SecretKeySpec;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String DB_NAME = "gestionUtilisateurs.db";
    private static final String TABLE = "client";
    private final String COLUMN1 = "nom", COLUMN2 = "email", COLUMN3 = "motPasse";
    public DataBaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE + "(idClient INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT," +
                "email TEXT," +
                "motPasse TEXT" +
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
        SecretKeySpec secretKey = getKey(mp);
        Client client=null;
        SQLiteDatabase db = this.getReadableDatabase();
        String pass = null;
        try {
            pass = CryptoUtils.decrypt(mp, secretKey);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Cursor cursor = db.query(TABLE,new String[] {COLUMN1, COLUMN2, COLUMN3},"email=? AND motPasse=?",new String[] {email,pass},null,null,null);
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

    public long ajouterClient(Client client)
    {
        String mp;
        SQLiteDatabase db = this.getWritableDatabase();

       SecretKeySpec secretKey = getKey(client.getMotPasse());
        try {
            mp = CryptoUtils.encrypt(client.getMotPasse(), secretKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }


        ContentValues values = new ContentValues();
        values.put(COLUMN1, client.getNom());
        values.put(COLUMN2, client.getEmail());
        values.put(COLUMN3, mp);

        long res = db.insert(TABLE,null, values);
        db.close();
        return res;
    }

    public SecretKeySpec getKey(String pass)
    {
        char[] password = pass.toCharArray();
        byte[] salt = new byte[16]; // Remplissez le sel avec des données aléatoires
        int iterationCount = 10000; // Nombre d'itérations
        int keyLength = 256; // Longueur de la clé en bits
        SecretKeySpec secretKey = null;
        try {
            secretKey = CryptoUtils.createSecretKey(password, salt, iterationCount, keyLength);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return secretKey;
    }
}
