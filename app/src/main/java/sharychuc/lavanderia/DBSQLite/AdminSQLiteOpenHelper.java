package sharychuc.lavanderia.DBSQLite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sharychuc.lavanderia.DBSQLite.Tables;

/**
 * Created by Shary on 26/01/2016.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    Tables tables;
    ArrayList<String[]> listOfTables;
    String dbTable;

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        tables = new Tables();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        listOfTables = new ArrayList<String[]>();
        listOfTables.add(tables.getLaundry());
        listOfTables.add(tables.getAccount());
        listOfTables.add(tables.getUser());

        for (int i = 0; i < listOfTables.size(); i++) {
            dbTable = "";
            for (int j = 0; j < listOfTables.get(i).length; j++) {
                if (j == 0) {
                    dbTable = "CREATE TABLE " + listOfTables.get(i)[j] + "(";
                }
                if (j == 1) {
                    dbTable = dbTable + listOfTables.get(i)[j] + " INTEGER PRIMARY KEY,";
                }
                if (j < listOfTables.get(i).length - 1) {
                    dbTable = dbTable + listOfTables.get(i)[j] + " TEXT,";
                } else {
                    dbTable = dbTable + listOfTables.get(i)[j] + " INTEGER" + ")";
                }
            }
            db.execSQL(dbTable);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
