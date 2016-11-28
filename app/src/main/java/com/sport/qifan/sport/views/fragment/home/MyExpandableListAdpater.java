package com.sport.qifan.sport.views.fragment.home;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.custome.CircleImageView;
import com.sport.qifan.sport.model.module.SportNewsDetailComments;
import com.sport.qifan.sport.model.module.SportsFirstClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/13.
 */

public class MyExpandableListAdpater extends BaseExpandableListAdapter {
    private List<SportsFirstClass> sportsType;
    private Context mContext;

    public MyExpandableListAdpater(Context context) {
        this.mContext = context;
        this.sportsType = new ArrayList<>();
    }

    public MyExpandableListAdpater(List<SportsFirstClass> sportsType, Context mContext) {
        this.sportsType = sportsType;
        this.mContext = mContext;
    }

    public void setSportTypes(List<SportsFirstClass> sportsType) {
        this.sportsType = sportsType;
    }

    /**
     * 条目数量
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return sportsType.size();
    }

    /**
     * 每个条目里面有几个内容
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return sportsType.get(groupPosition).getSports().size();
    }

    /**
     * 获取指定的分组数据
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return sportsType.get(groupPosition);
    }

    /**
     * 获取指定分组中的指定子选项数据
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sportsType.get(groupPosition).getSports().get(childPosition);
    }

    /**
     * 获取指定分组的ID, 这个ID必须是唯一的
     *
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获取子选项的ID, 这个ID必须是唯一的
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们。
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 获取显示指定分组的视图
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_sports_first_class, parent, false);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        return convertView;
    }

    /**
     *  获取显示指定分组中的指定子选项的视图
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder=null;
        if (convertView==null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_sports_second_class,parent,false);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ChildViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        @BindView(R.id.sport_image)
        ImageView sport_image;
        @BindView(R.id.sport_type)
        TextView sport_type;
        @BindView(R.id.event_total_count)
        TextView event_total_count;

        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ChildViewHolder {
        @BindView(R.id.creator_head)
        CircleImageView creator_head;
        @BindView(R.id.creator_name)
        TextView creator_name;
        @BindView(R.id.event_start_time)
        TextView event_start_time;
        @BindView(R.id.event_end_time)
        TextView event_end_time;
        @BindView(R.id.event_place)
        TextView event_place;
        @BindView(R.id.event_number_vs_total)
        TextView event_number_vs_total;

        public ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
