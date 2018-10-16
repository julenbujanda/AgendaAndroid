package ga.julen.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    private ListView listView;

    SQLiteOpenHelper sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        listView = findViewById(R.id.list_view);
        ArrayList<String> nombres=new ArrayList<>();
        try {
            nombres=leerDB();
        } catch (Exception e) {
            e.printStackTrace();
            nombres.add("No hay ningún contacto.");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, nombres);
        listView.setAdapter(arrayAdapter);
    }

    private ArrayList<String> leerDB() {
        ArrayList<String> nombres = new ArrayList<>();
        sqLite = new SQLiteOpenHelper(this, "agenda", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };
        Cursor cursor = sqLite.getWritableDatabase().rawQuery("SELECT * FROM agenda", null);
        while (cursor.moveToNext()) {
            nombres.add(cursor.getString(1) + " " + cursor.getString(2));
        }
        return nombres;
    }

}
