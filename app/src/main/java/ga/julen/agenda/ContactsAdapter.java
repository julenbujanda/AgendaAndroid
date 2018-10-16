package ga.julen.agenda;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsAdapter implements ListAdapter {

    private ArrayList<Contacto> contactos;
    private Context contexto;

    public ContactsAdapter(ArrayList<Contacto> contactos, Context contexto) {
        this.contactos = contactos;
        this.contexto = contexto;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Contacto getItem(int posicion) {
        return contactos.get(posicion);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int posicion, View view1, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View view = inflater.inflate(R.layout.contacto, viewGroup, false);
        TextView txtNombre = view.findViewById(R.id.lista_nombre);
        TextView txtApellido = view.findViewById(R.id.lista_apellido);
        txtNombre.setText(contactos.get(posicion).getNombre());
        txtApellido.setText(contactos.get(posicion).getApellido());
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return contactos.size();
    }

    @Override
    public int getViewTypeCount() {
        return contactos.size();
    }

    @Override
    public boolean isEmpty() {
        return contactos.isEmpty();
    }
}
