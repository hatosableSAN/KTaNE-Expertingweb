//　自分が格納されているフォルダ名
package service;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import beans.Student;
import beans.ClassDef;
import dao.StudentDAO;
import dao.ClassDAO;
import dao.MemberDAO;

public class ClassService {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public ClassService() {
    }

    // 追加
    // 引数はStudentオブジェクト
    // なんか間違えてる気がする、この引数とか、メソッド名が…
    public void registClass(ClassDef classdef, String student_member[]) {

        // StudentDAOオブジェクト生成
        ClassDAO classDAO = new ClassDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = classDAO.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        int class_id = 0;
        class_id = classDAO.registClass(classdef, this.connection); // エラー

        // DataBaseとの接続を切断する
        classDAO.closeConnection(this.connection);

        // ここからメンバー登録
        MemberDAO memberDAO = new MemberDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = memberDAO.createConnection();

        for (int i = 0; i < student_member.length; i++) {
            memberDAO.registMember(student_member[i], class_id, this.connection);
        }

        // StudentオブジェクトをDataBaseに登録する
        // memberDAO.registMember(classdef, this.connection);

        // DataBaseとの接続を切断する
        memberDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

    }

    // 検索 resultを全部studentに代えただけ
    // 検索もだけど、findAllも作らないと…
    public ClassDef searchClass(ClassDef classdef) {

        // StudentDAOオブジェクト生成
        ClassDAO classDAO = new ClassDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = classDAO.createConnection();

        // 検索する
        classdef = classDAO.searchClass(classdef, this.connection);// ここもだめ

        // DataBaseとの接続を切断する
        classDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return classdef;
    }

    public List<ClassDef> getAllClass() {// クラスの情報をすべて持ってくる
        ClassDAO classDAO = new ClassDAO();
        this.connection = classDAO.createConnection();
        List<ClassDef> ClassDefList = classDAO.findAll(connection);
        classDAO.closeConnection(this.connection); // DataBaseとの接続を切断する
        this.connection = null; // コネクションオブジェクトを破棄する
        return ClassDefList;
    }

    public List<ClassDef> getAllMyClass(String UserId) {// 自身のクラスの情報をすべて持ってくる
        ClassDAO classDAO = new ClassDAO();
        this.connection = classDAO.createConnection();
        List<ClassDef> ClassDefList = classDAO.findMyAll(UserId, connection);
        classDAO.closeConnection(this.connection); // DataBaseとの接続を切断する
        this.connection = null; // コネクションオブジェクトを破棄する
        return ClassDefList;
    }

    public List<ClassDef> getAllOtherClass(String UserId) {// 他人のクラスの情報をすべて持ってくる
        ClassDAO classDAO = new ClassDAO();
        this.connection = classDAO.createConnection();
        List<ClassDef> ClassDefList = classDAO.findOtherAll(UserId, connection);
        classDAO.closeConnection(this.connection); // DataBaseとの接続を切断する
        this.connection = null; // コネクションオブジェクトを破棄する
        return ClassDefList;
    }

    public ClassDef findClass(ClassDef classdef) {// クラスの情報をidから持ってくる //これsearchClassと被ってる？
        ClassDAO classDAO = new ClassDAO();
        this.connection = classDAO.createConnection();
        ClassDef ClassDef = classDAO.searchClass(classdef, connection);
        classDAO.closeConnection(this.connection); // DataBaseとの接続を切断する
        this.connection = null;// コネクションオブジェクトを破棄する
        return ClassDef;
    }

    public List<Student> getAllClassmember(ClassDef classDef) {// クラスのidからメンバーの生徒リスト持ってくる
        MemberDAO memberDAO = new MemberDAO();
        this.connection = memberDAO.createConnection();
        List<Student> StudentList = memberDAO.searchClass(classDef, connection);
        memberDAO.closeConnection(this.connection); // DataBaseとの接続を切断する
        this.connection = null;// コネクションオブジェクトを破棄する
        return StudentList;
    }

    public boolean updateClass(ClassDef classdef, String student_member[]) { //クラス変更
        boolean result = false;

        // StudentDAOオブジェクト生成
        ClassDAO classDAO = new ClassDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = classDAO.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        //int class_id = 0;
        //class_id = 
        result = classDAO.updateClass(classdef, this.connection); //id撮ってくる意味ないよね、classdefで分かるし

        // DataBaseとの接続を切断する
        classDAO.closeConnection(this.connection);

        // ここからメンバー更新
        MemberDAO memberDAO = new MemberDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = memberDAO.createConnection();
        System.out.println("student_member.length= "+student_member.length);

        //for (int i = 0; i < student_member.length; i++) {
            result = memberDAO.updateMember(student_member, classdef, this.connection);
        //}

        // StudentオブジェクトをDataBaseに登録する
        // memberDAO.registMember(classdef, this.connection);

        // DataBaseとの接続を切断する
        memberDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return result;

    }

    /*
     * public List<ClassDef> getClassDef() {
     *
     * // StudentDAOオブジェクト生成
     * ClassDAO classDAO = new ClassDAO();
     *
     * List<ClassDef> classdef = new ArrayList<ClassDef>();
     *
     * // DataBaseへ接続し、コネクションオブジェクトを生成する
     * this.connection = classDAO.createConnection();
     *
     * // 検索する
     * //student =
     * classdef = classDAO.findAll(this.connection);
     *
     * // DataBaseとの接続を切断する
     * classDAO.closeConnection(this.connection);
     *
     * // コネクションオブジェクトを破棄する
     * this.connection = null;
     *
     * return classdef;
     * }
     */

}