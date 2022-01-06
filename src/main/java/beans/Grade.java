package beans;

public class Grade {

    // 属性
    private int id;
    private String StudentId = null;
    private int lesson_id = 0;
    private boolean attendance = true; // クラスのメンバー？
    private int red = 1; // 初期値を引数に持ったコンストラクタ
    private int green = 1; // 初期値を引数に持ったコンストラクタ
    private int blue = 1; // 初期値を引数に持ったコンストラクタ
    private String comment = null; // 初期値を引数に持ったコンストラクタ
    private int seat = 0; // 初期値を引数に持ったコンストラクタ
    private String UserId = null;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return this.StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public int getLessonId() {
        return this.lesson_id;
    }

    public void setLessonId(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public boolean isAttendance() {
        return this.attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public int getRed() {
        return this.red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return this.green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return this.blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSeat() {
        return this.seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getUserId() {
        return this.UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public Grade(int id,String StudentId,int lesson_id,Boolean attendance,int red,int green,int blue,String comment,int seat,String UserId){
    // 属性
    this.id=id;
    this.StudentId = StudentId;
    this.lesson_id = lesson_id;
    this.attendance = attendance; // クラスのメンバー？
    this.red = red; // 初期値を引数に持ったコンストラクタ
    this.green = green; // 初期値を引数に持ったコンストラクタ
    this.blue = blue; // 初期値を引数に持ったコンストラクタ
    this.comment = comment; // 初期値を引数に持ったコンストラクタ
    this.seat = seat; // 初期値を引数に持ったコンストラクタ
    this.UserId = UserId;

    }



    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public Grade() {
    }



}