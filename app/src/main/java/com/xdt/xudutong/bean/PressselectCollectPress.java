package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\7\5 0005.
 */
//查看个人中心用户收藏新闻
public class PressselectCollectPress {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询新闻成功
     * content : {"data":[{"COLLECTTBLID":263,"EDITOR":"高云飞","CREATE_TIME":"2017-04-06 18:56:18",
     * "URLPAD":"http://m.21xc.com/content/201704/06/c369299.html","UPDATE_TIME":"2017-04-06 18:56:32","STATUS":1,
     * "COL":"首页顶部主标题","COLID":73,"VERSION":2,"CONTENT":"<h1 class=\"ui-article-title\" style='font-size: 20px;color:
     * #000;line-height: 30px;padding: 10px 0 5px 0;font-family: Microsoft YaHei;text-align: left;text-shadow: 1px 1px 1px #fff;' >
     * 许昌10秒形象宣传片亮相央视一套 <\/h1> <div class=\"ui-article-infos\">  <time> 时间：2017-04-06 07:30:37 <\/time>  <span class=\"source\">
     *     来源： <\/span>  <span class=\"source\"> 作者： <\/span>  <var class=\"js-font big-fontSize\"><\/var> <\/div><div id=\"content-show\"
     *     class=\"mar-t-20\">  <p>   <!--enpcontent-->   <!--enpcontent--><\/p> <p style=\"text-align: center\"><img src=\"http://m.21xc.com/pic/201
     *     704/06/d0505bc9-19ea-4261-8b4f-41295f2c2f30.jpg\" style=\"max-width: 100%;\"><\/p> <p style=\"text-align: center\"><img src=\"http://
     *     m.21xc.com/pic/201704/06/ebae734e-7f3a-4ede-b65c-24a95b270c4d.jpg\" style=\"max-width: 100%;\"><\/p> <p style=\"text-align: center;\">
     *         图为宣传片视频截图。<\/p> <p>市民宋丽华每天中午习惯边吃午饭边收看CCTV-1定期播出的《新闻30分》。4月1日和4月4日，她在收看该节目时，惊喜地发现
     *         ，许昌的城市形象宣传片在央视1套午间时段播出啦！这无形中增强了她身为许昌人的自豪感。她情不自禁地打电话给远在西安的亲友，请他们以后注意收看。
     *         <\/p> <p>4月4日12：25，记者注意到，我市三国文化旅游周城市主题广告亮相央视：画面依次呈现了曹操雕像、春秋楼、西湖公园、文峰塔等城市文化符号。伴
     *         随着\u201c闻听三国事，每欲到许昌。中国许昌\u201d的画外音，镜头最后定格在夜色下壮美的智慧门。画面下方是一行文字：热烈祝贺许昌第十一届三国文化
     *         旅游周4月28日隆重开幕。该城市形象广告时长10秒，画面气派秀美，彰显了曹魏故都厚重的历史文化和优美的自然风光。<\/p> <p>据了解，许昌第十一届三国文
     *         化旅游周将于4月28日至5月3日举行，主题为\u201c曹魏风&nbsp;三国情&nbsp;许昌行\u201d，共举办10大项活动。许昌10秒形象宣传片于4月1日正式登陆央视《新
     *         闻30分》，宣传活动将持续至5月4日。具体播出时间为4月份：1、4、7、9、10、13、16、19、22、23、25、28、29日；5月份：1、4日。CCTV-1与CCTV-13《新闻30
     *         分》（12：00-12：30）栏目并机播出。广告插播栏目中12：25左右播出。<\/p> <p><br style=\"text-align: left;\"><\/p> <!--/enpcontent--> <!--enpprop
     *         erty <articleid>369299<\/articleid><date>2017-04-06 07:30:37:0<\/date><author><\/author><title>许昌10秒形象宣传片亮相央视一套<\/title><keywor
     *         d><\/keyword><subtitle><\/subtitle><introtitle><\/introtitle><siteid>1<\/siteid><nodeid>73<\/nodeid><nodename>首页顶部主标题<\/nodename><no
     *         desearchname>首页顶部主标题<\/nodesearchname><picurl><\/picurl>/enpproperty-->  <!--/enpcontent-->  <p><\/p>  <!--<p style=\"text-align: ce
     *         nter; text-indent: 0;\"><img src=\"http://upload.21xc.com/2016/0215/1455497950525.jpg\" border=\"0\" width=\"500\" alt=\"02150502\" height
     *         =\"350\" style=\"border: 0px none;\" /><\/p><p style=\"text-align: center;\">招聘会现场，很多求职者进行咨询。<\/p><p><strong>返乡务工人员比
     *         往年多三成<\/strong><\/p><p>2月14日9时许，该中心二楼挤满了前来求职的人。&ldquo;才第一天人就这么多，今年不愁招不到工人了。&rdquo;河南华丽纸业包
     *         装有限公司的负责人说。<\/p><p>现场很多求职者都是返乡务工人员。&ldquo;累了，不想在外面漂泊了。前几年总觉得许昌的工作待遇低，想在外面多挣点儿钱。
     *         今年父母不想让我去外地，所以我就准备留下来找工作。&rdquo;孙莹莹大学毕业后就在深圳一家公司打工。由于公司不景气，她打算回许
     *         昌找工作。<\/p><p>该中心工作人员介绍，像孙莹莹这样的返乡务工人员今年比往年多三成。<\/p><p><strong>待遇提高、工作环境提升<\/strong><\/p><p><st
     *         rong>是他们返乡工作的主要原因<\/strong><\/p><p>很多大型企业参加了这次招聘会。其中，许昌远东传动轴股份有限公司一次招工50名，月薪在2800元&mdash
     *         ;4200元。<\/p><p>&ldquo;这次我们招收的都是普通工人，待遇比往年有所提高。还不到半个小时，就已经有20多人前来应聘。&rdquo;该公司总经理付东安说，&
     *         ldquo;这次我们招收的也有返乡务工人员。他们去年在沿海城市打工，月薪也就三四千元。而我们许昌很多企业近两年都提高了待遇，所以很多人选择回来找工作。
     *         &rdquo;<\/p><p>在招聘会现场，记者发现很多夫妻一起来找工作，王洪诗一家就是这样。&ldquo;我老公是山东人，原来在一汽上班。但由于我父母是&lsquo;空
     *         巢&rsquo;老人，身体不好，他就放弃了以前的工作，跟我回许昌找工作。&rdquo;王洪诗的妻子说，她回来后才发现，很多大型企业的待遇都很高。<\/p><p>&ldquo
     *         ;刚开始，我很不情愿回来找工作，怕待遇比以前差。&rdquo;王洪诗说，待遇好、工作环境好是他们选择工作的主要标准。<\/p><p><strong>连续一个月的招聘会<\
     *         /strong><\/p><p><strong>将提供7669个就业岗位<\/strong><\/p><p>在连续一个月的招聘会中，包括许昌远东传动轴股份有限公司、河南瑞贝卡发制品股份有限公司、许继低压电器有限公司、许昌浪潮云数信息技术有限公司、许昌阳光实业集团等大型企业在内的198家企业将提供教育、金融、电子商务、医药、物流、机械制造等就业岗位7669个。<\/p><p>除此之外，该中心今天会举办我市2016年残疾人专场招聘会，现场还有专业的创业指导。<\/p><p>需要注意的是，2月18日，江苏省苏州市、太仓市16家名优企业将提供972个优质就业岗位，参加我市跨区域就业合作招聘会。<\/p>--> <\/div>"
     *         ,"ROW_ID":1,"ID":369299,"URL":"http://www.21xc.com/content/201704/06/c369299.html","PUBTIME":"2017-04-06 07:30:37","TITLE":"许昌形象宣传片亮相央视一套"}]}
     */

    private int flag;
    private String code;
    private String desc;
    private ContentBean content;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public class ContentBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * COLLECTTBLID : 263
             * EDITOR : 高云飞
             * CREATE_TIME : 2017-04-06 18:56:18
             * URLPAD : http://m.21xc.com/content/201704/06/c369299.html
             * UPDATE_TIME : 2017-04-06 18:56:32
             * STATUS : 1
             * COL : 首页顶部主标题
             * COLID : 73
             * VERSION : 2
             * CONTENT : <h1 class="ui-article-title" style='font-size: 20px;color: #000;line-height: 30px;padding: 10px 0 5px 0;font-family: Microsoft YaHei;text-align: left;text-shadow: 1px 1px 1px #fff;' > 许昌10秒形象宣传片亮相央视一套 </h1> <div class="ui-article-infos">  <time> 时间：2017-04-06 07:30:37 </time>  <span class="source"> 来源： </span>  <span class="source"> 作者： </span>  <var class="js-font big-fontSize"></var> </div><div id="content-show" class="mar-t-20">  <p>   <!--enpcontent-->   <!--enpcontent--></p> <p style="text-align: center"><img src="http://m.21xc.com/pic/201704/06/d0505bc9-19ea-4261-8b4f-41295f2c2f30.jpg" style="max-width: 100%;"></p> <p style="text-align: center"><img src="http://m.21xc.com/pic/201704/06/ebae734e-7f3a-4ede-b65c-24a95b270c4d.jpg" style="max-width: 100%;"></p> <p style="text-align: center;">图为宣传片视频截图。</p> <p>市民宋丽华每天中午习惯边吃午饭边收看CCTV-1定期播出的《新闻30分》。4月1日和4月4日，她在收看该节目时，惊喜地发现，许昌的城市形象宣传片在央视1套午间时段播出啦！这无形中增强了她身为许昌人的自豪感。她情不自禁地打电话给远在西安的亲友，请他们以后注意收看。</p> <p>4月4日12：25，记者注意到，我市三国文化旅游周城市主题广告亮相央视：画面依次呈现了曹操雕像、春秋楼、西湖公园、文峰塔等城市文化符号。伴随着“闻听三国事，每欲到许昌。中国许昌”的画外音，镜头最后定格在夜色下壮美的智慧门。画面下方是一行文字：热烈祝贺许昌第十一届三国文化旅游周4月28日隆重开幕。该城市形象广告时长10秒，画面气派秀美，彰显了曹魏故都厚重的历史文化和优美的自然风光。</p> <p>据了解，许昌第十一届三国文化旅游周将于4月28日至5月3日举行，主题为“曹魏风&nbsp;三国情&nbsp;许昌行”，共举办10大项活动。许昌10秒形象宣传片于4月1日正式登陆央视《新闻30分》，宣传活动将持续至5月4日。具体播出时间为4月份：1、4、7、9、10、13、16、19、22、23、25、28、29日；5月份：1、4日。CCTV-1与CCTV-13《新闻30分》（12：00-12：30）栏目并机播出。广告插播栏目中12：25左右播出。</p> <p><br style="text-align: left;"></p> <!--/enpcontent--> <!--enpproperty <articleid>369299</articleid><date>2017-04-06 07:30:37:0</date><author></author><title>许昌10秒形象宣传片亮相央视一套</title><keyword></keyword><subtitle></subtitle><introtitle></introtitle><siteid>1</siteid><nodeid>73</nodeid><nodename>首页顶部主标题</nodename><nodesearchname>首页顶部主标题</nodesearchname><picurl></picurl>/enpproperty-->  <!--/enpcontent-->  <p></p>  <!--<p style="text-align: center; text-indent: 0;"><img src="http://upload.21xc.com/2016/0215/1455497950525.jpg" border="0" width="500" alt="02150502" height="350" style="border: 0px none;" /></p><p style="text-align: center;">招聘会现场，很多求职者进行咨询。</p><p><strong>返乡务工人员比往年多三成</strong></p><p>2月14日9时许，该中心二楼挤满了前来求职的人。&ldquo;才第一天人就这么多，今年不愁招不到工人了。&rdquo;河南华丽纸业包装有限公司的负责人说。</p><p>现场很多求职者都是返乡务工人员。&ldquo;累了，不想在外面漂泊了。前几年总觉得许昌的工作待遇低，想在外面多挣点儿钱。今年父母不想让我去外地，所以我就准备留下来找工作。&rdquo;孙莹莹大学毕业后就在深圳一家公司打工。由于公司不景气，她打算回许昌找工作。</p><p>该中心工作人员介绍，像孙莹莹这样的返乡务工人员今年比往年多三成。</p><p><strong>待遇提高、工作环境提升</strong></p><p><strong>是他们返乡工作的主要原因</strong></p><p>很多大型企业参加了这次招聘会。其中，许昌远东传动轴股份有限公司一次招工50名，月薪在2800元&mdash;4200元。</p><p>&ldquo;这次我们招收的都是普通工人，待遇比往年有所提高。还不到半个小时，就已经有20多人前来应聘。&rdquo;该公司总经理付东安说，&ldquo;这次我们招收的也有返乡务工人员。他们去年在沿海城市打工，月薪也就三四千元。而我们许昌很多企业近两年都提高了待遇，所以很多人选择回来找工作。&rdquo;</p><p>在招聘会现场，记者发现很多夫妻一起来找工作，王洪诗一家就是这样。&ldquo;我老公是山东人，原来在一汽上班。但由于我父母是&lsquo;空巢&rsquo;老人，身体不好，他就放弃了以前的工作，跟我回许昌找工作。&rdquo;王洪诗的妻子说，她回来后才发现，很多大型企业的待遇都很高。</p><p>&ldquo;刚开始，我很不情愿回来找工作，怕待遇比以前差。&rdquo;王洪诗说，待遇好、工作环境好是他们选择工作的主要标准。</p><p><strong>连续一个月的招聘会</strong></p><p><strong>将提供7669个就业岗位</strong></p><p>在连续一个月的招聘会中，包括许昌远东传动轴股份有限公司、河南瑞贝卡发制品股份有限公司、许继低压电器有限公司、许昌浪潮云数信息技术有限公司、许昌阳光实业集团等大型企业在内的198家企业将提供教育、金融、电子商务、医药、物流、机械制造等就业岗位7669个。</p><p>除此之外，该中心今天会举办我市2016年残疾人专场招聘会，现场还有专业的创业指导。</p><p>需要注意的是，2月18日，江苏省苏州市、太仓市16家名优企业将提供972个优质就业岗位，参加我市跨区域就业合作招聘会。</p>--> </div>
             * ROW_ID : 1
             * ID : 369299
             * URL : http://www.21xc.com/content/201704/06/c369299.html
             * PUBTIME : 2017-04-06 07:30:37
             * TITLE : 许昌形象宣传片亮相央视一套
             */

            private int COLLECTTBLID;
            private String EDITOR;
            private String CREATE_TIME;
            private String URLPAD;
            private String UPDATE_TIME;
            private int STATUS;
            private String COL;
            private int COLID;
            private int VERSION;
            private String CONTENT;
            private int ROW_ID;
            private int ID;
            private String URL;
            private String PUBTIME;
            private String TITLE;

            public int getCOLLECTTBLID() {
                return COLLECTTBLID;
            }

            public void setCOLLECTTBLID(int COLLECTTBLID) {
                this.COLLECTTBLID = COLLECTTBLID;
            }

            public String getEDITOR() {
                return EDITOR;
            }

            public void setEDITOR(String EDITOR) {
                this.EDITOR = EDITOR;
            }

            public String getCREATE_TIME() {
                return CREATE_TIME;
            }

            public void setCREATE_TIME(String CREATE_TIME) {
                this.CREATE_TIME = CREATE_TIME;
            }

            public String getURLPAD() {
                return URLPAD;
            }

            public void setURLPAD(String URLPAD) {
                this.URLPAD = URLPAD;
            }

            public String getUPDATE_TIME() {
                return UPDATE_TIME;
            }

            public void setUPDATE_TIME(String UPDATE_TIME) {
                this.UPDATE_TIME = UPDATE_TIME;
            }

            public int getSTATUS() {
                return STATUS;
            }

            public void setSTATUS(int STATUS) {
                this.STATUS = STATUS;
            }

            public String getCOL() {
                return COL;
            }

            public void setCOL(String COL) {
                this.COL = COL;
            }

            public int getCOLID() {
                return COLID;
            }

            public void setCOLID(int COLID) {
                this.COLID = COLID;
            }

            public int getVERSION() {
                return VERSION;
            }

            public void setVERSION(int VERSION) {
                this.VERSION = VERSION;
            }

            public String getCONTENT() {
                return CONTENT;
            }

            public void setCONTENT(String CONTENT) {
                this.CONTENT = CONTENT;
            }

            public int getROW_ID() {
                return ROW_ID;
            }

            public void setROW_ID(int ROW_ID) {
                this.ROW_ID = ROW_ID;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
                this.URL = URL;
            }

            public String getPUBTIME() {
                return PUBTIME;
            }

            public void setPUBTIME(String PUBTIME) {
                this.PUBTIME = PUBTIME;
            }

            public String getTITLE() {
                return TITLE;
            }

            public void setTITLE(String TITLE) {
                this.TITLE = TITLE;
            }
        }
    }
}
