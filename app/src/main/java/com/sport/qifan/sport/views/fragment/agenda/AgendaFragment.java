package com.sport.qifan.sport.views.fragment.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.custome.CircleImageView;
import com.sport.qifan.sport.model.module.AgendaEvents;
import com.sport.qifan.sport.views.BaseFragment;
import com.sport.qifan.sport.views.fragment.news.NewsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/13.
 */

public class AgendaFragment extends BaseFragment implements AgendaFragView{
    @BindView(R.id.root_view)
    protected View rootView;
    @BindView(R.id.agenda_list_events)
    protected ListView listView;

    private MyAdapter adapter;
    private AgendaFragPresenter mPresenter = new AgendaFragPresenter(this);
    private List<AgendaEvents> agendaEventsList;

    @Override
    public int getLayoutId() {
        return R.layout.agenda_fragment_layout;
    }

    @Override
    public void initDatas() {
        mPresenter.init(getActivity());
        agendaEventsList=mPresenter.getAgendaEventsTest();
        adapter=new MyAdapter(agendaEventsList);
        listView.setAdapter(adapter);
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void showProgessBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    private class MyAdapter extends BaseAdapter{
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
            ViewHolder holder=null;
            if (convertView==null){
                convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda_event_list, null);
                holder=new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
              holder= (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

         class ViewHolder {
             // TODO: 2016/11/29  
//             @BindView(R.id.user_head)
//             CircleImageView user_head;
//             @BindView(R.id.tv_event_type)
//             TextView event_type;
//             @BindView(R.id.tv_event_time)
//             TextView event_time;
//             @BindView(R.id.tv_event_place)
//             TextView event_place;
//             @BindView(R.id.event_progress_bar)
//             MyProgressBarHorizontal event_progress;

             public ViewHolder(View view){
                ButterKnife.bind(this,view);
            }
        }
    }
}
