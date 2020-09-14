package com.fatchao.gangedrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 抽象 RecyclerView.ViewHolder
 *
 * @param <T> 数据集的类型
 */
public abstract class RvHolder<T> extends RecyclerView.ViewHolder {
    protected RvListener mListener;

    public RvHolder(View itemView, int type, RvListener listener) {
        super(itemView);
        this.mListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v.getId(), getAdapterPosition());
            }
        });
    }

    /**
     * 绑定Item View视图
     *
     * @param t
     * @param position
     */
    public abstract void bindHolder(T t, int position);

}
