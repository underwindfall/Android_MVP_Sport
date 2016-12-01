package com.sport.qifan.sport.views.fragment.agenda;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.custome.CircleImageView;
import com.sport.qifan.sport.custome.MyProgressBarHorizontal;
import com.sport.qifan.sport.custome.PersonInfoPopupWindow;
import com.sport.qifan.sport.model.module.AgendaEvents;
import com.sport.qifan.sport.utils.LoggerUtil;
import com.sport.qifan.sport.views.BaseFragment;
import com.sport.qifan.sport.views.fragment.news.NewsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/13.
 */

public class AgendaFragment extends BaseFragment implements AgendaFragView {
    @BindView(R.id.root_view)
    protected View rootView;
    @BindView(R.id.agenda_list_events)
    protected ListView listView;

    private MyAdapter adapter;
    private AgendaFragPresenter mPresenter = new AgendaFragPresenter(this);
//    private List<AgendaEvents> agendaEventsList;

    @Override
    public int getLayoutId() {
        return R.layout.agenda_fragment_layout;
    }

    @Override
    public void initDatas() {
        mPresenter.init(getActivity());
        adapter = new MyAdapter(mPresenter.getAgendaEventsTest());
        listView.setAdapter(adapter);
    }

    @Override
    public void initEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.showPopupWindow();
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
    public void setBackGroundAlpha(float alpha) {
        WindowManager.LayoutParams windowlp = getActivity().getWindow().getAttributes();
        windowlp.alpha = alpha;
        getActivity().getWindow().setAttributes(windowlp);
    }

    @Override
    public void showPersonInfoPopupWindow(PersonInfoPopupWindow popupWindow) {
        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    private class MyAdapter extends BaseAdapter {
        private List<AgendaEvents> item;

        public MyAdapter(List<AgendaEvents> item) {
            this.item = item;
        }

        @Override
        public int getCount() {
            return item.size();
        }

        @Override
        public Object getItem(int position) {
            return item.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda_event_list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            for (int i = 0; i < item.size(); i++) {
                holder.event_place.setText(item.get(i).getUser_events_place());
                holder.event_time.setText(item.get(i).getUser_events_time());
                holder.event_type.setText(item.get(i).getUser_events_name());
//                holder.event_progress.setProgress(Integer.parseInt(item.get(i).getEvents_Progress()));

            }
            holder.event_progress.setProgress(position * 10);
            return convertView;
        }
    }

    public class ViewHolder {
        @BindView(R.id.user_head)
        CircleImageView user_head;
        @BindView(R.id.tv_event_type)
        TextView event_type;
        @BindView(R.id.tv_event_time)
        TextView event_time;
        @BindView(R.id.tv_event_place)
        TextView event_place;
        @BindView(R.id.event_progress_bar)
        MyProgressBarHorizontal event_progress;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
