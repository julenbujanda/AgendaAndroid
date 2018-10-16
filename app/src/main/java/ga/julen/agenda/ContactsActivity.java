package ga.julen.agenda;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    private ListView listView;

    SQLiteOpenHelper sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        listView = findViewById(R.id.list_view);
        ArrayList<Contacto> contactos = new ArrayList<>();
        comprobarPermisoLlamada();
        try {
            contactos = leerDB();
        } catch (Exception e) {
            e.printStackTrace();
            contactos.add(new Contacto(0, "No hay ningún contacto", "", "", ""));
        }
        final ContactsAdapter contactsAdapter = new ContactsAdapter(contactos, getApplicationContext());
        listView.setAdapter(contactsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                mostrarPopUp(view, contactsAdapter.getItem(posicion));
            }
        });
    }

    private ArrayList<Contacto> leerDB() throws SQLException {
        ArrayList<Contacto> contactos = new ArrayList<>();
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
            contactos.add(new Contacto(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4)));
        }
        return contactos;
    }

    public void mostrarPopUp(View view, final Contacto contacto) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);


        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);

        TextView txtPopUpNombre = popupView.findViewById(R.id.popup_nombre);
        Button btnPopUpTelefono = popupView.findViewById(R.id.popup_telefono);
        TextView txtPopUpMail = popupView.findViewById(R.id.popup_mail);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        txtPopUpNombre.setText(contacto.getNombre() + " " + contacto.getApellido());
        btnPopUpTelefono.setText(contacto.getNumero());
        txtPopUpMail.setText(contacto.getMail());

        btnPopUpTelefono.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contacto.getNumero()));
                startActivity(callIntent);
            }
        });

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void comprobarPermisoLlamada() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ContactsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }

}
