package br.ufjf.dcc196.fabricioguidine.avistamentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AvistamentoAdapter extends RecyclerView.Adapter<AvistamentoAdapter.AvistamentoViewHolder> {
    private List<Avistamento> avistamentos;
    private OnAvistamentoClickListener listener;

    public AvistamentoAdapter(List<Avistamento> avistamentos, OnAvistamentoClickListener listener) {
        this.avistamentos = avistamentos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AvistamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context contexto = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View avistamentoView = inflater.inflate(R.layout.avistamento_layout,parent,false);
        AvistamentoViewHolder viewHolder = new AvistamentoViewHolder(avistamentoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AvistamentoViewHolder holder, int position) {
    Avistamento avistamento = avistamentos.get(position);
    holder.textViewNome.setText(avistamento.getNome());
    holder.textViewEspecie.setText(avistamento.getEspecie());
    holder.textViewAvistamentos.setText(avistamento.getAvistamento().toString());
    }

    @Override
    public int getItemCount() {
        return avistamentos.size();
    }

    public class AvistamentoViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNome;
        private TextView textViewEspecie;
        private TextView textViewAvistamentos;

        public AvistamentoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewEspecie = itemView.findViewById(R.id.textViewEspecie);
            textViewAvistamentos = itemView.findViewById(R.id.textViewAvistamentos);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAvistamentoClick(v,getAdapterPosition());
                }
            });

            itemView.findViewById(R.id.buttonSub).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSubClik(v,getAdapterPosition());
                }
            });

            itemView.findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAddClick(v,getAdapterPosition());
                }
            });

            itemView.findViewById(R.id.buttonExcluir).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(v,getAdapterPosition());
                }
            });
        }
    }

    public interface OnAvistamentoClickListener{
        void onAvistamentoClick(View view,int position);
        void onSubClik(View view,int position);
        void onAddClick(View view, int position);
        void onDeleteClick(View view, int position);
    }

}
