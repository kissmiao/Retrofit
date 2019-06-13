package com.hongliang.retrofitdemo.httputil.bean;

import java.io.Serializable;

public class ResponseData<T> extends BaseBean implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;
    /**
     * head : {"msg":"成功","code":0}
     * success : true
     * body : {"intruction":"http://203.0.104.29:9907/woyoou/instruction?appId=mosi5123123123","timeid":"6b28ffa4-5677-426d-a023-c30dc18b52f8","contact":"12312312312","proId":"163","wechatId":"aaa","readme":"mosi5","wechatName":"aaa","orgId":"281","wechatImg":"","authBankCardType":"2"}
     */
    private HeadEntity head;
    private boolean success;
    private T body;

    public void setHead(HeadEntity head) {
        this.head = head;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public HeadEntity getHead() {
        return head;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getBody() {
        return body;
    }


    public class HeadEntity {
        /**
         * msg : 成功
         * code : 0
         */
        private String msg;
        private int code;

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public int getCode() {
            return code;
        }
    }


}
                         