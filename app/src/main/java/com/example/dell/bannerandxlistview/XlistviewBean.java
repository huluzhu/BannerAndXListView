package com.example.dell.bannerandxlistview;

import java.util.List;

/**
 * Created by 胡计强 on 2017/06/28.
 */
public class XlistviewBean {

    /**
     * ret_code : 200
     * ret_msg : ok
     * list : [{"date":"111","id":1,"pic":"http://p1.pstatp.com/large/22c90001cf8b5388ce33","title":" \n他\u201c秘书圈\u201d的人数规模，赶上了周永康","type":1},{"date":"333","id":3,"pic":"http://p1.pstatp.com/list/190x124/e580016ab3624f1ed33","title":"家里的一分硬币现在值多少钱？\n家里的一分硬币现在值多少钱？\n家里的一分硬币现在值多少钱？\n家里的一分硬币现在值多少钱？\n家里的一分硬币现在值多少钱","type":1},{"date":"444","id":4,"pic":"http://p1.pstatp.com/origin/26ec0004cc0249b49e7c","title":"世界上\u201c最恐怖\u201d的景点大盘点，中国竟然排在第一！","type":1},{"date":"6666","id":6,"pic":"http://p3.pstatp.com/list/190x124/216d001357929b02f476","title":" \n张爱朋首回应白百何绯闻事件，短短二句话，白百何后悔看错了吧！","type":1},{"date":"7777","id":7,"pic":"http://p3.pstatp.com/list/190x124/213300016c777190f9ed|http://p3.pstatp.com/list/190x124/22ca00011911b0a8061c","title":" \n曾经的歌星，落魄到参加选秀，结果唱一半却被韩红喊停","type":2},{"date":"8888","id":8,"pic":"http://p1.pstatp.com/list/190x124/191a00048757f6714455","title":"中国又开工这一重大工程：连美国都造不出来 周边国家沉默不语","type":1},{"date":"99","id":9,"pic":"http://p3.pstatp.com/list/190x124/22c700036549c9b5ff07","title":"中国第一黑老大东北王乔四爷 最终也逃不过法网恢恢","type":1},{"date":"111","id":10,"pic":"http://p3.pstatp.com/list/190x124/22c6000628d79850e6d7","title":"号称中国关系最硬的人，奶奶是慈禧，大伯是光绪，哥哥是宣统！","type":1},{"date":"222","id":11,"pic":"http://p3.pstatp.com/list/190x124/22ca00011911b0a8061c","title":" \n\u201c二婚女和剩女，我该娶哪个啊？求救！\u201d","type":1},{"date":"34323","id":16,"pic":"http://p3.pstatp.com/list/190x124/28820012a2ed870afe9c|http://p9.pstatp.com/list/190x124/28820012a37f01432419","title":"紫谷伊甸园\u201c童心童行\u201d活动开幕啦","type":2},{"date":"","id":17,"pic":"http://s3m.mediav.com/galileo/88d8e16558fa0fd6402fe8daa32f5d44.jpg|http://p3.pstatp.com/list/190x124/289c001c528de064679d","title":"永创佳乐 专业蔬菜大棚/花卉大棚","type":2}]
     */

    private int ret_code;
    private String ret_msg;
    private List<ListBean> list;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * date : 111
         * id : 1
         * pic : http://p1.pstatp.com/large/22c90001cf8b5388ce33
         * title :
         他“秘书圈”的人数规模，赶上了周永康
         * type : 1
         */

        private String date;
        private int id;
        private String pic;
        private String title;
        private String type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
