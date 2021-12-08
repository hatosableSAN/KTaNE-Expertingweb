//  自分が格納されているフォルダ名
package beans;

public class User {

    // 属性
    private String id = null; // UserID
    private String password = null; // パスワード
    private String password2 = null; // パスワード確認

    // 初期値を引数に持ったコンストラクタ
    public User(String id, String password, String password2) {

        this.id = id;
        this.password = password;
        this.password2 = password2;
        
    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public User() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPassword2() {
        return this.password2;
    }

}
