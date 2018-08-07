package gangemi.lorenzo.codicefiscale;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import gangemi.lorenzo.codicefiscaleutil.CF_Builder;
import gangemi.lorenzo.codicefiscaleutil.PersonalData;

public class MainActivity extends AppCompatActivity {

    TextView CodiceFiscale;
    EditText Cognome, Nome, DataDiNascita;
    AutoCompleteTextView ComuneDiNascita;
    RadioButton Uomo;
    Button BtnProcedi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CF_Builder.init(this);
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        CodiceFiscale = this.findViewById(R.id.textCodiceFiscale);
        Cognome = this.findViewById(R.id.editCognome);
        Nome = this.findViewById(R.id.editNome);
        DataDiNascita = this.findViewById(R.id.editData);
        ComuneDiNascita = this.findViewById(R.id.editAutoCitta);
        Uomo= this.findViewById(R.id.radioUomo);
        BtnProcedi= findViewById(R.id.btnGenera);

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
