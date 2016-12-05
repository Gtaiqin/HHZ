package com.hhzmy.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RadioGroup;

import com.hhzmy.hhz.R;

import static com.hhzmy.hhz.R.id.details_tab1;

/**
 * Created by w9072 on 2016/11/16.
 */

public class details_det_frag extends Fragment {

    private View view;
    private RadioGroup details_tab;
    private WebView detalis_det_webview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details_det_frag_layout, null, false);
        infoview();
        setweb1();
        setfragment();
        return view;
    }


    private void infoview() {
        detalis_det_webview = (WebView) view.findViewById(R.id.detalis_det_webview);
    }

    //点击选项卡替换页面
    public void setfragment() {
        details_tab = (RadioGroup) view.findViewById(R.id.details_tab);
        details_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case details_tab1:
                        setweb1();
                        break;
                    case R.id.details_tab2:
                        //detalis_det_webview.destroy();
                        setWEB("http://product.suning.com/pds-web/product/graphicDetailsApp/0000000000/102295661/10051/R9000413/1.html");
                        break;
                    case R.id.details_tab3:
                        //detalis_det_webview.destroy();
                        setWEB("http://product.suning.com/pds-web/product/graphicSaleApp/0000000000/102295661.html");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void setWEB(String url) {
        detalis_det_webview.getSettings().setJavaScriptEnabled(true);
        detalis_det_webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        detalis_det_webview.loadUrl(url);
    }


    private void setweb1() {
        detalis_det_webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String htmlData = "\"<div moduleId='R9000413_2' moduleName='关联推荐'><p><img src2=\"http://image3.suning.cn//uimg/cms/img/147826176955030691.png\" alt=\"\" usemap=\"#Map\" border=\"0\" /> <map name=\"Map\"> <area coords=\"7,9,258,106\" shape=\"rect\" href=\"http://quan.suning.com/lqzx_recommend.do?activityId=201611040000995005&activitySecretKey=MEbbXk0c8XqddzTYKQbAJy3e\" target=\"_blank\" /> <area coords=\"274,6,523,104\" shape=\"rect\" href=\"http://quan.suning.com/lqzx_recommend.do?activityId=201611040000995031&activitySecretKey=P8ktM6fkGs8u31ZrHx9MXwVN\" target=\"_blank\" /> <area coords=\"534,6,770,100\" shape=\"rect\" href=\"http://quan.suning.com/lqzx_recommend.do?activityId=201611040000995044&activitySecretKey=ucGPhCAOtCTKTbHJSVQ7iYIE\" target=\"_blank\" /> </map></p> <!-- 母婴用品互联3.10 --> <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#fff799\"> <tbody> <tr bgcolor=\"#fff799\"> <td align=\"center\"><a href=\"http://cuxiao.suning.com/myzcqjh1101.html\"><img src2=\"http://image3.suning.cn//uimg/cms/img/147841973948824711.jpg\" alt=\"\" /></a></td> </tr> <tr bgcolor=\"#fff799\"> <td align=\"center\"> </td> </tr> <tr> <td bgcolor=\"#fff799\"> <table border=\"0\" align=\"center\" bgcolor=\"#fffcdc\"> <tbody> <tr> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/945004789.html\" target=\"_blank\"><img src2=\"http://image2.suning.cn/uimg/b2c/newcatentries/0000000000-000000000945004789_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">雀巢 超级能恩3段800g*2桶 </span></td> <td ><a href=\"http://product.suning.com/0000000000/945004789.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164130.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/102410015.html\" target=\"_blank\"><img src2=\"http://image5.suning.cn/uimg/b2c/newcatentries/0000000000-000000000102410015_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">花王 妙而舒特大号XL38片 学步裤 </span></td> <td ><a href=\"http://product.suning.com/0000000000/102410015.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164138.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/122736070.html\" target=\"_blank\"><img src2=\"http://image1.suning.cn/uimg/b2c/newcatentries/0000000000-000000000122736070_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">贝贝沃尔 芝士鳕鱼肠84g</span></td> <td ><a href=\"http://product.suning.com/0000000000/122736070.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164144.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/102586393.html\" target=\"_blank\"><img src2=\"http://image4.suning.cn/uimg/b2c/newcatentries/0000000000-000000000102586393_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">帮宝适 超薄干爽婴儿纸尿裤加大号XL128片</span></td> <td ><a href=\"http://product.suning.com/0000000000/102586393.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164150.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> <tr> <td bgcolor=\"#fff799\"> <table border=\"0\" align=\"center\" bgcolor=\"#fffcdc\"> <tbody> <tr> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/104392679.html\" target=\"_blank\"><img src2=\"http://image2.suning.cn/uimg/b2c/newcatentries/0000000000-000000000104392679_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">宝得适 儿童安全座椅超级百变王 白金版</span></td> <td ><a href=\"http://product.suning.com/0000000000/104392679.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164150.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/107211174.html\" target=\"_blank\"><img src2=\"http://image3.suning.cn/uimg/b2c/newcatentries/0000000000-000000000107211174_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">AUBY 澳贝 运动系列 乖乖小鸭 益智玩具</span></td> <td ><a href=\"http://product.suning.com/0000000000/107211174.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164206.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/127713074.html\" target=\"_blank\"><img src2=\"http://image1.suning.cn/uimg/b2c/newcatentries/0000000000-000000000127713074_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">良良 麻棉祛味隔尿垫（超大号）绿色</span></td> <td ><a href=\"http://product.suning.com/0000000000/127713074.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164212.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/125942525.html\" target=\"_blank\"><img src2=\"http://image3.suning.cn/uimg/b2c/newcatentries/0000000000-000000000125942526_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">好孩子Goodbaby进口床实木无漆多功能婴儿床 </span></td> <td ><a href=\"http://product.suning.com/0000000000/105546656.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164150.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> <tr> <td bgcolor=\"#fff799\"> </td> </tr> </tbody> </table></div><div moduleId='R9000413_3' moduleName='商品详情'><p><span style=\"font-size: 14px;\"><strong> 产品特点:</strong></span></p> <p><span style=\"color: #000000; font-size: 14px;\">1.全面棉柔外层,全新加倍柔软,更透气,就连腰部胶带粘贴区也采用透气绵软材质,宝宝每寸肌肤都能开心呼吸,屁屁不生闷气,整<br />体全彩图案设计,就像宝宝可爱的小裤裤,丰富的图案为宝宝提供更具启发性的视觉刺激.<br />2.全方位吸水体,前后穿都可以,双层超吸收,尿量更多也不怕,瞬间吸水超薄层,吸收更迅速,表层干爽不回渗.<br />3.跨下立体环绕剪裁,双向穿都轻松,怎么穿都服帖.<br />4.独特的侧边伸缩腰围,不论前穿后穿,任何姿势都服帖合身,不松脱,不下滑.<br />5.巧撕圆弧魔术贴,魔术贴变聪明了,好撕好粘更顺手,圆弧造型,不会刮伤小宝宝细微肌肤.<br />6.天然呵护层,表层的每根纤维都添加天然植物精华,经临床实验证实对预防及抑制尿布症的发生具有成效.</span></p> <p><span style=\"font-size: 14px;\"><span style=\"font-family: arial, helvetica, sans-serif;\"><span style=\"color: #000;\"><img src2=\"http://image.suning.cn/uimg/sop/commodity/101709020266969895015290_x.jpg\" alt=\"\" /><img src2=\"http://image.suning.cn/uimg/sop/commodity/100070592712410814056500_x.jpg\" alt=\"\" /><img src2=\"http://image.suning.cn/uimg/sop/commodity/212614477377587906459020_x.jpg\" alt=\"\" /><img src2=\"http://image.suning.cn/uimg/sop/commodity/134331860838867935418580_x.jpg\" alt=\"\" /></span></span></span><strong><span style=\"font-size: 26px;\"><strong><strong><span style=\"font-size: 16px;\"><span style=\"font-family: arial, helvetica, sans-serif;\"><strong><span style=\"font-size: 14px;\"><strong><img src2=\"http://image.suning.cn/uimg/BTC/PDI/102295665_20140928164732.jpg\" alt=\"\" /></strong></span></strong></span></span></strong> </strong></span></strong></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; text-indent: 0cm; mso-layout-grid-align: none; mso-char-indent-count: 0;\"> <span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><strong>花王妙而舒常见问题：</strong></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><strong>1、若不小心将妙而舒纸尿裤和衣物一起洗涤了，如何去除衣物和洗衣机里的附着物？<span lang=\"EN-US\" style=\"display: none; mso-hide: all;\">    </span></strong></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">万一不小心将纸尿裤放入洗衣机里洗，会有胶冻状的物质以及纸屑附着在衣服及洗衣机内。胶冻状物是吸收<span lang=\"EN-US\"><br /> </span>体使用的高分子吸收物，在吸收了尿液或洗衣机里的水之后膨胀起来的物质。纸状物则是纸尿裤的材料，也<span lang=\"EN-US\"><br /> </span>就是纸浆及无纺布。<span lang=\"EN-US\">  </span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">附着在衣服上的有效清除方法<span lang=\"EN-US\">]</span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">在脱水后，干燥前用刷子等刷掉。也可以使用黏着胶带等去除。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">干燥后，还有残渣粘在衣服上的话，请再次用刷子刷。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">然后，若有时间，可以在脱水后或干燥之后，再次水洗。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">附着在洗衣机内时的清除方法<span lang=\"EN-US\">]</span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">先把脏物网中的脏物清除，使用纸巾把洗衣机内部擦拭干净。然后，再次放水冲洗干净。<span lang=\"EN-US\"> </span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">对身体的影响<span lang=\"EN-US\">]</span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">残留在衣服上的高分子吸收体、纸浆和无纺布，直接接触肌肤也不会有任何伤害。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"b??','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">附着在洗衣机内时的清除方法<span lang=\"EN-US\">]</span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">先把脏物网中的脏物清除，使用纸巾把洗衣机内部擦拭干净。然后，再次放水冲洗干净。<span lang=\"EN-US\"> </span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">对身体的影响<span lang=\"EN-US\">]</span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">残留在衣服上的高分子吸收体、纸浆和无纺布，直接接触肌肤也不会有任何伤害。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"be软雅黑','sans-serif'; color: red;\"><strong>2、什么时候该换大一号的妙而舒纸尿裤？</strong></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">1)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、大腿及腰部觉得太紧、有勒痕时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">2)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、腰贴位置已经贴在最外侧时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">3)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、纸尿裤裤裆变短或腰围在肚脐以下时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">4)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、尿尿便便的量增多，容易外漏时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">5)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、宝宝体重超过尺寸表中的体重标示时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">    </span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">出现上述情况时，请选用大一号的纸尿裤。</span></span></span></span></span></span></p></div><div moduleId='R9000413_4' moduleName='商品参数'><p>?</p></div><div moduleId='R9000413_5' moduleName='商品展示'><p>?</p></div>\"";
        htmlData = htmlData.replaceAll("&", "");
        htmlData = htmlData.replaceAll("quot;", "\"");
        htmlData = htmlData.replaceAll("lt;", "<");
        htmlData = htmlData.replaceAll("gt;", ">");
        detalis_det_webview.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null);
    }
}
