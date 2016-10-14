package lanou.amg1.advert;

import android.app.Activity;
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
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;
import lanou.amg1.main.MainActivity;
import lanou.amg1.urlall.URLAll;


public class AdvertActivity extends FragmentActivity {

    private TextView WelComeActivity_TextView;
    private ImageView WelComeActivity_ImageView;
    int i;
    String url;
    private My_One_AsyncTask my_one_asyncTask;
    private My_Two_AsyncTask my_two_asyncTask;
    private Boolean execute = false;
    private WebView advertActivity_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SharedPreferences sharedPreferences = getSharedPreferences(URLAll.SHAREDPREFERENCES_ADAVERACTIVITY, MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferences_edit = sharedPreferences.edit();
        sharedPreferences_edit.commit();
        Boolean ds = sharedPreferences.getBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN, true);

        if (ds) {

            Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
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


        WelComeActivity_TextView = (TextView) findViewById(R.id.AdvertActivity_TextView);
        advertActivity_webview = (WebView) findViewById(R.id.advertActivity_webview);


        WelComeActivity_ImageView = (ImageView) findViewById(R.id.AdvertActivity_ImageView);

        WelComeActivity_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        if (isNetworkAvailable(AdvertActivity.this)) {
            GsonRequest<AdvertBean> onePageFragmentBeanGsonRequest = new GsonRequest<AdvertBean>(URLAll.WELCOME_URL, AdvertBean.class, new Response.Listener<AdvertBean>() {
                @Override
                public void onResponse(final AdvertBean bean) {
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


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            VolleySingleton.getInstance().addRequest(onePageFragmentBeanGsonRequest);


        } else {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(3000);
                        Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }).start();

        }


        WelComeActivity_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                if (my_two_asyncTask != null && my_two_asyncTask.getStatus() != AsyncTask.Status.FINISHED)
                    my_two_asyncTask.cancel(true);
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

            WelComeActivity_ImageView.setBackground(drawable);
            WelComeActivity_TextView.setText("跳过 " + 3 + "秒");

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
            WelComeActivity_TextView.setText("跳过 " + values[0] + "秒");
        }


        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);


            if (integer == -1) {

                Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }


        }
    }


    public boolean isNetworkAvailable(Activity activity) {
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
