package ga.julen.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    private ListView listView;

    SQLiteOpenHelper sqLite;

    ArrayList<Contacto> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        listView = findViewById(R.id.list_view);
        contactos = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        try {
            nombres = leerDB();
        } catch (Exception e) {
            e.printStackTrace();
            nombres.add("No hay ningún contacto.");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, nombres);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                mostrarPopUp(view, contactos.get(posicion));
            }
        });
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
            contactos.add(new Contacto(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4)));
        }
        return nombres;
    }

    public void mostrarPopUp(View view, Contacto contacto) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);


        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);

        TextView txtPopUpNombre = popupView.findViewById(R.id.popup_nombre);
        TextView txtPopUpTelefono = popupView.findViewById(R.id.popup_telefono);
        TextView txtPopUpMail = popupView.findViewById(R.id.popup_mail);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        txtPopUpNombre.setText(contacto.getNombre() + " " + contacto.getApellido());
        txtPopUpTelefono.setText(contacto.getNumero());
        txtPopUpMail.setText(contacto.getMail());

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}
