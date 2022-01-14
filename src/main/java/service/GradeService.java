//　自分が格納されているフォルダ名
package service;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import beans.Grade;
import beans.Lessons;
import beans.SeatingArrangements;
import beans.Student;
import beans.StudentSeatingArr;
import dao.StudentDAO;
import dao.GradeDAO;
import dao.MemberDAO;

public class GradeService {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public GradeService() {
    }

    // 追加
    // 引数はStudentオブジェクト
    // なんか間違えてる気がする、この引数とか、メソッド名が…
    public void registGrade(Grade grade) {

        // StudentDAOオブジェクト生成
        GradeDAO dao = new GradeDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = dao.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        dao.registGrade(grade, this.connection); // エラー

        // DataBaseとの接続を切断する
        dao.closeConnection(this.connection);

    }

    // 検索 resultを全部studentに代えただけ
    // 検索もだけど、findAllも作らないと…
    public Grade searchGrade(Grade grade) {

        // StudentDAOオブジェクト生成
        GradeDAO dao = new GradeDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = dao.createConnection();

        // 検索する
        grade = dao.searchGrade(grade, this.connection);// ここもだめ

        // DataBaseとの接続を切断する
        dao.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return grade;
    }


    public Grade findGrade(Grade grade) {// クラスの情報をidから持ってくる
        GradeDAO dao = new GradeDAO();
        this.connection = dao.createConnection();
        Grade Grade = dao.searchGrade(grade, connection);
        dao.closeConnection(this.connection); // DataBaseとの接続を切断する
        this.connection = null;// コネクションオブジェクトを破棄する
        return Grade;
    }

    public void registLessons(Lessons lessons, String userId) {
        GradeDAO dao = new GradeDAO();
        this.connection = dao.createConnection();
        dao.registLessons(lessons,userId, connection);
        dao.closeConnection(this.connection); // DataBaseとの接続を切断する
        this.connection = null;// コネクションオブジェクトを破棄する
    }

    public int getLessonId() {
        GradeDAO dao = new GradeDAO();
        this.connection = dao.createConnection();
        int id=dao.getLessonId(connection);
        dao.closeConnection(this.connection); // DataBaseとの接続を切断する
        this.connection = null;// コネクションオブジェクトを破棄する
        return id;
    }

	public List<Lessons> getLessonList(String userId) {
        GradeDAO dao = new GradeDAO();
        this.connection=dao.createConnection();
        List<Lessons> List = dao.getLessonList(userId,connection);
        // dao に反映
        dao.closeConnection(this.connection);
        return List;
	}

    public List<Grade> getGradeList(int id) {
        GradeDAO dao = new GradeDAO();
        this.connection=dao.createConnection();
        System.out.println(id);
        List<Grade> List = dao.getGradeList(id,connection);
        // dao に反映
        dao.closeConnection(this.connection);
        return List;
    }

    public Lessons searchLesson(int id) {
        Lessons lesson=new Lessons();
        GradeDAO dao = new GradeDAO();
        this.connection=dao.createConnection();
         lesson = dao.searchLesson(id,connection);
        // dao に反映
        dao.closeConnection(this.connection);
        return lesson;
	}

    public SeatingArrangements searchSeatingArrangements(int seating_arrangements_id) {
        return null;
    }

    public void DeleteLessonInfo(int id) {
        GradeDAO dao = new GradeDAO();
        this.connection=dao.createConnection();
        dao.deleteGradeInfo(id,connection);
        dao.deleteLessonInfo(id,connection);
        // dao に反映
        dao.closeConnection(this.connection);
    }

    public void UpdateLessonInfo(int id, String date, int periodnum, String comment) {
        GradeDAO dao = new GradeDAO();
        this.connection=dao.createConnection();
        dao.updateLessonInfo(id,date,periodnum,comment,connection);
        // dao に反映
        dao.closeConnection(this.connection);
    }

    public List<Grade> getStudentGradeList(String id) {
        GradeDAO dao = new GradeDAO();
        this.connection=dao.createConnection();
        System.out.println(id);
        List<Grade> List = dao.getStudentGradeList(id,connection);
        // dao に反映
        dao.closeConnection(this.connection);
        return List;
        }

        public Grade getStudentGrade(int id) {
            GradeDAO dao = new GradeDAO();
            this.connection=dao.createConnection();
            System.out.println(id);
            Grade Grade = dao.getStudentGrade(id,connection);
            // dao に反映
            dao.closeConnection(this.connection);
            return Grade;
            }

        public void updateStudentGrade(int red, int blue, int green, String comment, Boolean attendance,int id) {
            GradeDAO dao = new GradeDAO();
            this.connection=dao.createConnection();
            dao.updateStudentGrade(red,blue,green,comment,attendance,id,connection);
            // dao に反映
            dao.closeConnection(this.connection);
               
        }

        public List<Lessons> searchLessonWithDate(String date) {
            GradeDAO dao = new GradeDAO();
            this.connection=dao.createConnection();
            List<Lessons> List = dao.searchLessonWithDate(date,connection);
            // dao に反映
            dao.closeConnection(this.connection);
            return List;
        }

        public List<Lessons> searchLessonWithComment(String searchword) {
            GradeDAO dao = new GradeDAO();
            this.connection=dao.createConnection();
            List<Lessons> List = dao.searchLessonWithComment(searchword,connection);
            // dao に反映
            dao.closeConnection(this.connection);
            return List;
        }
        
        



 

    /*
     * public List<Grade> getGrade() {
     *
     * // StudentDAOオブジェクト生成
     * GradeDAO dao = new GradeDAO();
     *
     * List<Grade> grade = new ArrayList<Grade>();
     *
     * // DataBaseへ接続し、コネクションオブジェクトを生成する
     * this.connection = dao.createConnection();
     *
     * // 検索する
     * //student =
     * grade = dao.findAll(this.connection);
     *
     * // DataBaseとの接続を切断する
     * dao.closeConnection(this.connection);
     *
     * // コネクションオブジェクトを破棄する
     * this.connection = null;
     *
     * return grade;
     * }
     */

}