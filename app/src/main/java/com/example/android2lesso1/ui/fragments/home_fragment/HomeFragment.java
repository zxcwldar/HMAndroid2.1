package com.example.android2lesso1.ui.fragments.home_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android2lesso1.R;
import com.example.android2lesso1.constants.Constants;
import com.example.android2lesso1.note_model.NoteModel;
import com.example.android2lesso1.databinding.FragmentHomeBinding;
import com.example.android2lesso1.interfaces.OnItemClickListener;
import com.example.android2lesso1.ui.fragments.home_fragment.adapter.AdapterNotes;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    AdapterNotes adapterNotes;
    NoteModel noteModel;
    OnItemClickListener onItemClickListener;

    ArrayList<NoteModel> text = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterNotes = new AdapterNotes(text);
        binding.recyclerNotes.setAdapter(adapterNotes);

        initListeners();
        getData();
        sendData();

    }

    private void sendData() {
        adapterNotes.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(NoteModel noteModel) {
                String title = noteModel.getText();
                int number = noteModel.getNumber();
                noteModel = new NoteModel(title, number);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.KEY2, noteModel);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_createNotesFragment, bundle);
            }
        });
    }

    private void getData() {
        if (getArguments() != null) {
            noteModel = (NoteModel) getArguments().getSerializable(Constants.KEY1);
            adapterNotes.setNotesToList(noteModel);

        }
    }


    private void initListeners() {
        binding.btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_createNotesFragment);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}