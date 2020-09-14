package example.com.shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;

import java.text.NumberFormat;
import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView imgCart;
    private ViewGroup anim_mask_layout;
    private RecyclerView rvType,rvSelected;
    private TextView tvCount,tvCost,tvSubmit;
    private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;
    private StickyListHeadersListView listView;


    private ArrayList<GoodsItem> dataList,typeList;
    private SparseArray<GoodsItem> selectedList;
    private SparseIntArray groupSelect;

    private RightAdapter myAdapter;
    private ShoppingCartAdapter shoppingCartAdapter;
    private LeftAdapter leftAdapter;

    private NumberFormat nf;
    private Handler mHanlder;

    private String merchantID,useid,username,userphone,userhead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get();
        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mHanlder = new Handler(getMainLooper());
        dataList = GoodsItem.getGoodsList();
        typeList = GoodsItem.getTypeList();
        selectedList = new SparseArray<>();
        groupSelect = new SparseIntArray();
        Intent intent=getIntent();
        merchantID=intent.getStringExtra("merchantID");
        useid=intent.getStringExtra("userid");
        username=intent.getStringExtra("username");
        userphone=intent.getStringExtra("userphone");
        userhead=intent.getStringExtra("userhead");
        initView();
    }

    private void initView(){
        tvCount = (TextView) findViewById(R.id.tvCount);
        tvCost = (TextView) findViewById(R.id.tvCost);
        tvSubmit  = (TextView) findViewById(R.id.tvSubmit);
        rvType = (RecyclerView) findViewById(R.id.typeRecyclerView);

        imgCart = (ImageView) findViewById(R.id.imgCart);
        anim_mask_layout = (RelativeLayout) findViewById(R.id.containerLayout);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);

        listView = (StickyListHeadersListView) findViewById(R.id.itemListView);

        rvType.setLayoutManager(new LinearLayoutManager(this));
        leftAdapter = new LeftAdapter(this,typeList);
        rvType.setAdapter(leftAdapter);
        rvType.addItemDecoration(new DividerDecoration(this));

        myAdapter = new RightAdapter(dataList,this);
        listView.setAdapter(myAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            //当右侧的列表滑动的时候，与左侧的列表进行关联
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                GoodsItem item = dataList.get(firstVisibleItem);
                if(leftAdapter.selectTypeId != item.typeId) {
                    leftAdapter.selectTypeId = item.typeId;
                    //界面刷新
                    leftAdapter.notifyDataSetChanged();
                    //根据类别id获取分类的Position 用于滚动左侧的类别列表（即把左侧的列表滑动到指定位置）
                    rvType.smoothScrollToPosition(getSelectedGroupPosition(item.typeId));
                }
            }
        });

    }


    public void playAnimation(int[] start_location){
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.button_add);
        setAnim(img,start_location);
    }

    private Animation createAnim(int startX, int startY){
        int[] des = new int[2];
        imgCart.getLocationInWindow(des);

        AnimationSet set = new AnimationSet(false);

        Animation translationX = new TranslateAnimation(0, des[0]-startX, 0, 0);
        translationX.setInterpolator(new LinearInterpolator());
        Animation translationY = new TranslateAnimation(0, 0, 0, des[1]-startY);
        translationY.setInterpolator(new AccelerateInterpolator());//加速补间器（interpolator补间器的意思）
        Animation alpha = new AlphaAnimation(1,0.5f);
        set.addAnimation(translationX);
        set.addAnimation(translationY);
        set.addAnimation(alpha);
        set.setDuration(500);

        return set;
    }

    private void addViewToAnimLayout(final ViewGroup vg, final View view,
                                     int[] location) {

        int x = location[0];
        int y = location[1];
        int[] loc = new int[2];
        vg.getLocationInWindow(loc);
        view.setX(x);
        view.setY(y-loc[1]);
        vg.addView(view);
    }
    private void setAnim(final View v, int[] start_location) {

        addViewToAnimLayout(anim_mask_layout, v, start_location);
        Animation set = createAnim(start_location[0],start_location[1]);
        //监听Animation的运行过程
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                mHanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim_mask_layout.removeView(v);
                    }
                },100);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(set);
    }

    @Override
    public void onClick(View v){
        String arr="";
        String orderinfo="";
        int i = v.getId();
        if (i == R.id.bottom) {
            showBottomSheet();
        } else if (i == R.id.clear) {
            clearCart();
        } else if (i == R.id.tvSubmit) {
            int size = selectedList.size();
            int count =0;
            double cost = 0;
            for(int j=0;j<size;j++){
                GoodsItem item = selectedList.valueAt(j);
                count += item.count;
                cost += item.count*item.price;
                arr +=item.name+","+item.count+","+nf.format(item.price)+",";
                orderinfo +=item.name+","+"×"+item.count+","+nf.format(item.price)+",";
            }
            arr+=" "+","+"总计："+","+nf.format(cost);
            Log.e("arr", arr);
            Intent intent=new Intent(MainActivity.this,OrderActivity.class);
            intent.putExtra("arr",arr);
            intent.putExtra("orderinfo",orderinfo);
            intent.putExtra("cost",nf.format(cost));
            intent.putExtra("merchantID",merchantID);
            intent.putExtra("userid",useid);
            intent.putExtra("username",username);
            intent.putExtra("userphone",userphone);
            intent.putExtra("userhead",userhead);
            startActivity(intent);
            finish();
        } else {

        }
    }

    //添加商品
    public void add(GoodsItem item,boolean refreshGoodList){

        int groupCount = groupSelect.get(item.typeId);
        if(groupCount==0){
            groupSelect.append(item.typeId,1);
        }else{
            groupSelect.append(item.typeId,++groupCount);
        }

        GoodsItem temp = selectedList.get(item.id);
        if(temp==null){
            item.count=1;
            selectedList.append(item.id,item);
        }else{
            temp.count++;
        }
        update(refreshGoodList);
    }
    //移除商品
    public void remove(GoodsItem item,boolean refreshGoodList){

        int groupCount = groupSelect.get(item.typeId);
        if(groupCount==1){
            groupSelect.delete(item.typeId);
        }else if(groupCount>1){
            groupSelect.append(item.typeId,--groupCount);
        }

        GoodsItem temp = selectedList.get(item.id);
        if(temp!=null){
            if(temp.count<2){
                selectedList.remove(item.id);
            }else{
                item.count--;
            }
        }
        update(refreshGoodList);
    }
    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList){
        int size = selectedList.size();
        int count =0;
        double cost = 0;
        for(int i=0;i<size;i++){
            GoodsItem item = selectedList.valueAt(i);
            count += item.count;
            cost += item.count*item.price;
        }

        if(count<1){
            tvCount.setVisibility(View.GONE);
        }else{
            tvCount.setVisibility(View.VISIBLE);
        }

        tvCount.setText(String.valueOf(count));

        if(cost > 0){
            tvSubmit.setVisibility(View.VISIBLE);
        }else{
            tvSubmit.setVisibility(View.GONE);
        }

        tvCost.setText(nf.format(cost));

        if(myAdapter!=null && refreshGoodList){
            myAdapter.notifyDataSetChanged();
        }
        if(shoppingCartAdapter !=null){
            shoppingCartAdapter.notifyDataSetChanged();
        }
        if(leftAdapter !=null){
            leftAdapter.notifyDataSetChanged();
        }
        if(bottomSheetLayout.isSheetShowing() && selectedList.size()<1){
            bottomSheetLayout.dismissSheet();
        }
    }
    //清空购物车
    public void clearCart(){
        selectedList.clear();
        groupSelect.clear();
        update(true);

    }
    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id){
        GoodsItem temp = selectedList.get(id);
        if(temp==null){
            return 0;
        }
        return temp.count;
    }
    //根据类别Id获取属于当前类别的数量
    public int getSelectedGroupCountByTypeId(int typeId){
        return groupSelect.get(typeId);
    }
    //根据类别id获取分类的Position 用于滚动左侧的类别列表
    public int getSelectedGroupPosition(int typeId){
        for(int i=0;i<typeList.size();i++){
            if(typeId==typeList.get(i).typeId){
                return i;
            }
        }
        return 0;
    }

    //当左侧的列表被点击时，右侧的列表的 第position个item显示在listView的最上面一项
    public void onTypeClicked(int typeId){
        listView.setSelection(getSelectedPosition(typeId));
    }

    private int getSelectedPosition(int typeId){
        int position = 0;
        for(int i=0;i<dataList.size();i++){
            if(dataList.get(i).typeId == typeId){
                position = i;
                break;
            }
        }
        return position;
    }

    private View createBottomSheetView(){
        View view = LayoutInflater.from(this).inflate(R.layout.shopping_cart,(ViewGroup) getWindow().getDecorView(),false);
        rvSelected = (RecyclerView) view.findViewById(R.id.selectRecyclerView);
        rvSelected.setLayoutManager(new LinearLayoutManager(this));
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(this);
        shoppingCartAdapter = new ShoppingCartAdapter(this,selectedList);
        rvSelected.setAdapter(shoppingCartAdapter);
        return view;
    }

    private void showBottomSheet(){
        if(bottomSheet==null){
            bottomSheet = createBottomSheetView();
        }
        if(bottomSheetLayout.isSheetShowing()){
            bottomSheetLayout.dismissSheet();
        }else {
            if(selectedList.size()!=0){
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }

    public void get() {
//        Call<GoodsItem> call;
//        call = (Call<GoodsItem>) NetWork.getMyApi().getUserinfo();
//        call.enqueue(new Callback<GoodsItem>() {
//            @Override
//            public void onResponse(Call<GoodsItem> call, retrofit2.Response<GoodsItem> response) {
//                System.out.println("请求成功");
//            }
//            @Override
//            public void onFailure(Call<GoodsItem> call, Throwable t) {
//                System.out.println("请求失败");
//                System.out.print(t.getMessage());
//            }
//        });
    }

}
