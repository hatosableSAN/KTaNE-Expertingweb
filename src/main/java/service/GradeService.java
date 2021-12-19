//　自分が格納されているフォルダ名
package service;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import beans.Grade;
import beans.Student;
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
    public void registGrade(Grade grade, String student_member[]) {

        // StudentDAOオブジェクト生成
        GradeDAO dao = new GradeDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = dao.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        dao.registGrade(grade, this.connection); // エラー

        // DataBaseとの接続を切断する
        dao.closeConnection(this.connection);

        // ここからメンバー登録
        MemberDAO memberDAO = new MemberDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = memberDAO.createConnection();

        for (int i = 0; i < student_member.length; i++) {
            memberDAO.registMember(student_member[i], this.connection);
        }

        // StudentオブジェクトをDataBaseに登録する
        // memberDAO.registMember(grade, this.connection);

        // DataBaseとの接続を切断する
        memberDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

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