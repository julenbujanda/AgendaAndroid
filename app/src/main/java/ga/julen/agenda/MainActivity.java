package ga.julen.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAnadir;
    private Button btnVerContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAnadir = findViewById(R.id.btn_anadir_contacto);
        btnVerContactos = findViewById(R.id.btn_ver_contactos);
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNameActivity.class);
                startActivity(intent);
            }
        });
        btnVerContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContactsActivity.class);
                startActivity(intent);
            }
        });
    }
}
