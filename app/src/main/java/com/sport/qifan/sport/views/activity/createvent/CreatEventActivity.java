package com.sport.qifan.sport.views.activity.createvent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.model.module.SportEvents;
import com.sport.qifan.sport.utils.LoggerUtil;
import com.sport.qifan.sport.views.BaseActivity;
import com.sport.qifan.sport.views.fragment.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/29.
 */

public class CreatEventActivity extends BaseActivity implements CreatEventView {
    @BindView(R.id.ll_point_container)
    protected LinearLayout mPointContainer;
    @BindView(R.id.event_type_container)
    protected ViewPager mGridViewContainer;
    private CreatEventPresenter mPresenter = new CreatEventPresenter(this);
    private List<SportEvents> sportEventsList;
    //save each gridview
    private List<View> listGViews;
    //amount of item in one page
    private int pageItemCount = 9;
    //grideview's page size
    private int gvPageSize = 1;
    private int gvColumns = 3;
    private int totalEvents = 0;

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_event_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(CreatEventActivity.this);
        listGViews = new ArrayList<>();
        sportEventsList = mPresenter.initSportEvents();
        totalEvents = mPresenter.getTotalEvents();
        initGirdViewsAndPoints();
        mGridViewContainer.setAdapter(new MyViewPagerAdapter());
    }

    /**
     * 初始化gridview底部的小圆点 and listGirdView
     */
    private void initGirdViewsAndPoints() {
        if (totalEvents > 0) {
            gvPageSize = totalEvents / pageItemCount + 1;
            for (int i = 0; i < gvPageSize; i++) {
                listGViews.add(getViewPagerItem(i));
                View point = new View(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
                if (i == 0) {
                    point.setBackgroundResource(R.drawable.point_selected);
                } else {
                    params.leftMargin = 10;
                    point.setBackgroundResource(R.drawable.point_normal);
                }
                mPointContainer.addView(point, params);
            }
            mPointContainer.setVisibility(View.VISIBLE);
        } else {
            mPointContainer.removeAllViews();
            mPointContainer.setVisibility(View.GONE);
        }
    }

    /**
     * 获取不同页的gridview
     *
     * @param index
     * @return
     */
    private View getViewPagerItem(int index) {
        LayoutInflater inflater = (LayoutInflater) CreatEventActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.viewpage_gridview, null);
        GridView gridview = (GridView) layout.findViewById(R.id.grid_view);
        gridview.setNumColumns(gvColumns);
        MyGridViewAdapter adapter = new MyGridViewAdapter(CreatEventActivity.this, index, pageItemCount);
        gridview.setAdapter(adapter);
        return gridview;
    }

    @Override
    protected void initEvents() {
        mGridViewContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPointSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * position页的圆点变为选中
     *
     * @param position
     */

    private void setPointSelected(int position) {
        int total = mPointContainer.getChildCount();
        for (int i = 0; i < total; i++) {
            View v = mPointContainer.getChildAt(i);
            v.setBackgroundResource(position == i ? R.drawable.point_selected : R.drawable.point_normal);
        }
    }

    @Override
    public void showProgessBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    /**
     * Girdview的adapter
     */
    private class MyGridViewAdapter extends BaseAdapter {
        private List<SportEvents> items;
        private Context context;
        /**
         * ViewPager页码
         */
        private int index;
        /**
         * 根据屏幕大小计算得到的每页item个数
         */
        private int pageItemCount;

        public MyGridViewAdapter(Context context, int index, int pageItemCount) {
            this.context = context;
            this.index = index;
            this.pageItemCount = pageItemCount;
            items = new ArrayList<SportEvents>();

            //get pageitemcount items
            int list_index = index * pageItemCount;
            int lastItem = list_index + pageItemCount;
            if (lastItem > totalEvents) {
                lastItem = totalEvents;
            }
            for (int i = list_index; i < lastItem; i++) {
                LoggerUtil.d("gv", "item name " + sportEventsList.get(i).getEvent_name());
                items.add(sportEventsList.get(i));
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return sportEventsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(CreatEventActivity.this).inflate(R.layout.events_gridview_items, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final ViewHolder finalHolder = holder;
            final SportEvents events = items.get(position);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), events.getEvent_image());
            holder.event_icon.setImageBitmap(bitmap);
            holder.event_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (events.isSelected()) {
                        finalHolder.selected.setVisibility(View.INVISIBLE);
                        items.get(position).setSelected(false);
                    } else {
                        finalHolder.selected.setVisibility(View.VISIBLE);
                        items.get(position).setSelected(true);
                    }
                }
            });
//            if (events.isSelected()) {
//                holder.selected.setVisibility(View.VISIBLE);
//            } else {
//                holder.selected.setVisibility(View.INVISIBLE);
//            }
            return convertView;
        }

        private class ViewHolder {
            ImageView event_icon;
            ImageView selected;

            public ViewHolder(View view) {
                event_icon = (ImageView) view.findViewById(R.id.event_icon);
                selected = (ImageView) view.findViewById(R.id.iv_selected);
            }
        }

    }

    private class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listGViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View gv = listGViews.get(position);
            mGridViewContainer.addView(gv);
            return gv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View gv = listGViews.get(position);
            mGridViewContainer.removeView(gv);
        }
    }
}
