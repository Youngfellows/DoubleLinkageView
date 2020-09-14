package com.fatchao.gangedrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 抽象RecyclerViewAdapterr
 *
 * @param <T> 数据集的类型
 */
public abstract class RvAdapter<T> extends RecyclerView.Adapter<RvHolder> {
    protected List<T> list;
    protected Context mContext;
    protected RvListener listener;
    protected LayoutInflater mInflater;
    private RecyclerView mRecyclerView;

    public RvAdapter(Context context, List<T> list, RvListener listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.list = list;
        this.listener = listener;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Item视图,视图与ViewHolder绑定
        View view = mInflater.inflate(getLayoutId(viewType), parent, false);
        return getHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        holder.bindHolder(list.get(position), position);//给视图绑定数据
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    /**
     * 设置Item 布局
     *
     * @param viewType
     * @return
     */
    protected abstract int getLayoutId(int viewType);

    /**
     * 创建改Item 布局的ViewHolder
     *
     * @param view
     * @param viewType
     * @return
     */
    protected abstract RvHolder getHolder(View view, int viewType);

}
