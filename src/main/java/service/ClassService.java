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
        classDAO.registClass(classdef, this.connection); // エラー

        // DataBaseとの接続を切断する
        classDAO.closeConnection(this.connection);

        // ここからメンバー登録
        MemberDAO memberDAO = new MemberDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = memberDAO.createConnection();

        for (int i = 0; i < student_member.length; i++) {
            memberDAO.registMember(student_member[i], this.connection);
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
        // DataBaseとの接続を切断する
        classDAO.closeConnection(this.connection);
        // コネクションオブジェクトを破棄する
        this.connection = null;
        return ClassDefList;
    }

    public ClassDef idGetClass(ClassDef classdef) {// クラスの情報をidから持ってくる
        ClassDAO classDAO = new ClassDAO();
        this.connection = classDAO.createConnection();
        ClassDef ClassDef = classDAO.searchClass(classdef, connection);
        // DataBaseとの接続を切断する
        classDAO.closeConnection(this.connection);
        // コネクションオブジェクトを破棄する
        this.connection = null;
        return ClassDef;
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