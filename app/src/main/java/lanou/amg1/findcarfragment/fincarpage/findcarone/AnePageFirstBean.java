package lanou.amg1.findcarfragment.fincarpage.findcarone;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class AnePageFirstBean {


    /**
     * returncode : 0
     * message :
     * result : {"metalist":[{"key":"label","description":"标签","list":[{"value":14,"name":"入门代步"},{"value":13,"name":"都市白领"},{"value":25,"name":"想买SUV"},{"value":21,"name":"撩妹利器"},{"value":17,"name":"硬派越野"},{"value":18,"name":"7座大空间"}]},{"key":"order","description":"排序","list":[{"value":0,"name":"关注高"},{"value":1,"name":"价格低"},{"value":2,"name":"价格高"}]}]}
     */

    private int returncode;
    private String message;
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
        /**
         * key : label
         * description : 标签
         * list : [{"value":14,"name":"入门代步"},{"value":13,"name":"都市白领"},{"value":25,"name":"想买SUV"},{"value":21,"name":"撩妹利器"},{"value":17,"name":"硬派越野"},{"value":18,"name":"7座大空间"}]
         */

        private List<MetalistBean> metalist;

        public List<MetalistBean> getMetalist() {
            return metalist;
        }

        public void setMetalist(List<MetalistBean> metalist) {
            this.metalist = metalist;
        }

        public static class MetalistBean {
            private String key;
            private String description;
            /**
             * value : 14
             * name : 入门代步
             */

            private List<ListBean> list;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private int value;
                private String name;

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
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
}
