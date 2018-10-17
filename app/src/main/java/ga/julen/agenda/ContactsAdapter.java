package ga.julen.agenda;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsAdapter extends BaseAdapter implements Filterable {

    private ArrayList<Contacto> contactos;
    private ArrayList<Contacto> contactosMostrados;
    private Context contexto;

    public ContactsAdapter(ArrayList<Contacto> contactos, Context contexto) {
        super();
        //this.contactos = contactos;
        this.contactosMostrados = contactos;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return contactosMostrados.size();
    }

    @Override
    public Contacto getItem(int posicion) {
        return contactosMostrados.get(posicion);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int posicion, View view1, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View view = inflater.inflate(R.layout.contacto, viewGroup, false);
        TextView txtNombre = view.findViewById(R.id.lista_nombre);
        TextView txtApellido = view.findViewById(R.id.lista_apellido);
        txtNombre.setText(contactosMostrados.get(posicion).getNombre());
        txtApellido.setText(contactosMostrados.get(posicion).getApellido());
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return contactosMostrados.size();
    }

    @Override
    public int getViewTypeCount() {
        return contactosMostrados.size();
    }

    @Override
    public boolean isEmpty() {
        return contactosMostrados.isEmpty();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Contacto> contactosFiltrados = new ArrayList<>();

                if (contactos == null)
                    contactos = contactosMostrados;
                if (constraint != null) {
                    for (Contacto contacto : contactos) {
                        if (contacto.getNombre().toLowerCase().contains(constraint.toString().toLowerCase()))
                            contactosFiltrados.add(contacto);
                    }
                    results.count = contactosFiltrados.size();
                    results.values = contactosFiltrados;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactosMostrados = (ArrayList<Contacto>) filterResults.values;
                notifyDataSetChanged();
            }
        };


    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}
