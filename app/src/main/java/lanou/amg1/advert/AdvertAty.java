package lanou.amg1.advert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.jpush.android.api.JPushInterface;
import lanou.amg1.R;
import lanou.amg1.bean.AdvertBean;
import lanou.amg1.tools.requset.GsonRequest;
import lanou.amg1.tools.requset.VolleySingleton;
import lanou.amg1.main.MainActivity;
import lanou.amg1.tools.URLAll;


public class AdvertAty extends FragmentActivity {

    private TextView advertaty_tv;
    private ImageView advertaty_iv;
    int i;
    String url;
    private My_One_AsyncTask my_one_asyncTask;
    private My_Two_AsyncTask my_two_asyncTask;
    private Boolean execute = false;
    private WebView advertaty_wbv;
    private Thread thread;
    private Thread thread1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SharedPreferences sharedPreferences = getSharedPreferences(URLAll.SHAREDPREFERENCES_ADAVERACTIVITY, MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferences_edit = sharedPreferences.edit();
        sharedPreferences_edit.commit();
        Boolean ds = sharedPreferences.getBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN, true);

        if (ds) {

            Intent intent = new Intent(AdvertAty.this, MainActivity.class);
            startActivity(intent);
            finish();

            overridePendingTransition(URLAll.ZERO, URLAll.ZERO

            );
            execute = true;
            SharedPreferences sharedPreferences1 = getSharedPreferences(URLAll.SHAREDPREFERENCES_ADAVERACTIVITY, MODE_PRIVATE);
            SharedPreferences.Editor savedInstanceState_edit = sharedPreferences1.edit();
            savedInstanceState_edit.putBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN, false);
            savedInstanceState_edit.commit();


        }


        my_two_asyncTask = new My_Two_AsyncTask();
        my_one_asyncTask = new My_One_AsyncTask();


        setContentView(R.layout.activity_advert);


        advertaty_tv = (TextView) findViewById(R.id.advertaty_tv);
        advertaty_wbv = (WebView) findViewById(R.id.advertaty_wbv);


        advertaty_iv = (ImageView) findViewById(R.id.advertaty_iv);

        advertaty_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        if (isNetworkAvailable(AdvertAty.this)) {
            GsonRequest<AdvertBean> onePageFragmentBeanGsonRequest = new GsonRequest<AdvertBean>(URLAll.WELCOME_URL, AdvertBean.class, new Response.Listener<AdvertBean>() {



                @Override
                public void onResponse(final AdvertBean bean) {


                    try {
                        url = bean.getResult().getAd().getImgad().getOpenurl();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    Thread.sleep(1000);
                                    if (execute == false) {
                                        my_one_asyncTask.execute(bean.getResult().getAd().getImgad().getImgurl());
                                        my_two_asyncTask.execute(URLAll.TWO);
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (IllegalStateException e){

                                }

                            }
                        }).start();

                    }catch (NullPointerException e){

                        thread1 = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    Thread.sleep(2000);
                                    Intent intent = new Intent(AdvertAty.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                        thread1.start();
                    }






                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            VolleySingleton.getInstance().addRequest(onePageFragmentBeanGsonRequest);


        } else {

            thread = new Thread(new Runnable() {
                 @Override
                 public void run() {

                     try {
                         Thread.sleep(2000);
                         Intent intent = new Intent(AdvertAty.this, MainActivity.class);
                         startActivity(intent);
                         finish();

                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }


                 }
             });

            thread.start();

        }


        advertaty_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdvertAty.this, MainActivity.class);
                startActivity(intent);
                finish();
                if (my_two_asyncTask != null && my_two_asyncTask.getStatus() != AsyncTask.Status.FINISHED)
                    my_two_asyncTask.cancel(true);


                if(thread != null){

                    thread.interrupt();

                }
                if(thread1 != null){

                    thread1.interrupt();

                }

            }
        });

    }


    public class My_One_AsyncTask extends AsyncTask<String, String, Bitmap> {


        private Bitmap bitmap;
        private InputStream inputStream;
        private HttpURLConnection httpURLConnection;

        @Override
        protected Bitmap doInBackground(String... params) {


            String dada = params[0];

            try {
                URL url = new URL(dada);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                if (httpURLConnection.getResponseCode() == 200) {

                    inputStream = httpURLConnection.getInputStream();

                    bitmap = BitmapFactory.decodeStream(inputStream);


                }

                inputStream.close();


                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return bitmap;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            Drawable drawable = new BitmapDrawable(bitmap);

            advertaty_iv.setBackground(drawable);
            advertaty_tv.setText("跳过 " + 3 + "秒");

        }
    }

    public class My_Two_AsyncTask extends AsyncTask<Integer, Integer, Integer> {


        @Override
        protected Integer doInBackground(Integer... params) {


            for (i = params[0]; i >= 0; i--) {

                try {
                    Thread.sleep(1000);

                    publishProgress(i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            return i;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            advertaty_tv.setText("跳过 " + values[0] + "秒");
        }


        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);


            if (integer == -1) {

                Intent intent = new Intent(AdvertAty.this, MainActivity.class);
                startActivity(intent);
                finish();
            }


        }
    }


    public boolean isNetworkAvailable(android.app.Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

}
