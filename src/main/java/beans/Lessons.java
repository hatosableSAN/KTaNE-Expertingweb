package beans;

public class Lessons {

    // 属性
    private int id;
    private int seating_arrangements_id;
    private String LessonDate;
    private int periodnum;
    private String Comment;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeating_arrangements_id() {
        return this.seating_arrangements_id;
    }

    public void setSeating_arrangements_id(int seating_arrangements_id) {
        this.seating_arrangements_id = seating_arrangements_id;
    }

    public String getLessonDate() {
        return this.LessonDate;
    }

    public void setLessonDate(String LessonDate) {
        this.LessonDate = LessonDate;
    }

    public int getPeriodnum() {
        return this.periodnum;
    }

    public void setPeriodnum(int periodnum) {
        this.periodnum = periodnum;
    }

    public String getComment() {
        return this.Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

 
    // 初期値を引数に持ったコンストラクタ
    public Lessons(int id,int seating_arrangements_id,String LessonDate,int periodnum,String comment) {

        this.id = id;
        this.seating_arrangements_id	 = seating_arrangements_id;
        this.LessonDate = LessonDate;
        this.periodnum = periodnum;
        this.Comment=comment;
        // this.taikai_kekka = taikai_kekka;

    }



    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public Lessons() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること



}