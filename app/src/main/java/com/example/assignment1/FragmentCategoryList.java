package com.example.assignment1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.assignment1.provider.CategoryViewModel;
import com.example.assignment1.provider.EventViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCategoryList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCategoryList extends Fragment {

    SharedPreferences sP;
    ArrayList<Category> eventCategories;
    String eventCategoriesString;
    private CategoryViewModel categoryViewModel;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapterCat adapter;

    Gson gson = new Gson();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCategoryList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListCategory.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCategoryList newInstance(String param1, String param2) {
        FragmentCategoryList fragment = new FragmentCategoryList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, //From sample code
                             Bundle savedInstanceState) {
        Log.d("FragmentCategory", "onCreateView: ");
        sP = getActivity().getSharedPreferences(KeyStore.FILE_NAME, MODE_PRIVATE);
        eventCategoriesString = sP.getString(KeyStore.CATEGORY_LIST, "[]");
        Type type = new TypeToken<ArrayList<Category>>() {}.getType();
        eventCategories = gson.fromJson(eventCategoriesString, type);

        View v = inflater.inflate(R.layout.fragment_category_list, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerAdapterCat(getActivity(), new ArrayList<>());

        recyclerView.setAdapter(adapter);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getAllCategory().observe(getViewLifecycleOwner(), newData1 -> {
            adapter.setData(new ArrayList<Category>(newData1));
            adapter.notifyDataSetChanged();
        });

        return v;
    }
}