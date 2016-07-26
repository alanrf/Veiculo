package mobile.unisinos.br.veiculo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mobile.unisinos.br.veiculo.pojo.Veiculo;

/**
 * Created by alan on 24/07/2016.
 */
public class DBVeiculo {

    private SQLiteDatabase db;

    public DBVeiculo(Context context) {
        DBCore access = new DBCore(context);
        db = access.getWritableDatabase();
    }

    public void inserirVeiculo(Veiculo veiculo) {
        ContentValues values = new ContentValues();
        values.put("marca", veiculo.getMarca());
        values.put("modelo", veiculo.getModelo());
        values.put("placa", veiculo.getPlaca());
        values.put("ano", veiculo.getAno());

        db.insert("veiculo", null, values);
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        ContentValues values = new ContentValues();
        values.put("marca", veiculo.getMarca());
        values.put("modelo", veiculo.getModelo());
        values.put("placa", veiculo.getPlaca());
        values.put("ano", veiculo.getAno());

        db.update("veiculo", values, "_id = ?", new String[]{String.valueOf(veiculo.getId())});
    }

    public void deletarVeiculo(Veiculo veiculo) {
        db.delete("veiculo", "_id = ?", new String[]{String.valueOf(veiculo.getId())});
    }

    public List<Veiculo> retornarVeiculos() {
        String[] columns = {"_id", "marca", "modelo", "placa", "ano"};
        List<Veiculo> list = new ArrayList<Veiculo>();
        Cursor cursor = db.query("veiculo", columns, null, null, null, null, "marca, modelo ASC");

        try {
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(cursor.getInt(0));
                    veiculo.setMarca(cursor.getString(1));
                    veiculo.setModelo(cursor.getString(2));
                    veiculo.setPlaca(cursor.getString(3));
                    veiculo.setAno(cursor.getInt(4));

                    list.add(veiculo);
                }
            }
        } finally {
            cursor.close();
        }

        return list;
    }
}
