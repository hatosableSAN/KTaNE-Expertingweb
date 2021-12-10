package beans;

public class Student {

    // 属性
	private String student_id = null; // 生徒番号
    private String student_name = null; // 生徒名
    private String student_gender = null; // 生徒の性別
    private String student_user = null; // 生徒を登録した教員ユーザ
    //private String taikai_kekka = null; // 対戦結果

    // 初期値を引数に持ったコンストラクタ
    public Student(String student_id, String student_name, String student_gender, String student_user) {

        this.student_id = student_id;
        this.student_name = student_name;
        this.student_gender = student_gender;
        this.student_user = student_user;
        //this.taikai_kekka = taikai_kekka;

    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public Student() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setStudent_gender(String student_gender) {
        this.student_gender = student_gender;
    }

    public void setStudent_user(String student_user) {
        this.student_user = student_user;
    }

    /*public void setTaikai_kekka(String taikai_kekka) {
        this.taikai_kekka = taikai_kekka;
    }*/

    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public String getStudent_id() {
        return this.student_id;
    }

    public String getStudent_name() {
        return this.student_name;
    }

    public String getStudent_gender() {
        return this.student_gender;
    }

    public String getStudent_user() {
        return this.student_user;
    }

    /*public String getTaikai_kekka() {
        return this.taikai_kekka;
    }*/

}
