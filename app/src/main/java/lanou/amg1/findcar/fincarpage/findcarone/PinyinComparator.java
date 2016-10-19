package lanou.amg1.findcar.fincarpage.findcarone;

import java.util.Comparator;

import lanou.amg1.bean.ListViewBean;

/**
 * @author xiaanming
 */
public class PinyinComparator implements Comparator<ListViewBean> {

    public int compare(ListViewBean o1, ListViewBean o2) {
        if (o1.getLetter().equals("@")
                || o2.getLetter().equals("#")) {
            return -1;
        } else if (o1.getLetter().equals("#")
                || o2.getLetter().equals("@")) {
            return 1;
        } else {
            return o1.getLetter().compareTo(o2.getLetter());
        }
    }

}
