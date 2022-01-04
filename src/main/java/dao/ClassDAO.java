//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
//import model.ShiftBeans;
//import model.ColorBeans;
import utility.DriverAccessor;
import java.time.LocalDateTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import model.*;

import utility.DriverAccessor;

//import beans.Student;
import beans.ClassDef;

public class ClassDAO extends DriverAccessor {
    /*
     * // 属性
     *
     * // データベースの接続先アドレスを静的変数として記述
     * private final static String DRIVER_URL =
     * "jdbc:mysql://localhost:3306/schoolapp_db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";
     *
     * // データベース接続ドライバの名前を静的変数として記述
     * // Mysql5.系
     * // private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
     * // Mysql8.系
     * private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
     *
     * // データベースのユーザー名 （デフォルトではroot）
     * private final static String USER_NAME = "root";
     *
     * // データベースのユーザーのパスワード (デフォルトでは設定なし)
     * private final static String PASSWORD = "";
     *
     * // データベースとの接続を行う
     * // データベースの接続情報を持ったConnectionオブジェクトを返す
     * public Connection createConnection() {
     * try {
     * Class.forName(DRIVER_NAME);
     * Connection con = DriverManager.getConnection(DRIVER_URL, USER_NAME,
     * PASSWORD);
     * return con;
     * } catch (ClassNotFoundException e) {
     * System.out.println("Can't Find JDBC Driver.\n");
     * } catch (SQLException e) {
     * System.out.println("Connect Error.\n");
     * }
     * return null;
     * }
     *
     * // Connectionオブジェクトを使って、データベースとの接続を切断する
     * // 引数Connectionオブジェクト
     * public void closeConnection(Connection con) {
     *
     * try {
     * con.close();
     * } catch (Exception ex) {
     * }
     * }
     */
    // 情報をデータベースに登録する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public int registClass(ClassDef classdef, Connection connection) {

        try {

            // SQLコマンド
            String sql = "insert into classes (name,year,user_id) values(?, ?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            // PreparedStatement stmt = connection.prepareStatement(sql,
            // java.sql.Statement.RETURN_GENERATED_KEYS);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, classdef.getClass_name());
            // int stu_gender = Integer.parseInt(student.getStudent_gender());
            stmt.setInt(2, classdef.getClass_year());
            stmt.setString(3, classdef.getClass_user());
            // stmt.setString(4, student.getStudent_user());
            // stmt.setString(5, result.getTaikai_kekka());
            // ResultSet rs = statement.executeQuery();
            stmt.executeUpdate();// この場所
            ResultSet rs = stmt.getGeneratedKeys();
            rs.first();
            int class_id = 0;
            class_id = rs.getInt(1);// 怒られた

            /*
             * if (rs.next()) {
             * class_id = rs.getInt(1);//ここで怒られる emptyなのに置けないよって
             * }
             */
            rs.close();

            // stmt.executeUpdate();
            System.out.println("class_id" + class_id);
            return class_id;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return 0; // class_idにしたら怒られた

        } finally {
        }
    }

    public void registClassOnly(ClassDef classdef, Connection connection) { //クラス情報だけ登録、メンバーなし

        try {

            // SQLコマンド
            String sql = "insert into classes (name,year,user_id) values(?, ?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);
            // PreparedStatement stmt = connection.prepareStatement(sql,
            // java.sql.Statement.RETURN_GENERATED_KEYS);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, classdef.getClass_name());
            stmt.setInt(2, classdef.getClass_year());
            stmt.setString(3, classdef.getClass_user());
            // ResultSet rs = statement.executeQuery();
            stmt.executeUpdate();// この場所
            //ResultSet rs = stmt.getGeneratedKeys();
            //rs.first();
            //int class_id = 0;
            //class_id = rs.getInt(1);

            //rs.close();

            //stmt.executeUpdate();
            //System.out.println("class_id" + class_id);
            //return;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            //return; // class_idにしたら怒られた

        } finally {
        }
    }

    // 検索する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public ClassDef searchClass(ClassDef classdef, Connection connection) {

        try {

            // SQLコマンド
            // String sql = "select * from classes where id = '" + classdef.getClass_name()
            // + "'";
            String sql = "select * from classes where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, classdef.getClass_id());
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            // Statement stmt = connection.createStatement();
            // ResultSet rs = stmt.executeQuery(sql);
            // System.out.println("取得した文字列は" + rs.getString("taikai_name") + "です！");

            rs.first();

            /*
             * if(rs.getString("taikai_name") == null) {
             * result.setPlayer_name("0");
             * result.setTaikai_name("0");
             * result.setTaikai_level("0");
             * result.setTaikai_kekka("0");
             *
             * }else {
             * result.setTaikai_name(rs.getString("taikai_name"));
             * result.setTaikai_level(rs.getString("taikai_level"));
             * result.setTaikai_kekka(rs.getString("taikai_kekka"));
             * }
             */

            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する

            //
            // classdef.setClass_id(rs.getString("id"));
            classdef.setClass_name(rs.getString("name"));
            classdef.setClass_year(rs.getInt("year"));
            classdef.setClass_user(rs.getString("user_id"));
            System.out.println("取得した文字列は" + classdef + "です！");
            // 終了処理
            statement.close();
            rs.close();

            // Studentオブジェクトを返す
            return classdef;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;

        } finally {
        }
    }

    /*
     * public class StudentDAO extends DriverAccessor {
     * //こっちは引数があるから、検索に引っかかったものを探す
     * public List<Student> findAll(Student student, Connection connection) {
     * String sql =
     * "SELECT * FROM students WHERE student_id IN(Select workplace_id FROM workplace WHERE user_id = ?) ORDER BY start_date ASC, end_date ASC"
     * ;
     * try {
     * PreparedStatement statement = connection.prepareStatement(sql);
     * statement.setString(1, Account.getLoginId());
     * ResultSet rs = statement.executeQuery();
     * List<ShiftBeans> SbList = new ArrayList<ShiftBeans>();
     * while (rs.next()) {
     * ShiftBeans returnSb = new ShiftBeans();
     * returnSb.setShiftId(rs.getInt("shift_id"));
     * returnSb.setStartDate(rs.getString("start_date"));
     * returnSb.setEndDate(rs.getString("end_date"));
     * returnSb.setWorkplaceId(rs.getInt("workplace_id"));
     * returnSb.setMemo(rs.getString("memo"));
     * SbList.add(returnSb);
     * }
     * statement.close();
     * rs.close();
     *
     * return SbList;
     * } catch (SQLException e) {
     * e.printStackTrace();
     * return null;
     * }
     * }
     */

    // public class SampleDao extends DriverAccessor {
    // こっちは引数が無いから、全部取ってくる
    public List<ClassDef> findAll(Connection connection) {
        String findAll = "select * from classes";
        try {
            PreparedStatement statement = connection.prepareStatement(findAll);
            ResultSet rs = statement.executeQuery();
            List<ClassDef> classList = new ArrayList<ClassDef>();
            while (rs.next()) {
                ClassDef classdef = new ClassDef();
                classdef.setClass_id(rs.getInt("id"));
                classdef.setClass_name(rs.getString("name"));
                classdef.setClass_year(rs.getInt("year"));
                // student.setStudent_gender(gender);
                classdef.setClass_user(rs.getString("user_id"));
                classList.add(classdef);
            }
            statement.close();
            rs.close();

            return classList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ClassDef> findMyAll(String user_id, Connection connection) {
        // 特定userIdの作成した全クラスの情報を取得
        String sql = "select * from classes where user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user_id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            List<ClassDef> classList = new ArrayList<ClassDef>();
            while (rs.next()) {
                ClassDef classdef = new ClassDef();
                classdef.setClass_id(rs.getInt("id"));
                classdef.setClass_name(rs.getString("name"));
                classdef.setClass_year(rs.getInt("year"));
                classdef.setClass_user(rs.getString("user_id"));
                classList.add(classdef);
            }
            statement.close();
            rs.close();

            return classList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ClassDef> findOtherAll(String user_id, Connection connection) {
        // 特定userIdの"以外"の作成した全クラスの情報を取得
        String sql = "select * from classes where user_id != ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user_id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            List<ClassDef> classList = new ArrayList<ClassDef>();
            while (rs.next()) {
                ClassDef classdef = new ClassDef();
                classdef.setClass_id(rs.getInt("id"));
                classdef.setClass_name(rs.getString("name"));
                classdef.setClass_year(rs.getInt("year"));
                classdef.setClass_user(rs.getString("user_id"));
                classList.add(classdef);
            }
            statement.close();
            rs.close();

            return classList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // }
    public boolean updateClass(ClassDef classdef, Connection connection) {

        try {

            // SQLコマンド
            String sql = "update classes set name=?,year=? where id = '" + classdef.getClass_id() + "'";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            // PreparedStatement stmt = connection.prepareStatement(sql,
            // java.sql.Statement.RETURN_GENERATED_KEYS);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, classdef.getClass_name());
            // int stu_gender = Integer.parseInt(student.getStudent_gender());
            stmt.setInt(2, classdef.getClass_year());
            // stmt.setString(3, classdef.getClass_user());
            // stmt.setString(4, student.getStudent_user());
            // stmt.setString(5, result.getTaikai_kekka());
            // ResultSet rs = statement.executeQuery();
            stmt.executeUpdate();// この場所
            // ResultSet rs = stmt.getGeneratedKeys();
            // rs.first();
            // int class_id = 0;
            // class_id = rs.getInt(1);//怒られた

            /*
             * if (rs.next()) {
             * class_id = rs.getInt(1);//ここで怒られる emptyなのに置けないよって
             * }
             */
            // rs.close();

            // stmt.executeUpdate();
            // System.out.println("class_id"+class_id);
            return true;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return false; // class_idにしたら怒られた

        } finally {
        }
    }

    public boolean deleteClassMember(ClassDef classdef, Connection connection) {

        try {
            // SQLコマンド
            String sql = "delete FROM classes where id=? LIMIT 1";
            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);
            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setInt(1, classdef.getClass_id());
            System.out.println(stmt);
            stmt.executeUpdate();
            stmt.close();
            return true;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return false;

        } finally {
        }
    }

    public boolean deleteClass(ClassDef classdef, Connection connection) {

        try {
            // SQLコマンド
            String sql = "delete FROM classes where id=? LIMIT 1";
            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);
            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setInt(1, classdef.getClass_id());
            System.out.println(stmt);
            stmt.executeUpdate();
            stmt.close();
            return true;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return false;

        } finally {
        }
    }

}
