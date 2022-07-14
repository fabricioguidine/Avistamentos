package br.ufjf.dcc196.fabricioguidine.avistamentos;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class AvistamentosRepository {
    private Context context;
    private SharedPreferences preferences;
    private final String PREFERENCES_NAME = "br.ufjf.dcc196.fabricioguidine.avistamentos";

    private List<Avistamento> avistamentos;

    public AvistamentosRepository(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        avistamentos = new ArrayList<Avistamento>();
        auxBotaAvistamentos();
        System.out.println("Avistamentos Tamanho :" + avistamentos.size());
    }

    private void auxBotaAvistamentos(){
        avistamentos.add(new Avistamento("Bem-te-vi","Pitangus sulphuratus"));
        avistamentos.add(new Avistamento("Martim-pescador","Megaceryle torquata"));
        avistamentos.add(new Avistamento("João-de-barro", "Furnarius rufus"));
    }

    public void addAvistamento(Avistamento novoAvistamento){
        avistamentos.add(novoAvistamento);
    }

    public Avistamento getAvistamento(int position){
        return avistamentos.get(position);
    }

    public List<Avistamento> getAvistamentos(){
        return this.avistamentos;
    }

    public void removeAvistamento(int posicao){avistamentos.remove(posicao);
    for (int i=0; i<avistamentos.size(); i++){
        System.out.println(avistamentos.get(i).getNome());
    }
    }

}
