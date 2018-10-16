package ga.julen.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNameActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtApellidos;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);
        txtNombre = findViewById(R.id.txt_nombre);
        txtApellidos = findViewById(R.id.txt_apellidos);
        btnSiguiente = findViewById(R.id.btn_siguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continuar();
            }
        });
    }

    private void continuar() {
        Intent intent = new Intent(this, AddInformationActivity.class);
        intent.putExtra("nombre", txtNombre.getText().toString());
        intent.putExtra("apellidos", txtApellidos.getText().toString());
        startActivity(intent);
    }

}
