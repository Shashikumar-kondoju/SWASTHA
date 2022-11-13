package com.androiddev.projectrelief;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NestedRVFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NestedRVFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recview;
    NestedRVAdapter nestedRVAdapter;
    LinearLayoutManager nestedLayoutManager;

    public NestedRVFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NestedRVFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NestedRVFragment newInstance(String param1, String param2) {
        NestedRVFragment fragment = new NestedRVFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nested_r_v, container, false);
        String str;
        Bundle bundle = this.getArguments();
        str = (String)bundle.getString("key");
        recview = (RecyclerView)view.findViewById(R.id.nested_rec_view);
        nestedLayoutManager = new LinearLayoutManager(getActivity());
        nestedLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recview.setLayoutManager(nestedLayoutManager);
        FirebaseRecyclerOptions<NestedModel> options =
                new FirebaseRecyclerOptions.Builder<NestedModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(str), NestedModel.class)
                        .build();
        nestedRVAdapter = new NestedRVAdapter(options);
        recview.setAdapter(nestedRVAdapter);
        nestedRVAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        nestedRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        nestedRVAdapter.stopListening();
    }
}