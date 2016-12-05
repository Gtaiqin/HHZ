package com.hhzmy.bean;

import java.util.List;

public class MyBean {

    public List<RsBean> rs;

    public static class RsBean {
        public String attriCf;
        public String bigPicture;
        public String categoryGoto;
        public int clickCount;
        public String description;
        public String dirName;
        public String gotoApp;
        public String gotoWap;
        public int id;
        public String ifShowShoppingCart;
        public String imgApp;
        public String imgWap;
        public int level;
        public int parentId;
        public String pcCi;
        public String seoCf;
        public int sort;
        public List<?> advts;
        //添加标记是否选中
        public boolean ischeck;

        public List<ChildrenBean> children;

        public static class ChildrenBean {
            public String attriCf;
            public String bigPicture;
            public String categoryGoto;
            public int clickCount;
            public String description;
            public String dirName;
            public String gotoApp;
            public String gotoWap;
            public int id;
            public String ifShowShoppingCart;
            public String imgApp;
            public String imgWap;
            public int level;
            public int parentId;
            public String pcCi;
            public String seoCf;
            public int sort;
            public List<?> advts;
            //添加标记是否为头部
            public boolean isHeader;

            public List<ChildrenBean> children;

        }
    }
}
