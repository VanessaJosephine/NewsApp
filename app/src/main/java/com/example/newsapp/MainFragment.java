package com.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainFragment extends Fragment implements NewsAdapter.ItemClickListener, TopStoriesAdapter.ItemClickListener {
    private ArrayList<News> newsList, news2List, news3List;
    private String[] newsTitle;
    private int[] imageResourceID;
    private String[] newsDesc;
    // Recycle View variable
    RecyclerView newsRecyclerView, news2RecyclerView, topStoriesRecyclerView;
    RecyclerView.LayoutManager layoutManager, layoutManager2, layoutManager3;
    NewsAdapter newsAdapter, news2Adapter;
    TopStoriesAdapter topStoriesAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // Initializes all news
        dataInitialize();
        // Initializes Recycler View
        recyclerViewInitialize(view);
        return view;

    }
    private void recyclerViewInitialize(View view)
    {
        // Recycler Views for all 3
        newsRecyclerView = view.findViewById(R.id.recyclerViewNews);
        news2RecyclerView = view.findViewById(R.id.recyclerViewNews2);
        topStoriesRecyclerView = view.findViewById(R.id.recyclerViewTopStories);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);

        newsAdapter = new NewsAdapter(getActivity(), newsList, this);
        news2Adapter = new NewsAdapter(getActivity(), news2List, this);
        topStoriesAdapter = new TopStoriesAdapter(getActivity(), news3List, this);

        newsRecyclerView.setAdapter(newsAdapter);
        news2RecyclerView.setAdapter(news2Adapter);
        topStoriesRecyclerView.setAdapter(topStoriesAdapter);

        newsRecyclerView.setLayoutManager(layoutManager);
        news2RecyclerView.setLayoutManager(layoutManager2);
        topStoriesRecyclerView.setLayoutManager(layoutManager3);
    }
    private void dataInitialize() {
        // News List
        newsList = new ArrayList<>();
        news2List = new ArrayList<>();
        // Top Stories List
        news3List = new ArrayList<>();
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
        for (int i = 0; i < newsTitle.length/2; i++) {
            News news = new News(newsTitle[i], newsDesc[i], imageResourceID[i]);
            newsList.add(news);
        }
        for (int i = newsTitle.length/2; i < newsTitle.length; i++) {
            News news = new News(newsTitle[i], newsDesc[i], imageResourceID[i]);
            news2List.add(news);
        }
        for (int i = 0; i < newsTitle.length; i += 2) {
            News news = new News(newsTitle[i], newsDesc[i], imageResourceID[i]);
            news3List.add(news);
        }
    }
    @Override
    public void onItemClick(News news)
    {
        Fragment fragment = ContentFragment.newInstance(news.title, news.desc, news.image);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("main_fragment"));
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}