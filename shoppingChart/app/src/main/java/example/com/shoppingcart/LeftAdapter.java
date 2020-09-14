package example.com.shoppingcart;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    public int selectTypeId;
    public MainActivity activity;
    public ArrayList<GoodsItem> dataList;

    public LeftAdapter(MainActivity activity, ArrayList<GoodsItem> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }


    //创建ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_left_linkage,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //绑定ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsItem item = dataList.get(position);

        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        if(dataList==null){
            return 0;
        }
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCount,type;
        private GoodsItem item;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCount = (TextView) itemView.findViewById(R.id.tvCount);
            type = (TextView) itemView.findViewById(R.id.type);
            itemView.setOnClickListener(this);
        }

        public void bindData(GoodsItem item){
            this.item = item;
            type.setText(item.typeName);
            //根据类别Id获取属于当前类别的数量
            int count = activity.getSelectedGroupCountByTypeId(item.typeId);
            tvCount.setText(String.valueOf(count));
            if(count<1){
                tvCount.setVisibility(View.GONE);
            }else{
                tvCount.setVisibility(View.VISIBLE);
            }
            if(item.typeId==selectTypeId){
                itemView.setBackgroundColor(Color.WHITE);
            }else{
                itemView.setBackgroundColor(Color.TRANSPARENT);
            }

        }

        //当左侧的列表被点击时，右侧的列表的 第position个item显示在listView的最上面一项
        @Override
        public void onClick(View v) {
            activity.onTypeClicked(item.typeId);
        }
    }
}
