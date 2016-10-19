package lanou.amg1.tools.requset;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import lanou.amg1.base.MyApp;


/**
 * Created by dllo on 16/9/20.
 */
public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    //把请求队列 放到单例类里 这样整个项目就只有一个
    private RequestQueue mRequestQueue;

    //内存泄漏 -- 占据内存空间context
    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApp.getContext());
    }

    //提高效率 -- 等待
    public static VolleySingleton getInstance() {
        if (volleySingleton == null) {

            synchronized (VolleySingleton.class) {

                if (volleySingleton == null) {
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }

    //类文件 对象 jvm 类加载器 一个类只加载一次 static属于类本身


    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    /**
     * 把请求 加到请求队列里去
     *
     * @param request 各种网络请求
     */
    public void addRequest(Request request){
        mRequestQueue.add(request);

    }
}
