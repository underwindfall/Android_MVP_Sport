package com.sport.qifan.sport.views.fragment.news;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.model.module.SportEvents;
import com.sport.qifan.sport.model.module.SportsNews;
import com.sport.qifan.sport.utils.LoggerUtil;
import com.sport.qifan.sport.views.BaseFragment;
import com.sport.qifan.sport.views.activity.newsdetail.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/13.
 */

public class NewsFragment extends BaseFragment implements NewFragView {
    @BindView(R.id.news_listview)
    protected ListView newListView;

    private NewsFragPresenter newsFragPresenter = new NewsFragPresenter(this);
    private NewsListViewAdapter newListViewAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.news_fragment_layout;
    }

    @Override
    public void initDatas() {
        newsFragPresenter.initNews(getContext());
        newsFragPresenter.getSportsNewsTest();
        newListViewAdapter = new NewsListViewAdapter(newsFragPresenter.getSportsNewsList());
        newListView.setAdapter(newListViewAdapter);
    }

    @Override
    public void initEvents() {
        newListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newsFragPresenter.goToDetailActivity();
            }
        });
    }

    @Override
    public void showProgessBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void goToDetail() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NewsDetailActivity.class);
        startActivity(intent);
    }

    private class NewsListViewAdapter extends BaseAdapter {

        private List<SportsNews> items;

        public NewsListViewAdapter() {
            items = new ArrayList();
        }

        public NewsListViewAdapter(List<SportsNews> items) {
            this.items = items;
        }

        @Override
        public int getCount() {
//            LoggerUtil.i("itemssize", Integer.toString(items.size()));
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news_listview, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            for (int i = 0; i < items.size(); i++) {
                holder.news_photo.setImageResource(items.get(i).getNews_image());
                holder.news_content.setText(items.get(i).getNews_title() + "\n" + items.get(i).getNews_context());
                holder.events_views.setText(Integer.toString(items.get(i).getNews_views()) + " Views");
                holder.events_apply.setText(Integer.toString(items.get(i).getNews_apply()) + " Applies");
                holder.events_like.setSelected(items.get(i).isLiked());
            }

            holder.events_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        notifyDataSetChanged();
                    }
                    //// TODO: 2016/11/17 请求更新服务器
                }
            });
            return convertView;
        }

    }

    public class ViewHolder {
        @BindView(R.id.iv_news_item_background)
        protected ImageView news_photo;
        @BindView(R.id.tv_news_item_content)
        protected TextView news_content;
        @BindView(R.id.tv_news_item_invitefriends)
        protected TextView invitefriends;
        @BindView(R.id.tv_news_item_join)
        protected TextView join_events;
        @BindView(R.id.tv_news_item_time)
        protected TextView events_time;
        @BindView(R.id.tv_news_item_views)
        protected TextView events_views;
        @BindView(R.id.tv_news_item_apply)
        protected TextView events_apply;
        @BindView(R.id.cb_news_item_like)
        protected CheckBox events_like;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
