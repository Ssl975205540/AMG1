package lanou.amg1.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/10.
 */
public class SearchAtyBean {


    /**
     * returncode : 0
     * message :
     * result : {"pageindex":1,"pagesize":0,"pagecount":1,"rowcount":10,"wordlist":[{"id":0,"name":"一汽-大众奥迪"},{"id":0,"name":"驭胜"},{"id":2429,"name":"逸动"},{"id":78,"name":"雅阁"},{"id":982,"name":"英朗"},{"id":4133,"name":"远景SUV"},{"id":3405,"name":"艾瑞泽5"},{"id":2863,"name":"翼虎"},{"id":474,"name":"远景"},{"id":2228,"name":"驭胜S350"}]}
     */

    private int returncode;
    private String message;
    /**
     * pageindex : 1
     * pagesize : 0
     * pagecount : 1
     * rowcount : 10
     * wordlist : [{"id":0,"name":"一汽-大众奥迪"},{"id":0,"name":"驭胜"},{"id":2429,"name":"逸动"},{"id":78,"name":"雅阁"},{"id":982,"name":"英朗"},{"id":4133,"name":"远景SUV"},{"id":3405,"name":"艾瑞泽5"},{"id":2863,"name":"翼虎"},{"id":474,"name":"远景"},{"id":2228,"name":"驭胜S350"}]
     */

    private ResultBean result;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int pageindex;
        private int pagesize;
        private int pagecount;
        private int rowcount;
        /**
         * id : 0
         * name : 一汽-大众奥迪
         */

        private List<WordlistBean> wordlist;

        public int getPageindex() {
            return pageindex;
        }

        public void setPageindex(int pageindex) {
            this.pageindex = pageindex;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getPagecount() {
            return pagecount;
        }

        public void setPagecount(int pagecount) {
            this.pagecount = pagecount;
        }

        public int getRowcount() {
            return rowcount;
        }

        public void setRowcount(int rowcount) {
            this.rowcount = rowcount;
        }

        public List<WordlistBean> getWordlist() {
            return wordlist;
        }

        public void setWordlist(List<WordlistBean> wordlist) {
            this.wordlist = wordlist;
        }

        public static class WordlistBean {
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
