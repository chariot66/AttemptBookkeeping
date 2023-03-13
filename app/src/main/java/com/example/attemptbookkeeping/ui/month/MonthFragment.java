package com.example.attemptbookkeeping.ui.month;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.attemptbookkeeping.databinding.FragmentMonthBinding;

public class MonthFragment extends Fragment {

    private FragmentMonthBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MonthViewModel monthViewModel =
                new ViewModelProvider(this).get(MonthViewModel.class);

        binding = FragmentMonthBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMonth;
        monthViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}