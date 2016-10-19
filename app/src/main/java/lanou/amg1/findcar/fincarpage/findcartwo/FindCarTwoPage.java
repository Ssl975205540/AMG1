package lanou.amg1.findcar.fincarpage.findcartwo;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import lanou.amg1.R;
import lanou.amg1.base.BaseFragment;

/**
 * Created by dllo on 16/9/27.
 */
public class FindCarTwoPage extends BaseFragment {

    //该程序模拟天成长度为100的数组
    private int[] data = new int[100];
    protected boolean isVisible;
    int hasData = 0;
    private boolean isReady = false;
    // 记录ProgressBar的完成进度
    int progressStatus = 0;
    private static ProgressBar bar;
    private WebView finCar_TwoPage_WebView;

    @Override
    protected void initData() {

        finCar_TwoPage_WebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        finCar_TwoPage_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        WebSettings settings = finCar_TwoPage_WebView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected int setContentView() {

        return R.layout.findcar_twopage;
    }

    //模拟一个耗时的操作
    private int doWork() {
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasData;
    }
    private int doWork(int i) {

        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasData;
    }




    @Override
    protected void initViews() {

        bar = (ProgressBar) findById(R.id.finCar_TwoPage_ProgressBar);
        finCar_TwoPage_WebView = findById(R.id.finCar_TwoPage_WebView);
    }

    @Override
    protected void initListeners() {


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {

            if(isVisible == false){
                setHandler();
                isVisible = true;


            }



        } else {

            isVisible = false;




        }
    }


    private void setHandler(){


        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    bar.setProgress(progressStatus);
                }


                if(progressStatus == 80){

                    finCar_TwoPage_WebView.loadUrl("http://m.che168.com/dalian/list/?sourcename=mainapp&safe=0&carsafe=1&pvareaid=102254");



                }
                if(hasData == 100){

                    bar.setVisibility(View.GONE);
                }

            }
        };
        // 启动线程来执行任务
        new Thread() {
            public void run() {
                while (progressStatus < 100) {
                    // 获取耗时的完成百分比


                    Message m = new Message();

                    if(progressStatus == 80){

                        progressStatus = doWork(80);

                        m.what = 0x111;
                        // 发送消息到Handler
                        handler.sendMessage(m);
                    }else {
                        progressStatus = doWork();
                        m.what = 0x111;
                        // 发送消息到Handler
                        handler.sendMessage(m);
                    }



                }


            }
        }.start();
    }

}
