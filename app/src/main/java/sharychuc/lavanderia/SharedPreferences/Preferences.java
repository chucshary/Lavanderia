package sharychuc.lavanderia.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Shary on 27/01/2016.
 */
public class Preferences {
    private Context rootView;
    private String fields;
    private String values;
    private String[] _fields;
    private String[] _values;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String nameShared;

    public Preferences(Context rootView, String fields, String values, String nameShared) {
        this.rootView = rootView;
        this.fields = fields;
        this.values = values;
        this.nameShared = nameShared;
        Log.d("SHARED ", fields + " " + values);
    }

    public void savePreferences() {
        try {
            _fields = fields.split(",");
            _values = values.split(",");
            sharedPreferences = rootView.getSharedPreferences(nameShared, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            for (int i = 0; i < _fields.length; i++) {
                editor.putString(_fields[i].trim(), _values[i].trim());
            }
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] loadPreferences() {
        _fields = fields.split(",");
        _values = new String[_fields.length];
        sharedPreferences = rootView.getSharedPreferences(nameShared, Context.MODE_PRIVATE);
        for (int i = 0; i < _fields.length; i++) {
            _values[i] = sharedPreferences.getString(_fields[i].trim(), "");
        }
        return _values;
    }

    public void cleanPreferences(String name) {
        sharedPreferences = rootView.getSharedPreferences(name, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }
}
