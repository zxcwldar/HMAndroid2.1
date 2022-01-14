package com.example.android2lesso1.ui.fragments.create_notes_fragment;

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
import com.example.android2lesso1.databinding.FragmentCreateNotesBinding;


public class CreateNotesFragment extends Fragment {
    private FragmentCreateNotesBinding binding;
    NoteModel noteModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateNotesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendData();
        getData();

    }


    private void sendData() {
        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.etText.getText().toString();
                int inputNumber = Integer.parseInt(binding.etInt.getText().toString());
                if (title.isEmpty()) {
                    binding.etText.setError("Put some text!");

                } else {
                    Bundle bundle = new Bundle();
                    noteModel = new NoteModel(title, inputNumber);
                    bundle.putSerializable(Constants.KEY1, noteModel);
                    Navigation.findNavController(view).navigate(R.id.action_createNotesFragment_to_homeFragment, bundle);

                }

            }
        });
    }

    private void getData() {
        if (getArguments() != null) {
            noteModel = (NoteModel) getArguments().getSerializable(Constants.KEY2);
            binding.etText.setText(noteModel.getText());
            binding.etInt.setText(Integer.toString(noteModel.getNumber()));

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}