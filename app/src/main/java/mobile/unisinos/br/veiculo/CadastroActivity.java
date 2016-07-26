package mobile.unisinos.br.veiculo;

import android.content.res.TypedArray;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import mobile.unisinos.br.veiculo.db.DBVeiculo;
import mobile.unisinos.br.veiculo.pojo.Veiculo;

public class CadastroActivity extends AppCompatActivity {

    private Spinner edMarca;
    private Spinner edModelo;
    private EditText edPlaca;
    private EditText edAno;
    private int _idVeiculo;
    private String strModelo = "";

    private DBVeiculo dbVeiculo;

    private boolean bNnovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dbVeiculo = new DBVeiculo(this);

        bindCampos();
        obterDadosActivityLista();
        bindBotoes();
    }

    /**
     * Obtem os dados passados pela activity de listagem para esta.
     */
    private void obterDadosActivityLista() {
        bNnovo = getIntent().getExtras().getBoolean("novo");
        if (!bNnovo) {
            _idVeiculo = getIntent().getExtras().getInt("_idVeiculo");
            String strMarca = getIntent().getExtras().getString("marca");
            strModelo = getIntent().getExtras().getString("modelo");
            String strPlaca = getIntent().getExtras().getString("placa");
            int intAno = getIntent().getExtras().getInt("ano");

            CharSequence[] marcas = getResources().getTextArray(R.array.array_marcas);
            for (int i=0; i< marcas.length; i++) {
                if (strMarca.equals(marcas[i])) {
                    edMarca.setSelection(i);
                    break;
                }
            }

            edPlaca.setText(strPlaca);
            edAno.setText(String.valueOf(intAno));

            selecionaModeloTelaAnterior();
        }
    }

    private void selecionaModeloTelaAnterior() {
        TypedArray arrayModelosMarcas = getResources().obtainTypedArray(
                R.array.array_marcas_carros);
        CharSequence[] modelos = arrayModelosMarcas.getTextArray(edMarca.getSelectedItemPosition());

        for (int i=0; i< modelos.length; i++) {
            if (strModelo.equals(modelos[i])) {
                edModelo.setSelection(i);
                break;
            }
        }
    }

    /**
     * Faz o bind das ações para os botões
     */
    private void bindBotoes() {
        Button btSalvar  = (Button) findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validaCampos()) {
                    gravarVeiculo();
                    finish();
                }
            }
        });

        Button btCancelar = (Button) findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (!bNnovo) {
            Button btDeletar = (Button) findViewById(R.id.btDeletar);
            btDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    excluirVeiculo();
                    exibeMensagem("Veículo excluído com sucesso.");
                    finish();
                }
            });
            btDeletar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Faz o bind dos campos para os atributos internos.
     */
    private void bindCampos() {
        edMarca = (Spinner) findViewById(R.id.edMarca);
        edModelo = (Spinner) findViewById(R.id.edModelo);
        edPlaca = (EditText) findViewById(R.id.edPlaca);
        edAno = (EditText) findViewById(R.id.edAno);

        defineComportamentoMarca();
    }

    /**
     * Define o comportamento do spinner marca, para mudar os modelos de acordo com a marca.
     */
    private void defineComportamentoMarca() {
        edMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TypedArray arrayModelosMarcas = getResources().obtainTypedArray(
                        R.array.array_marcas_carros);
                CharSequence[] modelos = arrayModelosMarcas.getTextArray(i);
                arrayModelosMarcas.recycle();

                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(edModelo.getContext(), android.R.layout.simple_spinner_item, modelos);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                edModelo.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                if (!bNnovo) {
                    selecionaModeloTelaAnterior();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    /**
     * Valida e salva um veículo
     */
    private void gravarVeiculo() {
        Veiculo v = new Veiculo();
        v.setPlaca(edPlaca.getText().toString());
        v.setAno(Integer.valueOf(edAno.getText().toString()));
        v.setMarca(edMarca.getSelectedItem().toString());
        v.setModelo(edModelo.getSelectedItem().toString());

        if (bNnovo) {
            dbVeiculo.inserirVeiculo(v);
            exibeMensagem("Veículo inserido na base.");
        } else {
            v.setId(_idVeiculo);
            dbVeiculo.atualizarVeiculo(v);
            exibeMensagem("Veículo atualizado.");
        }
    }

    /**
     * Exclui um veiculo
     */
    private void excluirVeiculo() {
        Veiculo v = new Veiculo();
        v.setId(_idVeiculo);
        dbVeiculo.deletarVeiculo(v);
    }

    /**
     * Realiza a validaçâo dos campos da tela
     * @return boolean
     */
    private boolean validaCampos() {
        if (campoPreenchido(edPlaca) && campoPreenchido(edAno)) {
            int valor = Integer.valueOf(String.valueOf(edAno.getText()));
            if (valor < 1900) {
                edAno.setError("O ano deve ser superior a 1900");
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida se o campo está vazio
     * @return boolean
     */
    private boolean campoPreenchido(EditText ed) {
        if (ed.getText() == null || "".equals(ed.getText().toString().trim())) {
            ed.setError("Este campo deve estar preenchido com um valor.");
            return false;
        }
        return true;
    }

    /**
     * Exibe uma mensagem no centro da tela
     * @param strMensagem
     */
    protected void exibeMensagem(CharSequence strMensagem) {
        Toast toast = Toast.makeText(getApplicationContext(), strMensagem, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
