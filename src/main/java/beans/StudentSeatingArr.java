package beans;

public class StudentSeatingArr {
    private int id = -1; // 座席id
    private int seatingArrangementId = -1; // 座席配置ID
    private String studentId = null; // 生徒ID
    private int seat = -1; // 座席番号

    // 初期値を引数に持ったコンストラクタ
    public StudentSeatingArr(int id, int seatingArrangementId, String studentId, int seat) {

        this.id = id;
        this.seatingArrangementId = seatingArrangementId;
        this.studentId = studentId;
        this.seat = seat;

    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public StudentSeatingArr() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatingArrangementId() {
        return seatingArrangementId;
    }

    public void setSeatingArrangementId(int seatingArrangementId) {
        this.seatingArrangementId = seatingArrangementId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

}
