# SmartFlexboxLayout
本库是基于RecycleView和FlexboxLayout的封装，提供了标签列表的多选，单选，和只用于列表标签的展示，水平及垂直导航栏（支持绑定ViewPager）等功能。

# 特性
1、使用简单，只需几行代码就可以使用本库<br/>
2、自定义性强，默认选择时和选中状态时，字体颜色，大小，背景色都可以自定义，如果还不满足你的需求，可以重写item的layout<br/>
3、支持单选、多选、和只用于展示使用，多选支持选择数量<br/>
4、支持setListener，当点击或选择时回调<br/>
5、支持设置预选列表 setSelectedData(List<Integer>)<br/>
6、支持获取选中数据的列表 getSelectedData()<br/>
7、支持顶部导航栏和垂直导航栏，支持绑定ViewPager

# 效果图

<img src="img/picture_20190718174448.jpg" width="360px"/>  <img src="img/gif.gif" width="360px"/>


# 如何使用

## 添加Gradle依赖<br/>
#### 1.先在项目根目录的 build.gradle 的 repositories 添加:<br/>

    allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }

#### 2、然后在dependencies添加：

    dependencies {
      ...
      //只支持AndroidX
      implementation 'com.github.huahuiPj:SmartFlexboxLayout:1.1.2'

      implementation 'androidx.appcompat:appcompat:1.0.2'
      implementation 'androidx.recyclerview:recyclerview:1.0.0'
    }

#### 3、布局文件中声明：

    多选布局：
    <com.chh.flexboxlayoututils.widget.SmartFlexboxLayout
            android:id="@+id/mSmartFlexboxLayout_Mulit"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:default_color="#555555"
            app:selected_textColor="#ffffff"
            app:default_drawable="@drawable/shape_default_drawable"
            app:selected_drawable="@drawable/shape_selected_drawable"
            app:text_size="14sp"
            app:checked_enable="true"
            app:mode="Mulit"
            app:max_num="3"/>

    单选布局：
    <com.chh.flexboxlayoututils.widget.SmartFlexboxLayout
            android:id="@+id/mSmartFlexboxLayout_singer"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:default_color="#555555"
            app:selected_textColor="#ffffff"
            app:default_drawable="@drawable/shape_default_drawable"
            app:selected_drawable="@drawable/shape_selected_drawable"
            app:text_size="14sp"
            app:checked_enable="true"
            app:mode="Singer"/>

     只用于展示布局：
    <com.chh.flexboxlayoututils.widget.SmartFlexboxLayout
            android:id="@+id/mSmartFlexboxLayout_onlyShow"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:checked_enable="false"/>

    垂直导航栏布局展示：
    <com.chh.flexboxlayoututils.widget.SmartFlexboxLayout
            android:id="@+id/mSmartFlexboxLayout_singerTabVertical"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="#eaeaea"
            android:layout_marginTop="20dp"
            app:default_color="#333333"
            app:selected_textColor="#f00"
            app:default_drawable="@drawable/layer_tab_v_default"
            app:selected_drawable="@drawable/layer_tab_v_select"
            app:divider="@drawable/shape_divider"
            app:text_size="14sp"
            app:checked_enable="true"
            app:mode="Singer"
            app:line_feed="false"        //true：换行    false：不换行
            app:orientation="Vertical"/>

    水平导航栏布局展示
    <com.chh.flexboxlayoututils.widget.SmartFlexboxLayout
            android:id="@+id/mSmartFlexboxLayout_singerTabHorizontal"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#ffffff"
            android:layout_marginTop="20dp"
            app:default_color="#333333"
            app:selected_textColor="#f00"
            app:default_drawable="@drawable/layer_tab_h_default"
            app:selected_drawable="@drawable/layer_tab_h_select"
            app:text_size="14sp"
            app:checked_enable="true"
            app:mode="Singer"
            app:line_feed="false"          //true：换行    false：不换行
            app:orientation="Horizontal"/>

#### 4、支持属性：<br/>
* default_color        默认时的字体颜色
* selected_textColor   选中时的字体颜色
* default_drawable     默认时样式
* selected_drawable    选中时的样式
* text_size            字体大小
* checked_enable       设置是否可以选中  （默认根据选择模式：单选模式-false  多选模式-true）
* mode                 选择类型  单选：Singer    多选：Mulit （默认多选）
* max_num              多选时生效 最大选择数量 (默认单选为1  多选为1)
*line_feed             是否换行  true：换行    false：不换行 (默认换行)
*divider               分隔线  （默认无）
*orientation           布局方向  水平方向：Horizontal   垂直方向：Vertical   （默认水平方向）



#### 5、事件 - 选择标签时的回调

    mSmartFlexboxLayout_Mulit.setListener(new setOnItemClickListener() {
        @Override
        public void onItemClick(View view, int position, boolean isCheck) {
            Log.d("TAG","onItemClick:"+ position +" isCheck:"+isCheck);
        }
    });

#### 6、获取选中的标签数据列表
    List<String> selectedData = mSmartFlexboxLayout_Mulit.getSelectedData();

#### 7、预先设置选中列表
    mSmartFlexboxLayout_Mulit.setSelectedData(List<Integer>);

#### 8、数据传入
    mSmartFlexboxLayout_Mulit.setData(Context,List<String>);

#### 9、支持条目布局传入
    mSmartFlexboxLayout_onlyShow.setData(Context,layoutRes,List<String>);

## 注意：自定义条目布局时，根布局为TextView ，如下所示：

    <TextView xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/mTextItems"
        android:layout_width="wrap_content"          //要实现水平导航栏item宽度相等时要设置权重（android:layout_width="0dp"  android:layout_weight="1" ）     否则不用设置
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        android:paddingLeft="10dp"
        android:paddingRight="10dp"
        android:paddingTop="6dp"
        android:paddingBottom="6dp"
        android:drawableLeft="@drawable/shape_show_text"
        android:drawablePadding="5dp"
        android:gravity="center_vertical"
        android:textSize="14sp"
        android:textColor="#333333"
        android:background="#ffffff"
        android:maxLines="1"
        android:text="item"/>



# 几种方式使用展示

    //多选
    final SmartFlexboxLayout mSmartFlexboxLayout_Mulit = findViewById(R.id.mSmartFlexboxLayout_Mulit);
    mSmartFlexboxLayout_Mulit.setData(this,dataList);//设置数据源
    mSmartFlexboxLayout_Mulit.setSelectedData(selectList);//设置预先选中的数据
    mSmartFlexboxLayout_Mulit.setListener(new setOnItemClickListener() {
        @Override
        public void onItemClick(View view, int position, boolean isCheck) {
            Log.d("TAG","onItemClick:"+ position +" isCheck:"+isCheck);
        }
    });

    //单选
    final SmartFlexboxLayout mSmartFlexboxLayout_singer = findViewById(R.id.mSmartFlexboxLayout_singer);
    mSmartFlexboxLayout_singer.setData(this,dataList);//设置数据源
    mSmartFlexboxLayout_singer.setSelectedData(selectList);//设置预先选中的数据
    mSmartFlexboxLayout_singer.setListener(new setOnItemClickListener() {
        @Override
        public void onItemClick(View view, int position, boolean isCheck) {
            Log.d("TAG","onItemClick:"+ position +" isCheck:"+isCheck);
        }
    });

    //只展示
    final SmartFlexboxLayout mSmartFlexboxLayout_onlyShow = findViewById(R.id.mSmartFlexboxLayout_onlyShow);
    mSmartFlexboxLayout_onlyShow.setData(this,R.layout.item_my_tv,dataList);//设置自定义条目及数据源
    mSmartFlexboxLayout_onlyShow.setListener(new setOnItemClickListener() {
        @Override
        public void onItemClick(View view, int position, boolean isCheck) {
            Log.d("TAG","onItemClick:"+ position +" isCheck:"+isCheck);
        }
    });


    //导航栏绑定ViewPager
    ViewPager mViewPager = findViewById(R.id.mViewPager);
    List<Fragment> fList = new ArrayList<>();
    fList.add(new FirstFragmentFragment());
    fList.add(new SecondFragmentFragment());
    mSmartFlexboxLayout.bindPagerAdapter(mViewPager,getSupportFragmentManager(),fList);