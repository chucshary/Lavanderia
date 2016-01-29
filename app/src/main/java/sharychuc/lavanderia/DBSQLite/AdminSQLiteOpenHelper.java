package sharychuc.lavanderia.DBSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shary on 26/01/2016.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    Tables tables;
    List<String[]> listOfTables;
    String dbTable;

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tables = new Tables();
        listOfTables = new ArrayList<String[]>();
        listOfTables.add(tables.getLaundry());
        listOfTables.add(tables.getAccount());
        listOfTables.add(tables.getUser());

        for (int i = 0; i < listOfTables.size(); i++) {
            dbTable = "";
            for (int j = 0; j < listOfTables.get(i).length; j++) {
                if (j == 0) {
                    dbTable = "CREATE TABLE " + listOfTables.get(i)[j] + "(";
                } else if (j == 1) {
                    dbTable = dbTable + listOfTables.get(i)[j] + " TEXT PRIMARY KEY,";
                } else if (j < listOfTables.get(i).length - 1) {
                    dbTable = dbTable + listOfTables.get(i)[j] + " TEXT,";
                } else {
                    dbTable = dbTable + listOfTables.get(i)[j] + " TEXT" + ")";
                }
            }
            Log.d("QUERY ", dbTable);
            db.execSQL(dbTable);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
