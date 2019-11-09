package altp.t3h.com.aktp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class DatabaseManager {
    private SQLiteDatabase database;
    private Context context;

    public DatabaseManager(Context context) {
        this.context = context;
        coppy();
    }

    private void coppy() {
        String path =
                Environment.getDataDirectory() + "/data/"
                        + context.getPackageName() + "/db";
        if (new File(path + "/db").exists()) {
            return;
        }
        try {
            AssetManager assetManager =
                    context.getAssets();
            InputStream in = assetManager.open("Question");
            //lay duong dan external app
            new File(path).mkdir();
            FileOutputStream out = new FileOutputStream(path + "/db");

            //copy
            byte[] b = new byte[1024];
            int le = in.read(b);
            while (le >= 0) {
                out.write(b, 0, le);
                le = in.read(b);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        //mo db

        if (database == null || database.isOpen() == false) {
            String path =
                    Environment.getDataDirectory() + "/data/"
                            + context.getPackageName() + "/db";
            database = SQLiteDatabase.openDatabase(path + "/db", null,
                    SQLiteDatabase.OPEN_READWRITE);
        }

    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
            database = null;
        }
    }

    public void query15Question() {
        open();
        String query = "SELECT * FROM (SELECT * FROM question ORDER BY random()) GROUP BY level";
        Cursor c = database.rawQuery(query, null);
        c.moveToFirst();
        int indexQuestion = c.getColumnIndex("ask");
        int indexLevel = c.getColumnIndex("level");
        int indexA = c.getColumnIndex("caseA");
        int indexB = c.getColumnIndex("caseA");
        int indexC = c.getColumnIndex("rc");
        int indexD = c.getColumnIndex("rd");
//        int indexTrue = c.getColumnIndex("truecase");

        while (!c.isAfterLast()) {
            String question = c.getString(indexQuestion);
            Log.d("DatabaseManager", "question: " + question);
            int level = c.getInt(indexLevel);
            Log.d("DatabaseManager", "level: " + level);
            String caseA = c.getString(indexA);
            Log.d("DatabaseManager", "caseA: " + caseA);
            String caseB = c.getString(indexB);
            Log.d("DatabaseManager", "caseB: " + caseB);
            String caseC = c.getString(indexC);
            Log.d("DatabaseManager", "caseC: " + caseC);
            String caseD = c.getString(indexD);
            Log.d("DatabaseManager", "caseD: " + caseD);
//            int trueCase = c.getInt(indexTrue);
//            Log.d("DatabaseManager", "trueCase: " + trueCase);
            c.moveToNext();
        }
        c.close();
        close();
    }

    public void insertScore(
        String name, String money, int levelPass) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("money", money);
        values.put("level_pass", levelPass);
        open();
        database.beginTransaction();
        long cou = database.insert("hight_score", null, values);
        System.out.println("insertScore: " + cou);
        database.setTransactionSuccessful();
//        database.endTransaction();
        close();
    }

    public void updateScore(String name, String id, int levelPass) {
        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("money", money);
        values.put("level_pass", levelPass);

        open();
        database.beginTransaction();
        database.update("hight_score", values,
                "id = " + id + " and name = '" + name + "'",
                null);
        database.setTransactionSuccessful();
        close();
    }

    public Question getQuestion(int level) {
        open();
        Cursor c =
                database.rawQuery("SELECT * FROM question WHERE level = " + level +
                                " ORDER BY RANDOM() LIMIT 1",
                        null);
        c.moveToFirst();
        int indexQuestion = c.getColumnIndex("question");
        int indexCA = c.getColumnIndex("casea");
        int indexCB = c.getColumnIndex("caseb");
        int indexCC = c.getColumnIndex("casec");
        int indexCD = c.getColumnIndex("cased");
        int indexTrue = c.getColumnIndex("truecase");

        String question = c.getString(indexQuestion);
        String ca = c.getString(indexCA);
        String cb = c.getString(indexCB);
        String cc = c.getString(indexCC);
        String cd = c.getString(indexCD);
        int trueCase = c.getInt(indexTrue);
//        Random rd = new Random();
//        int trueCase = rd.nextInt(4) + 1;
//        switch (trueCase) {
//            case 1:
//                break;
//            case 2:
//                String cTem = cb;
//                cb = ca;
//                ca = cTem;
//                break;
//            case 3:
//                cTem = cc;
//                cc = ca;
//                ca = cTem;
//                break;
//            default:
//                cTem = cd;
//                cd = ca;
//                ca = cTem;
//                break;
//        }

        close();
        Question q = new Question();
        q.setQuestion(question);
        q.setcA(ca);
        q.setcB(cb);
        q.setcC(cc);
        q.setcD(cd);
        q.setLevel(level);
        q.setTrueCase(trueCase);
        return q;
    }


}
