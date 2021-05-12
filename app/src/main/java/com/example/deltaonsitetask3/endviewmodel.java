package com.example.deltaonsitetask3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class endviewmodel extends ViewModel {

    public MutableLiveData<Boolean> getEnd() {
        if (end == null)
            end = new MutableLiveData<>();

        return end;
    }

    MutableLiveData<Boolean> end;

    public MutableLiveData<Boolean> getStart() {
        if (start == null)
            start = new MutableLiveData<>();
        return start;
    }

    MutableLiveData<Boolean> start;


}
