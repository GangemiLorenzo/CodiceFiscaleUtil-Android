package gangemi.lorenzo.codicefiscale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import gangemi.lorenzo.codicefiscaleutil.CF_Builder;
import gangemi.lorenzo.codicefiscaleutil.PersonalData;

public class MainActivity extends AppCompatActivity {

    TextView CodiceFiscale;
    EditText Cognome, Nome, DataDiNascita;
    AutoCompleteTextView ComuneDiNascita;
    RadioButton Uomo;
    Button BtnProcedi;
    CardView CardCodice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CF_Builder.init(this);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_github:
                String url = "https://github.com/GangemiLorenzo/CodiceFiscaleUtil-Android";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        Cognome = this.findViewById(R.id.editCognome);
        Nome = this.findViewById(R.id.editNome);
        DataDiNascita = this.findViewById(R.id.editData);
        ComuneDiNascita = this.findViewById(R.id.editAutoCitta);
        Uomo= this.findViewById(R.id.radioUomo);
        BtnProcedi= findViewById(R.id.btnGenera);
        CardCodice = findViewById(R.id.CardCodice);
        CardCodice.setVisibility(View.GONE);

        CodiceFiscale = (TextView) findViewById(R.id.CodiceFiscale);

        loadCityList();

        BtnProcedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date[] = DataDiNascita.getText().toString().split("/");
                PersonalData p = new PersonalData(Nome.getText().toString(),
                        Cognome.getText().toString(),
                        date[0],
                        date[1],
                        date[2],
                        Uomo.isChecked(),
                        ComuneDiNascita.getText().toString()
                );
                CardCodice.setVisibility(View.VISIBLE);
                CodiceFiscale.setText(CF_Builder.build(p));
            }
        });
    }

    private void loadCityList() {
        List<String> citylist = CF_Builder.getCityList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, citylist);
        ComuneDiNascita.setThreshold(1);
        ComuneDiNascita.setAdapter(adapter);
    }

}
