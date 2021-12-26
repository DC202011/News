package com.example.news;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class left_frag extends Fragment {

    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_frag, container, false);
        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true; // 可以找到news_content_layout布局时，为双页模式
        } else {
            isTwoPane = false; // 找不到news_content_layout布局时，为单页模式
        }
    }

    private List<News>  getNews() {
        List<News> newsList = new ArrayList<>();
            News news1 = new News();
            news1.setTitle("小米11pro");
            news1.setContent(getRandomLengthContent("只卖4999的小米手机"));
            news1.setPic(R.drawable.m1);
            newsList.add(news1);
            News news2 = new News();
            news2.setTitle("小米11U");
            news2.setContent(getRandomLengthContent("2999"));
            news2.setPic(R.drawable.m2);
            newsList.add(news2);
            News news3 = new News();
            news3.setTitle("小米mix4");
            news3.setContent(getRandomLengthContent("5999"));
            news3.setPic(R.drawable.m3);
            newsList.add(news3);
        return newsList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < length; i++) {
            builder.append(content);
//        }
        return builder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView newsTitleText;
            ImageView image;

            public ViewHolder(View view) {
                super(view);
                newsTitleText = (TextView) view.findViewById(R.id.news_title);
                image=(ImageView) view.findViewById(R.id.imageView);//传入图片
            }

        }

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_son, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(mNewsList.size()+"yutgguihkhflfjlahfilaehflahfaiwlflawiufiwafwiafyiwalufawiufawlfawlfaw");
                    News news = mNewsList.get(holder.getAbsoluteAdapterPosition());
                    if (isTwoPane) {
                        right_frag newsContentFragment = (right_frag)
                                getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        right_activity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
            holder.image.setImageResource(news.getPic());
        }

        @Override
        public int getItemCount() {

            return mNewsList.size();
        }

    }


//    private void initNews(){
//        for(int i=0;i<2;i++){
//            News mi11pro = new News("小米11pro","4999",R.drawable.m1);
//
//            News mi11u = new News("小米11u","2999",R.drawable.m2);
//
//            News mimix = new News("小米mix4","5999",R.drawable.m3);
//        }
//
//    }


}
