package mobile.unisinos.br.veiculo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alan on 24/07/2016.
 */
public class DBCore extends SQLiteOpenHelper {

    private static final String NOME_BD = "bdveiculo";
    private static final int VERSAO_BD = 2;

    public DBCore(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //cria a tabela
        sqLiteDatabase.execSQL("create table veiculo (_id integer primary key autoincrement, marca text not null, modelo text not null, placa text not null, ano int not null);");
        populaBaseFake(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // caso mude a versâo, recria o db, o correto seria neste caso ter scrits de migração ou apenas readequar a base atual
        sqLiteDatabase.execSQL("drop table veiculo;");
        onCreate(sqLiteDatabase);
    }

    /**
     * Criado apenas para facilitar a avaliação do aplicativo.
     * @param sqLiteDatabase
     */
    private void populaBaseFake(SQLiteDatabase sqLiteDatabase) {
        String baseInsert = "insert into veiculo (marca, modelo, placa, ano) values (?, ?, ?, ?);";

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Audi","A1","IPI2001","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Audi","A3","IPI2002","2012"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Audi","A3","IPI2005","2014"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Audi","A4","IPI2003","2014"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Audi","Q3","IPI2004","2011"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"BMW","X1","IBM2002","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"BMW","X5","IBM2003","2007"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"BMW","X5","IBM2005","2008"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"BMW","X6","IBM2004","2013"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Chevrolet(GM)","Agile","IGM2001","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Chevrolet(GM)","Captiva","IGM2002","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Chevrolet(GM)","Celta","IGM2003","2003"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Chevrolet(GM)","Classic","IGM2004","2009"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Fiat","500","IFI2001","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Fiat","Doblo","IFI2002","2014"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Fiat","Palio","IFI2003","2012"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Fiat","Palio Adventure","IFI2004","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Fiat","Punto","IFI2005","2011"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Fiat","Uno","IFI2006","1999"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Ford","EcoSport","IFO2001","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Ford","Edge","IFO2002","2011"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Ford","Fusion","IFO2003","2014"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Ford","Ka","IFO2004","2003"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Honda","Civic","IHO2001","2012"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Hyundai","HB20","IHU2001","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Hyundai","i30","IHU2002","2013"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Kia","Soul","IKI2001","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Kia","Sportage","IKI2002","2013"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Mercedes-Benz","CLA","IME2001","2014"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Nissan","March","INI2001","2014"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Nissan","Sentra","INI2002","2014"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Peugeot","307","IPE2002","2004"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Renault","Clio","IRE2001","2004"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Renault","Duster","IRE2002","2013"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Toyota","Hilux","ITO2001","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Toyota","Corolla","ITO2002","2013"});

        sqLiteDatabase.execSQL(baseInsert, new String[] {"Volkswagen","Fox","IVO2001","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Volkswagen","Fusca","IVO2002","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Volkswagen","Gol","IVO2003","2008"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Volkswagen","Gol","IVO2004","2013"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Volkswagen","Golf","IVO2005","2014"});
        sqLiteDatabase.execSQL(baseInsert, new String[] {"Volkswagen","Jetta","IVO2006","2012"});
    }
}
