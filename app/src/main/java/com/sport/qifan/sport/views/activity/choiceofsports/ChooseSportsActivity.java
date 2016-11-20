package com.sport.qifan.sport.views.activity.choiceofsports;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.custome.NoScrollGridView;
import com.sport.qifan.sport.model.module.SportEvents;
import com.sport.qifan.sport.views.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/15.
 */

public class ChooseSportsActivity extends BaseActivity implements ChooseSportsView {

    private static final int CHOICE_SPORTS_FINISH_OK = 0x123;
    @BindView(R.id.ll_point_container)
    protected LinearLayout mPointContainer;
    @BindView(R.id.event_type_container)
    protected ViewPager mGridViewContainer;
    @BindView(R.id.choose_sports_container)
    protected NoScrollGridView gvSportsChoose;
    @BindView(R.id.confirm_btn)
    protected Button btnConfirm;
    private ChooseSportsPresenter mPresenter = new ChooseSportsPresenter(this);
    //save each gridview
    private ArrayList<View> listGridView;
    //amount of item in one page
    private static final int pageItemCount = 9;
    //grideview's page size
    private int gvPageSize = 1;
    private int gvColumns = 3;
    private MySportsChooseAdapter sportsChooseAdapter;

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chose_sports_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(ChooseSportsActivity.this);
        mPresenter.init(ChooseSportsActivity.this);
        listGridView = new ArrayList<>();
        mPresenter.initSportsEvents();
        mGridViewContainer.setAdapter(new MyViewPagerAdapter());
        sportsChooseAdapter = new MySportsChooseAdapter();
        gvSportsChoose.setAdapter(sportsChooseAdapter);
    }

    /**
     * //初始化爱好ViewPager及其下方的圆点
     */
    @Override
    public void initGirdViewsAndPoints() {
        int totalEvents = mPresenter.getTotalEvents();
        if (totalEvents > 0) {
            gvPageSize = totalEvents / pageItemCount + 1;
            for (int i = 0; i < gvPageSize; i++) {
                //add gridview to list
                listGridView.add(getViewPagerItem(i));
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
     * This is function is used for return gridview
     *
     * @param index index for telling from which gridview page we are working on
     * @return
     */
    private View getViewPagerItem(int index) {
        LayoutInflater inflater = (LayoutInflater) ChooseSportsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.viewpage_gridview, null);
        GridView gridView = (GridView) layout.findViewById(R.id.grid_view);
        gridView.setNumColumns(gvColumns);
        MyGridViewAdapter myGridViewAdapter = new MyGridViewAdapter(index, pageItemCount, mPresenter.getSportEvents());
        gridView.setAdapter(myGridViewAdapter);
        return gridView;
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
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(CHOICE_SPORTS_FINISH_OK);
                onBackPressed();
            }
        });
    }

    /**
     * selected point with the position of page
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

    @Override
    public void refreshEventChoose(List<SportEvents> sportEventsChosen) {
        sportsChooseAdapter.setItems(sportEventsChosen);
        sportsChooseAdapter.notifyDataSetChanged();
    }


    /**
     * viewpager's adapter
     */
    private class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listGridView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View gv = listGridView.get(position);
            mGridViewContainer.addView(gv);
            return gv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View gv = listGridView.get(position);
            mGridViewContainer.removeView(gv);
        }
    }

    /**
     * GridViewAdapter
     */
    private class MyGridViewAdapter extends BaseAdapter {
        private List<SportEvents> items;
        /**
         * index to tell which page we want to use
         */
        private int index;
        private int pageItemCount;

        /**
         * Constructor for filling up all of GridViewAdapter
         *
         * @param index
         * @param pageItemCount
         * @param sportEvents
         */
        public MyGridViewAdapter(int index, int pageItemCount, List<SportEvents> sportEvents) {
            this.index = index;
            this.pageItemCount = pageItemCount;
            int totalEvents = sportEvents.size();
            items = new ArrayList<SportEvents>();
            //get pageItemCount items
            int list_index = index * pageItemCount;
            int lastItem = list_index + pageItemCount;
            if (lastItem > totalEvents) {
                lastItem = totalEvents;
            }
            for (int i = list_index; i < lastItem; i++) {
                items.add(sportEvents.get(i));
            }
        }

        @Override
        public int getCount() {
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
        public View getView(final int position, View convertView, final ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_gridview_items, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final ViewHolder finalHolder = holder;
            final SportEvents event = items.get(position);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), event.getEvent_image());
            holder.event_icon.setImageBitmap(bitmap);
            holder.event_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (event.isSelected()) {
                        //unchecked options
                        finalHolder.selected.setVisibility(View.INVISIBLE);
                        items.get(position).setSelected(false);
                        //remove chosen sports
                        mPresenter.removeChooseEvent(event);
                    } else {
                        //settle checked  sports
                        finalHolder.selected.setVisibility(View.VISIBLE);
                        items.get(position).setSelected(true);
                        mPresenter.addChooseEvent(event);
                    }
                }
            });
            if (event.isSelected()) {
                holder.selected.setVisibility(View.VISIBLE);
            } else {
                holder.selected.setVisibility(View.INVISIBLE);
            }
            return convertView;
        }
    }

    private class ViewHolder {
        ImageView event_icon;
        ImageView selected;

        public ViewHolder(View view) {
            event_icon = (ImageView) view.findViewById(R.id.event_icon);
            selected = (ImageView) view.findViewById(R.id.iv_selected);
        }
    }


    /**
     * Adapter for choose sports
     */
    private class MySportsChooseAdapter extends BaseAdapter {
        private List<SportEvents> items;

        public MySportsChooseAdapter() {
            items = new ArrayList<>();
        }

        @Override
        public int getCount() {
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
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_gridview_items, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            SportEvents events = items.get(position);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), events.getEvent_image());
            holder.event_icon.setImageBitmap(bitmap);
            holder.selected.setVisibility(View.INVISIBLE);
            return convertView;
        }

        public void setItems(List<SportEvents> sportEventsChosen) {
            this.items = sportEventsChosen;
        }
    }
}
