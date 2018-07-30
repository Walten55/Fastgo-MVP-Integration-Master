package me.walten.fastgo.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

import static me.walten.fastgo.common.Fastgo.getContext;

public abstract class XTinyBaseAdapter<T> extends BaseAdapter {

	protected List<T> mList;
	public Context context;
	private int layoutId;

	public XTinyBaseAdapter(Context context, List<T> mList, int layoutId) {
		this.context = context;
		this.mList = mList;
		this.layoutId = layoutId;
	}

	public XTinyBaseAdapter(List<T> mList, int layoutId){
		this(getContext(),mList,layoutId);
	}

	public void setData(List<T> data,boolean clear,boolean atEnd){
		if(mList==null)
			return;
		if(clear)
			mList.clear();
		if(atEnd)
			mList.addAll(mList.size(),data);
		else
			mList.addAll(0,data);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if(mList!=null)
			return mList.size();
		return 0;
	}

	@Override
	public T getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parents) {
		if(convertView == null)
			convertView = LayoutInflater.from(context).inflate(layoutId, parents,false);
		convert(convertView,mList.get(position) ,position);
		return convertView;
	}

	public abstract void convert(View convertView,T bean,int position);
}
