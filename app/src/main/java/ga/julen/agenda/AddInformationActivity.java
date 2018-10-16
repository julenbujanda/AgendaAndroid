package ga.julen.agenda;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddInformationActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtTelefono;
    private EditText txtMail;
    private Button btnGuardar;

    SQLiteOpenHelper sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information);
        txtNombre = findViewById(R.id.txt_nombre_i);
        txtApellidos = findViewById(R.id.txt_apellidos_i);
        txtTelefono = findViewById(R.id.txt_telefono);
        txtMail = findViewById(R.id.txt_mail);
        btnGuardar = findViewById(R.id.btn_guardar);
        recuperarDatos();
        crearDB();
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
    }

    private void recuperarDatos(){
        Intent intent=getIntent();
        txtNombre.setText(intent.getStringExtra("nombre"));
        txtApellidos.setText(intent.getStringExtra("apellidos"));
    }

    private void crearDB() {
        sqLite = new SQLiteOpenHelper(this, "agenda", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS agenda (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombre VARCHAR(15), " +
                        "apellidos VARCHAR(30), " +
                        "telefono VARCHAR(20), " +
                        "mail VARCHAR(30));");
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };
    }

    private void guardar() {
        SQLiteDatabase db = sqLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", txtNombre.getText().toString());
        contentValues.put("apellidos", txtApellidos.getText().toString());
        contentValues.put("telefono", txtTelefono.getText().toString());
        contentValues.put("mail", txtMail.getText().toString());
        db.insert("agenda", null, contentValues);
    }

}
