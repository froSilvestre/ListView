package arq.ifsp.dmo.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int REQUISICAO_ESTADO = 1;
    private static final String ESTADO_SELECIONADO = "Estado";

    private String estado;
    private Button btnSelecionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelecionar = findViewById(R.id.btn_selecionar);
        btnSelecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TelaEstadosActivity.class);
                intent.putExtra(TelaEstadosActivity.EXTRA_ESTADO, estado);

                startActivityForResult(intent, REQUISICAO_ESTADO);

            }
        });

        if(savedInstanceState != null){
            estado = savedInstanceState.getString(ESTADO_SELECIONADO);
            btnSelecionar.setText(estado);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUISICAO_ESTADO){
            estado = data.getStringExtra(TelaEstadosActivity.EXTRA_RESULTADO);

            if(estado != null){
                btnSelecionar.setText(estado);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ESTADO_SELECIONADO, estado);
    }
}