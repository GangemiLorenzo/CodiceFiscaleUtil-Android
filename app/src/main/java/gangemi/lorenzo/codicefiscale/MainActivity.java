package gangemi.lorenzo.codicefiscale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import gangemi.lorenzo.codicefiscaleutil.CF_Builder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, CF_Builder.build(),Toast.LENGTH_SHORT).show();
    }
}
