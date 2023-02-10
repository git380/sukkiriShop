package exception;

import java.util.List;

public class ZaikoException extends Exception {
    //インスタンス変数
    List<String> msgList; //在庫が足りなかった場合のメッセージリスト

    //コンストラクタ
    public ZaikoException() {
    }

    public ZaikoException(String msg, List<String> msgList) {
        super(msg);
        this.msgList = msgList;
    }

    public ZaikoException(String msg, Throwable except) {
        super(msg, except);
    }

    //setter & getter 手で入力せずEclipseの機能を使うこと
    public List<String> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<String> msgList) {
        this.msgList = msgList;
    }
}