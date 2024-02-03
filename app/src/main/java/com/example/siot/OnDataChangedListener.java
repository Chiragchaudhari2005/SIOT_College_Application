package com.example.siot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface OnDataChangedListener {
    void onDataChanged(List<ArrayList<String>> newData);
}
