package example.com.shoppingcart;

import java.util.ArrayList;
import java.util.Random;

public class GoodsItem {
    public int id;
    public int typeId;
    public int rating;
    public String name,img;
    public String typeName;
    public double price;
    public int count;
    private ArrayList<Results> results;
    private static String[] typeDishname={"素菜","荤菜","豆制品","口味","主食","酒水饮料"};
    private static String[][] Dishname={
            {"包心菜","花菜","玉米","黄花菜","空心菜","兰花菜","藕片","青菜","生菜","西红柿","香菜","玉米"},
            {"鹌鹑蛋","包心鱼丸","大虾","关东大鱼板","黑毛肚","鸡腿肉","螺丝肉","牛肉卷","熟牛肉","香菇脆", "鸭肠","肘花卷"},
            {"鸡蛋干","香干","油面筋","油豆腐","日本豆腐","千张","豆腐结","腐竹"},
            {"不辣","微辣","中辣","特辣","花椒油","辣椒油","醋"},
            {"年糕片","白圈粉","黑圈粉","方便面","龙口粉丝","土豆粉", "油条","宽粉"},
            {"雪花啤酒","雪碧","可乐","王老吉"}
    };
    private static double[][] Disprice={
            {2.5,3,1.5,2,2,3,3.5,1,1,2,3.5,5},
            {5,3,10,3,8,6,5,10,15,5,7,7},
            {5,3,3,4,6,2,2,3},
            {0,0,0,0,0,0,0},
            {3,2.5,2.5,2,3,3,2,4},
            {3.5,4,4,4}
    };
    private static String[][] Disimg={
            {"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E5%8C%85%E5%BF%83%E8%8F%9C.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E8%8A%B1%E8%8F%9C.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E7%8E%89%E7%B1%B3.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E9%BB%84%E8%8A%B1%E8%8F%9C.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E7%A9%BA%E5%BF%83%E8%8F%9C.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E5%85%B0%E8%8A%B1%E8%8F%9C.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E8%97%95%E7%89%87.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E9%9D%92%E8%8F%9C.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E7%94%9F%E8%8F%9C.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E8%A5%BF%E7%BA%A2%E6%9F%BF.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E9%A6%99%E8%8F%9C.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E7%B4%A0%E8%8F%9C%E7%B1%BB/%E8%A5%BF%E7%BA%A2%E6%9F%BF.jpg"
            }
            ,{"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E9%B9%8C%E9%B9%91%E8%9B%8B.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E5%8C%85%E5%BF%83%E9%B1%BC%E4%B8%B8.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E5%A4%A7%E8%99%BE.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E5%85%B3%E4%B8%9C%E5%A4%A7%E9%B1%BC%E6%9D%BF.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E9%BB%91%E6%AF%9B%E8%82%9A.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E9%B8%A1%E8%85%BF%E8%82%89.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E8%9E%BA%E4%B8%9D%E8%82%89.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E7%89%9B%E8%82%89%E5%8D%B7.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E7%86%9F%E7%89%9B%E8%82%89.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E9%A6%99%E8%8F%87%E8%84%86.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E9%B8%AD%E8%82%A0.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%8D%A4%E8%8F%9C%E7%B1%BB/%E8%82%98%E8%8A%B1%E5%8D%B7.jpg"}
            ,{"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%B1%86%E5%88%B6%E5%93%81/%E9%B8%A1%E8%9B%8B%E5%B9%B2.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%B1%86%E5%88%B6%E5%93%81/%E9%A6%99%E5%B9%B2.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%B1%86%E5%88%B6%E5%93%81/%E6%B2%B9%E9%9D%A2%E7%AD%8B.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%B1%86%E5%88%B6%E5%93%81/%E6%B2%B9%E8%B1%86%E8%85%90.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%B1%86%E5%88%B6%E5%93%81/%E6%97%A5%E6%9C%AC%E8%B1%86%E8%85%90.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%B1%86%E5%88%B6%E5%93%81/%E5%8D%83%E5%BC%A0.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%B1%86%E5%88%B6%E5%93%81/%E8%B1%86%E8%85%90%E7%BB%93.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E8%B1%86%E5%88%B6%E5%93%81/%E8%85%90%E7%AB%B9.jpg"}
            ,{"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E5%8F%A3%E5%91%B3/%E9%94%85%E5%BA%95-%E5%BE%AE%E8%BE%A3%20%E4%B8%AD%E8%BE%A3%20%E7%89%B9%E8%BE%A3.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E5%8F%A3%E5%91%B3/%E9%94%85%E5%BA%95-%E5%BE%AE%E8%BE%A3%20%E4%B8%AD%E8%BE%A3%20%E7%89%B9%E8%BE%A3.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E5%8F%A3%E5%91%B3/%E9%94%85%E5%BA%95-%E5%BE%AE%E8%BE%A3%20%E4%B8%AD%E8%BE%A3%20%E7%89%B9%E8%BE%A3.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E5%8F%A3%E5%91%B3/%E9%94%85%E5%BA%95-%E5%BE%AE%E8%BE%A3%20%E4%B8%AD%E8%BE%A3%20%E7%89%B9%E8%BE%A3.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E5%8F%A3%E5%91%B3/%E8%8A%B1%E6%A4%92%E6%B2%B9.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E5%8F%A3%E5%91%B3/%E8%BE%A3%E6%A4%92%E6%B2%B9.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E5%8F%A3%E5%91%B3/%E9%A6%99%E9%86%8B.jpg"}
            ,{"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E4%B8%BB%E9%A3%9F/%E5%B9%B4%E7%B3%95%E7%89%87.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E4%B8%BB%E9%A3%9F/%E7%99%BD%E5%9C%88%E7%B2%89.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E4%B8%BB%E9%A3%9F/%E9%BB%91%E5%9C%88%E7%B2%89.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E4%B8%BB%E9%A3%9F/%E6%96%B9%E4%BE%BF%E9%9D%A2.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E4%B8%BB%E9%A3%9F/%E9%BE%99%E5%8F%A3%E7%B2%89%E4%B8%9D.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E4%B8%BB%E9%A3%9F/%E5%9C%9F%E8%B1%86.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E4%B8%BB%E9%A3%9F/%E6%B2%B9%E6%9D%A1.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E4%B8%BB%E9%A3%9F/%E5%AE%BD%E7%B2%89.jpg"}
            ,{"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E9%85%92%E6%B0%B4%E9%A5%AE%E6%96%99/%E9%9B%AA%E8%8A%B1%E5%95%A4%E9%85%92.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E9%85%92%E6%B0%B4%E9%A5%AE%E6%96%99/%E9%9B%AA%E7%A2%A7.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E9%85%92%E6%B0%B4%E9%A5%AE%E6%96%99/%E5%8F%AF%E4%B9%90.jpg"
            ,"http://namiapp.oss-cn-beijing.aliyuncs.com/food/%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85/%E9%85%92%E6%B0%B4%E9%A5%AE%E6%96%99/%E7%8E%8B%E8%80%81%E5%90%89.jpg"}};

    public GoodsItem(int id, double price, String name, int typeId, String typeName, String Disimg) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        this.img=Disimg;
        rating = new Random().nextInt(5)+1;
    }

    private static ArrayList<GoodsItem> goodsList;
    private static ArrayList<GoodsItem> typeList;

    private static void initData(){
        goodsList = new ArrayList<>();
        typeList = new ArrayList<>();
        GoodsItem item = null;

//        for(int i=0;i<4;i++){
//            for(int j=1;j<10;j++){
//                item = new GoodsItem(100*i+j,Math.random()*100,"商品"+(100*i+j),i,"种类"+i);
//                goodsList.add(item);
//            }
//            typeList.add(item);
//        }
        for(int i=0;i<typeDishname.length;i++){
            for(int j=1;j<Dishname[i].length;j++){
                item = new GoodsItem(100*i+j,Disprice[i][j],Dishname[i][j],i,typeDishname[i],Disimg[i][j]);
                goodsList.add(item);
            }
            typeList.add(item);
        }
    }

    public static ArrayList<GoodsItem> getGoodsList(){
        if(goodsList==null){
            initData();
        }
        return goodsList;
    }
    public static ArrayList<GoodsItem> getTypeList(){
        if(typeList==null){
            initData();
        }
        return typeList;
    }
}
