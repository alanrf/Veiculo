package mobile.unisinos.br.veiculo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.List;

import mobile.unisinos.br.veiculo.db.DBVeiculo;
import mobile.unisinos.br.veiculo.pojo.Veiculo;

public class ListaActivity extends AppCompatActivity {

    private DBVeiculo dbVeiculo;
    private Veiculo[] arrayVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        dbVeiculo = new DBVeiculo(this);
        defineComportamentoNovo();
        defineComportamentoLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }

    /**
     * Atualiza a lista apresentada no listView
     */
    private void atualizaLista() {
        List<Veiculo> list = dbVeiculo.retornarVeiculos();
        arrayVeiculo = list.toArray(new Veiculo[list.size()]);
        VeiculoAdapter adapter = new VeiculoAdapter(this, arrayVeiculo);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

    }

    /**
     * Faz o bind do botão "NOVO" para ir para tela de cadastro.
     */
    private void defineComportamentoNovo() {
        Button bt = (Button) findViewById(R.id.btNovo);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent it = new Intent(ListaActivity.this, CadastroActivity.class);
                    it.putExtra("novo", true);
                    startActivity(it);
                }

        });
    }

    /**
     * Define o comportamento para quando seleciona um item da lista
     */
    private void defineComportamentoLista() {
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Veiculo v = arrayVeiculo[i];

                        Intent it = new Intent(ListaActivity.this, CadastroActivity.class);
                        it.putExtra("novo", false);
                        it.putExtra("_idVeiculo", v.getId());
                        it.putExtra("marca", v.getMarca());
                        it.putExtra("modelo", v.getModelo());
                        it.putExtra("placa", v.getPlaca());
                        it.putExtra("ano", v.getAno());

                        startActivity(it);
                    }
                }
        );
    }
}
