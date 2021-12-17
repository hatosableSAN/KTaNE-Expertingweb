package beans;

public class ClassDef {

    // 属性
    private int class_id = 0; // クラスID
    private String class_name = null; // クラス名
    private int class_year = 0; // 年度
    // private String class_member = null; // クラスのメンバー？
    private String class_user = null; // 対戦結果

    // 初期値を引数に持ったコンストラクタ
    public ClassDef(int class_id, String class_name, int class_year, String class_user) {

        this.class_id = class_id;
        this.class_name = class_name;
        this.class_year = class_year;
        this.class_user = class_user;
        // this.taikai_kekka = taikai_kekka;

    }

    public ClassDef(String class_name, int class_year, String class_user) {
        this.class_name = class_name;
        this.class_year = class_year;
        this.class_user = class_user;
    }

    public ClassDef(int class_id) {
        this.class_id = class_id;
    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public ClassDef() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public void setClass_year(int class_year) {
        this.class_year = class_year;
    }

    public void setClass_user(String class_user) {
        this.class_user = class_user;
    }

    /*
     * public void setTaikai_kekka(String taikai_kekka) {
     * this.taikai_kekka = taikai_kekka;
     * }
     */

    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public int getClass_id() {
        return this.class_id;
    }

    public String getClass_name() {
        return this.class_name;
    }

    public int getClass_year() {
        return this.class_year;
    }

    public String getClass_user() {
        return this.class_user;
    }

    /*
     * public String getTaikai_kekka() {
     * return this.taikai_kekka;
     * }
     */

}