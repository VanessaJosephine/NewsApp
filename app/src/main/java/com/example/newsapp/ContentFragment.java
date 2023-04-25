package com.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Random;

public class ContentFragment extends Fragment implements RelatedNewsAdapter.ItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private ArrayList<News> relatedNewsList;
    private String[] newsTitle;
    private int[] imageResourceID;
    private String[] newsDesc;
    private String mParam1;
    private String mParam2;
    private Integer mParam3;
    // Recycle View variable
    RecyclerView relatedNewsRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RelatedNewsAdapter relatedNewsAdapter;
    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance(String param1, String param2, Integer param3) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        // findViewById
        TextView title = view.findViewById(R.id.textView2);
        TextView desc = view.findViewById(R.id.textViewDesc);
        ShapeableImageView image = view.findViewById(R.id.imageViewContent);

        desc.setMovementMethod(new ScrollingMovementMethod());
        title.setText(mParam1);
        desc.setText(mParam2);
        image.setImageResource(mParam3);

        // Initializes Data
        dataInitialize();
        // Initializes Recycler View
        recyclerViewInitialize(view);

        return view;
    }
    private void dataInitialize() {
        relatedNewsList = new ArrayList<>();
        newsTitle = new String[]{
                getString(R.string.title_1),
                getString(R.string.title_2),
                getString(R.string.title_3),
                getString(R.string.title_4),
                getString(R.string.title_5),
                getString(R.string.title_6),
                getString(R.string.title_7),
                getString(R.string.title_8),
                getString(R.string.title_9),
                getString(R.string.title_10),
        };
        newsDesc = new String[]{
                getString(R.string.news_1),
                getString(R.string.news_2),
                getString(R.string.news_3),
                getString(R.string.news_4),
                getString(R.string.news_5),
                getString(R.string.news_6),
                getString(R.string.news_7),
                getString(R.string.news_8),
                getString(R.string.news_9),
                getString(R.string.news_10),
        };
        imageResourceID = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                R.drawable.g,
                R.drawable.h,
                R.drawable.i,
                R.drawable.j,
        };

        for (int i = 0; i < 3; i++) {
            int int_random = new Random().nextInt(10);
            News news = new News(newsTitle[int_random], newsDesc[int_random], imageResourceID[int_random]);
            relatedNewsList.add(news);
        }
    }
    private void recyclerViewInitialize(View view)
    {
        // Recycler View
        relatedNewsRecyclerView = view.findViewById(R.id.recyclerViewRelated);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
        relatedNewsAdapter = new RelatedNewsAdapter(getActivity(), relatedNewsList, this);
        relatedNewsRecyclerView.setAdapter(relatedNewsAdapter);
        relatedNewsRecyclerView.setLayoutManager(layoutManager);
    }
    @Override
    public void onItemClick(News news)
    {
        Fragment fragment = ContentFragment.newInstance(news.title, news.desc, news.image);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}