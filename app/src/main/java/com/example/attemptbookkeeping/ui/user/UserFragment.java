package com.example.attemptbookkeeping.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attemptbookkeeping.DetailPage.logAdapter;
import com.example.attemptbookkeeping.DetailPage.notelog;
import com.example.attemptbookkeeping.R;
import com.example.attemptbookkeeping.databinding.FragmentUserBinding;

import java.util.ArrayList;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    RecyclerView logListRecyclerView;
    logAdapter logadapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel userViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // Add the following lines to create RecyclerView
        logListRecyclerView = view.findViewById(R.id.userInputList);
        logListRecyclerView.setHasFixedSize(true);
        logListRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        logadapter = new logAdapter(view.getContext(), getLog());
        logListRecyclerView.setAdapter(logadapter);

        return view;
    }



    // Add Models to arraylist
    private ArrayList<notelog> getLog() {
        final int[] amounts = getResources().getIntArray(R.array.amount);
        final String[] types = getResources().getStringArray(R.array.type);
        final String[] times = getResources().getStringArray(R.array.time);
        ArrayList<notelog> models = new ArrayList<>();
        notelog p = new notelog();
        for (int i = 0; i < amounts.length; i++) {
            if (i != 0) {
                p = new notelog();
            }
            p.setAmount(amounts[i]);
            p.setType(types[i]);
            p.setTime(times[i]);
            models.add(p);
        }
        return models;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}