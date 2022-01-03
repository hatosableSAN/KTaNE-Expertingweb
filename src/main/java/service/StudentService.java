//　自分が格納されているフォルダ名
package service;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import beans.Student;
import dao.StudentDAO;

public class StudentService {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public StudentService() {
    }

    // 追加
    // 引数はStudentオブジェクト
    //なんか間違えてる気がする、この引数とか、メソッド名が…
    public void registStudent(Student student) {

        // StudentDAOオブジェクト生成
        StudentDAO studentDAO = new StudentDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = studentDAO.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        studentDAO.registStudent(student, this.connection); //エラー

        // DataBaseとの接続を切断する
        studentDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

    }

    // 検索　resultを全部studentに代えただけ
    //検索もだけど、findAllも作らないと…
    public Student searchStudent(Student student) {

        // StudentDAOオブジェクト生成
        StudentDAO studentDAO = new StudentDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = studentDAO.createConnection();

        // 検索する
        student = studentDAO.searchStudent(student, this.connection);//ここもだめ

        // DataBaseとの接続を切断する
        studentDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return student;
    }

    public boolean checkStudent(String stu_id) { //児童の番号がすでに使われているかチェック
        boolean result = false;//falseだと使えない

        // StudentDAOオブジェクト生成
        StudentDAO studentDAO = new StudentDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = studentDAO.createConnection();

        // 検索する
        result = studentDAO.checkStudent(stu_id, this.connection);//ここもだめ

        // DataBaseとの接続を切断する
        studentDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return result;
    }

    public List<Student> getStudent() {

        // StudentDAOオブジェクト生成
        StudentDAO studentDAO = new StudentDAO();

        List<Student> student = new ArrayList<Student>();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = studentDAO.createConnection();

        // 検索する
        //student = 
        student = studentDAO.findAll(this.connection);

        // DataBaseとの接続を切断する
        studentDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return student;
    }

    /*public List<Student> getStudentNumber(String student_number[]) {

        // StudentDAOオブジェクト生成
        StudentDAO studentDAO = new StudentDAO();

        List<Student> student = new ArrayList<Student>();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = studentDAO.createConnection();

        // 検索する
        //student = 
        student = studentDAO.findAllNumber(student_number,this.connection);

        // DataBaseとの接続を切断する
        studentDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return student;
    }*/

    public boolean deleteStudent(Student student) {
        boolean result = false;

        // StudentDAOオブジェクト生成
        StudentDAO studentDAO = new StudentDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = studentDAO.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        result = studentDAO.deleteStudent(student, this.connection); //エラー

        // DataBaseとの接続を切断する
        studentDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;
        return result;

    }

    public boolean updateStudent(Student student) {
        boolean result = false;

        // StudentDAOオブジェクト生成
        StudentDAO studentDAO = new StudentDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = studentDAO.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        result = studentDAO.updateStudent(student, this.connection); //エラー

        // DataBaseとの接続を切断する
        studentDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return result;

    }

}