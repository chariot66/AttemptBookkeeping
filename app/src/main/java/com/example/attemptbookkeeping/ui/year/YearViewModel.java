package com.example.attemptbookkeeping.ui.year;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YearViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public YearViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is year fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}