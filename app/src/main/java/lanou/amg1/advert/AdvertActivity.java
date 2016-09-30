package lanou.amg1.advert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import lanou.amg1.R;
import lanou.amg1.main.MainActivity;
import lanou.amg1.urlall.URLAll;


public class AdvertActivity extends FragmentActivity {

private TextView WelComeActivity_TextView;
    private ImageView WelComeActivity_ImageView;
    int i;
    private My_One_AsyncTask my_one_asyncTask;
    private My_Two_AsyncTask my_two_asyncTask;
    private Boolean execute = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SharedPreferences sharedPreferences = getSharedPreferences(URLAll.SHAREDPREFERENCES_ADAVERACTIVITY,MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferences_edit = sharedPreferences.edit();
        sharedPreferences_edit.commit();
        Boolean ds = sharedPreferences.getBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN,true);

        if(ds){

            Intent intent = new Intent(AdvertActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

            overridePendingTransition(URLAll.ZERO,URLAll.ZERO

            );
            execute = true;
            SharedPreferences sharedPreferences1 = getSharedPreferences(URLAll.SHAREDPREFERENCES_ADAVERACTIVITY,MODE_PRIVATE);
            SharedPreferences.Editor savedInstanceState_edit = sharedPreferences1.edit();
            savedInstanceState_edit.putBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN,false);
            savedInstanceState_edit.commit();


        }


        my_two_asyncTask = new My_Two_AsyncTask();
        my_one_asyncTask = new My_One_AsyncTask();


        setContentView(R.layout.activity_advert);


        WelComeActivity_TextView = (TextView) findViewById(R.id.AdvertActivity_TextView);

        WelComeActivity_ImageView = (ImageView) findViewById(R.id.AdvertActivity_ImageView);
        String str = "http://cdnq.duitang.com/uploads/item/201308/05/20130805143313_3Aedy.thumb.700_0.jpeg";

        if(execute == false){
            my_one_asyncTask.execute(str);
            my_two_asyncTask.execute(URLAll.TWO);
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




    public class My_One_AsyncTask extends AsyncTask<String,String,Bitmap>{


        private Bitmap bitmap;
        private InputStream inputStream;
        private HttpURLConnection httpURLConnection;

        @Override
        protected Bitmap doInBackground(String... params) {


            String dada = params[0];

            try {
                URL url = new URL(dada);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                if(httpURLConnection.getResponseCode() == 200){

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

            Drawable drawable =new BitmapDrawable(bitmap);

            WelComeActivity_ImageView.setBackground(drawable);
            Log.d("My_One_AsyncTask", "进来了额");
        }
    }

    public class My_Two_AsyncTask extends AsyncTask<Integer,Integer,Integer>{


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
            WelComeActivity_TextView.setText("跳过 "+values[0] + "秒");
        }


        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);


            if(integer == -1){

                Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }





        }
    }




}
