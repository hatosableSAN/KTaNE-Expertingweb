package beans;

public class SeatingArrangements {

    private int id = -1; // 座席配置ID
    private int classId = -1; // クラスID
    private String createdDate = null; // 作成日
    private String startDate = null; // 開始期間
    private String endDate = null; // 終了期間
    private String name = null; // 座席配置名
    private String userId = null; // ユーザーID

    public SeatingArrangements(int id, int classId, String createdDate, String startDate, String endDate, String name,
            String userId) {
        this.id = -1;// 座席配置ID
        this.classId = -1; // クラスID
        this.createdDate = null; // 作成日
        this.startDate = null; // 開始期間
        this.endDate = null; // 終了期間
        this.name = null; // 座席配置名
        this.userId = null; // ユーザーID
    }

    public SeatingArrangements() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
