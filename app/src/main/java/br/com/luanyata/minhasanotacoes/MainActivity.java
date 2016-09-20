package br.com.luanyata.minhasanotacoes;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText anotacao;
    private ImageView btnSalvar;
    private static final String ARQUIVOSALVAR = "Anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anotacao = (EditText) findViewById(R.id.idAnotacao);

        btnSalvar = (ImageView) findViewById(R.id.idBtnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = anotacao.getText().toString();
                gravarNoArquivo(texto);
                Toast.makeText(getApplicationContext(), " Arquivo Salvo", Toast.LENGTH_SHORT).show();
            }
        });

        String dadosArquivo = lerArquivo();
        if (dadosArquivo != null) {
            anotacao.setText(dadosArquivo);
        }
    }

    private void gravarNoArquivo(String texto) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(ARQUIVOSALVAR, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }
    }

    private String lerArquivo() {
        String resultado = "";

        try {
            InputStream arquivo = openFileInput(ARQUIVOSALVAR);
            if (arquivo != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String linha = "";
                while ((linha = bufferedReader.readLine()) != null) {

                    resultado += linha;
                }
                arquivo.close();
            }
        } catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }
        return resultado;
    }
}
