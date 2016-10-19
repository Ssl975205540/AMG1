package lanou.amg1.recommend;

import android.support.v4.app.Fragment;

/**
 * Created by dllo on 16/10/18.
 */
public class FragBean {

    private Fragment fragment;

    private String title;

    private int anInt;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
