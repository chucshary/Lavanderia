package sharychuc.lavanderia.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

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
    }

    public void savePreferences() {
        try {
            _fields = fields.split(",");
            _values = values.split(",");
            sharedPreferences = rootView.getSharedPreferences(nameShared, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            for (int i = 0; i < _fields.length; i++) {
                editor.putString(_fields[i], _values[i]);
            }
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] loadPreferences() {
        _values = values.split(",");
        _fields = new String[_values.length];
        sharedPreferences = rootView.getSharedPreferences(nameShared, Context.MODE_PRIVATE);
        for (int i = 0; i < _values.length; i++) {
            _fields[i] = sharedPreferences.getString(_values[i], "");
        }
        return _fields;
    }
}
