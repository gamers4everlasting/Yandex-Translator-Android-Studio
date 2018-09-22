package com.example.olenburg.texttranslator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olenburg.texttranslator.models.Translate;
import com.example.olenburg.texttranslator.network.ApiServices;
import com.example.olenburg.texttranslator.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Button btn;
    private EditText input;
    private TextView output;
    private final String key = "trnsl.1.1.20180920T072840Z.abe86953ae0357d5.48197fb65ff2fb2875ae0412d47561d23479db34";
    private String lang;
    Spinner s1, s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        input = findViewById(R.id.input_text);

        output = findViewById(R.id.output);

        s1 = findViewById(R.id.lang_from);
        s2 = findViewById(R.id.lang_to);



        s1.setOnItemSelectedListener(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lang1 = s1.getSelectedItem().toString();
                String lang2 = s2.getSelectedItem().toString();
                lang = String.format("%s-%s", lang1, lang2);
                String inputText = input.getText().toString();


                ApiServices apiServices = ApiClient.getClient().create(ApiServices.class);
                Call<Translate> call = apiServices.getAllPosts(key,  inputText, lang);
                call.enqueue(new Callback<Translate>() {
                    @Override
                    public void onResponse(Call<Translate> call, Response<Translate> response) {


                        List<String> list = response.body().getData();
                        output.setText(list.get(0).toString());

                    }

                    @Override
                    public void onFailure(Call<Translate> call, Throwable t) {
                        Log.e("@Error",  t.getMessage());
                    }
                });

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lang, android.R.layout.simple_spinner_item);
        s2.setAdapter(adapter);
        String language = "en-" + s1.getSelectedItem().toString();


        ApiServices apiServices = ApiClient.getClient().create(ApiServices.class);
        Call<Translate> call = apiServices.getAllPosts(key,  "Translate", language);
        call.enqueue(new Callback<Translate>() {
            @Override
            public void onResponse(Call<Translate> call, Response<Translate> response) {


                List<String> list = response.body().getData();
                btn.setText(list.get(0).toString());

            }

            @Override
            public void onFailure(Call<Translate> call, Throwable t) {
                Log.e("@Error",  t.getMessage());
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Choose Language :", Toast.LENGTH_SHORT).show();
    }
}
