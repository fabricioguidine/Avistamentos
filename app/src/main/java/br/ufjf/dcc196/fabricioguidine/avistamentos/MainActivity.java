package br.ufjf.dcc196.fabricioguidine.avistamentos;

import androidx.activity.result.ActivityResult;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> launcher;
    RecyclerView recyclerAvistamento;
    List<Avistamento> avistamentos;
    AvistamentoAdapter avistamentoAdapter;
    AvistamentosRepository repo;
    AvistamentoAdapter.OnAvistamentoClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repo = new AvistamentosRepository(getApplicationContext());
        recyclerAvistamento = findViewById(R.id.recyclerAvistamento);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAvistamento.setLayoutManager(layoutManager);

        listener = new AvistamentoAdapter.OnAvistamentoClickListener() {
            @Override
            public void onAvistamentoClick(View view, int position) {
                Avistamento avistamento = repo.getAvistamento(position);
                avistamento.setAvistamento(avistamento.getAvistamento()+1);
                avistamentoAdapter.notifyItemChanged(position);
            }

            @Override
            public void onSubClik(View view, int position) {
                Avistamento avistamento = repo.getAvistamento(position);
                if (avistamento.getAvistamento()>0)
                    avistamento.setAvistamento(avistamento.getAvistamento()-1);
                avistamentoAdapter.notifyItemChanged(position);
            }

            @Override
            public void onAddClick(View view, int position) {
                Avistamento avistamento = repo.getAvistamento(position);
                avistamento.setAvistamento(avistamento.getAvistamento()+1);
                avistamentoAdapter.notifyItemChanged(position);
            }

            @Override
            public void onDeleteClick(View view, int position) {
                repo.removeAvistamento(position);
                avistamentoAdapter = new AvistamentoAdapter(repo.getAvistamentos(),listener);
                recyclerAvistamento.setAdapter(avistamentoAdapter);
                Toast.makeText(MainActivity.this,"Deletado",Toast.LENGTH_SHORT).show();

            }
        };
        avistamentoAdapter = new AvistamentoAdapter(repo.getAvistamentos(),listener);
        recyclerAvistamento.setAdapter(avistamentoAdapter);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Bundle extras;

                        extras = result.getData().getExtras();
                        String nome = extras.getString("nome");
                        String especie = extras.getString("especie");

                        Avistamento novoAvistamento= new Avistamento(nome,especie);
                        repo.addAvistamento(novoAvistamento);

                        avistamentoAdapter = new AvistamentoAdapter(repo.getAvistamentos(),listener);
                        recyclerAvistamento.setAdapter(avistamentoAdapter);

                    }
                }
        );

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerAvistamento);
    }

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            repo.getAvistamentos().remove(viewHolder.getAdapterPosition());
            avistamentoAdapter = new AvistamentoAdapter(repo.getAvistamentos(),listener);
            recyclerAvistamento.setAdapter(avistamentoAdapter);
            Toast.makeText(MainActivity.this,"Deletado",Toast.LENGTH_SHORT).show();

        }
    };

    public void adicionaEspecie(View view){

        Intent intent = new Intent(MainActivity.this, AdicionaEspecie.class);

        launcher.launch(intent);

    }

}