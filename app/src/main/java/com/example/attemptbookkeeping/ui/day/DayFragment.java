package com.example.attemptbookkeeping.ui.day;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.attemptbookkeeping.DetailActivity;
import com.example.attemptbookkeeping.R;
import com.example.attemptbookkeeping.databinding.FragmentDayBinding;
import com.example.attemptbookkeeping.tools.DataHolder;

public class DayFragment extends Fragment {

    private FragmentDayBinding binding;
    String notebook;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DayViewModel dayViewModel =
                new ViewModelProvider(this).get(DayViewModel.class);

        binding = FragmentDayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDay;

        // load Notebook Name
        DetailActivity activity = (DetailActivity) getActivity();
        if(activity.noteName == null){
            notebook = DataHolder.getInstance().getItem();
        }
        else{
            notebook = activity.noteName;
        }

        textView.setText(notebook + "'s detail page");

//        dayViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}