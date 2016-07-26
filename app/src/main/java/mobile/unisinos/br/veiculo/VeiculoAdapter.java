package mobile.unisinos.br.veiculo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mobile.unisinos.br.veiculo.pojo.Veiculo;

/**
 * Created by alan on 25/07/2016.
 */
public class VeiculoAdapter extends ArrayAdapter<Veiculo> {

    private final Context context;
    private final Veiculo[] values;

    public VeiculoAdapter(Context context, Veiculo[] objects) {
        super(context, R.layout.lista_row_layout, objects);
        this.context = context;
        this.values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.lista_row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.iconEdit);
        Veiculo v = values[position];
        textView.setText(v.getModelo() +" - "+ v.getPlaca());
        if ("Audi".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.audi);
        } else if ("BMW".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.bmw);
        } else if ("Chevrolet(GM)".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.chevrolet);
        } else if ("Fiat".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.fiat);
        } else if ("Ford".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.ford);
        } else if ("Honda".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.honda);
        } else if ("Hyundai".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.hyundai);
        } else if ("Kia".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.kia);
        } else if ("Mercedes-Benz".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.mercedes);
        } else if ("Nissan".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.nissan);
        } else if ("Peugeot".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.peugeot);
        } else if ("Renault".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.renault);
        } else if ("Toyota".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.toyota);
        } else if ("Volkswagen".equals(v.getMarca())) {
            imageView.setImageResource(R.drawable.volks);
        }

        if (position % 2 == 1) {
            rowView.setBackgroundColor(Color.WHITE);
        } else {
//            rowView.setBackgroundColor(Color.parseColor("#D3D3D3"));
            rowView.setBackgroundColor(Color.parseColor("#E0FFFF"));
        }

        return rowView;
    }


}
