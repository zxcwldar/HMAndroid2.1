package com.example.android2lesso1.ui.fragments.home_fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2lesso1.note_model.NoteModel;
import com.example.android2lesso1.databinding.NotesHolderBinding;
import com.example.android2lesso1.interfaces.OnItemClickListener;

import java.util.ArrayList;

public class AdapterNotes extends RecyclerView.Adapter<AdapterNotes.HolderNotes> {
    OnItemClickListener onItemClickListener;
    ArrayList<NoteModel> list = new ArrayList<>();

    public AdapterNotes(ArrayList<NoteModel> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public HolderNotes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderNotes(NotesHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull HolderNotes holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNotesToList(NoteModel noteModel) {
        list.add(noteModel);
        notifyDataSetChanged();
    }

    public class HolderNotes extends RecyclerView.ViewHolder {
        private NotesHolderBinding binding;

        public HolderNotes(@NonNull NotesHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(NoteModel noteModel) {
            binding.tvNotes.setText(noteModel.getText());
            binding.tvNumber.setText(Integer.toString(noteModel.getNumber()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    onItemClickListener.onItemClick(noteModel);
                }
            });
        }
    }
}
