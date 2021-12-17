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
import beans.*;

import utility.DriverAccessor;

//import beans.Student;
import beans.ClassDef;

public class MemberDAO extends DriverAccessor {

    // 属性

    // データベースの接続先アドレスを静的変数として記述
    /*
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
    public void registMember(String student_id, Connection connection) {

        try {

            // SQLコマンド
            String sql1 = "select count(*) from classes";
            String sql2 = "insert into members (class_id,student_id) values(?, ?)";

            // SQLコマンドの実行
            // PreparedStatement stmt1 = connection.prepareStatement(sql1);
            Statement stmt1 = connection.createStatement();
            ResultSet rs = stmt1.executeQuery(sql1);// rsに結果が入っている
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            // System.out.println(rs);
            rs.first();
            int count = rs.getInt(1);// 分からんけど1でcount値取得できた

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt2.setInt(1, count);
            // int stu_gender = Integer.parseInt(student.getStudent_gender());
            stmt2.setString(2, student_id);
            // stmt.setString(3, classdef.getClass_user());
            // stmt.setString(4, student.getStudent_user());
            // stmt.setString(5, result.getTaikai_kekka());

            // stmt.executeUpdate();
            stmt1.close();
            rs.close();
            stmt2.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    // 検索する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    /*
     * public ClassDef searchClass(ClassDef classdef, Connection connection) {
     *
     * try {
     *
     * // SQLコマンド
     * String sql = "select * from classes where id = '" + classdef.getClass_name()+
     * "'";
     *
     * // SQLのコマンドを実行する
     * // 実行結果はrsに格納される
     * Statement stmt = connection.createStatement();
     * ResultSet rs = stmt.executeQuery(sql);
     * //System.out.println("取得した文字列は" + rs.getString("taikai_name") + "です！");
     *
     * rs.first();
     *
     * /*if(rs.getString("taikai_name") == null) {
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
     *
     * // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
     *
     * //
     * //classdef.setClass_id(rs.getString("id"));
     * classdef.setClass_name(rs.getString("name"));
     * classdef.setClass_year(rs.getInt("year"));
     * classdef.setClass_user(rs.getString("user_id"));
     *
     * // 終了処理
     * stmt.close();
     * rs.close();
     *
     * // Studentオブジェクトを返す
     * return classdef;
     *
     * } catch (SQLException e) {
     *
     * // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
     * e.printStackTrace();
     * return null;
     *
     * } finally {
     * }
     * }
     */
    public List<Student> searchClass(ClassDef classdef, Connection connection) {

        try {

            // SQLコマンド
            String sql = "select * from members where class_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, classdef.getClass_id());
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            List<Student> studentList = new ArrayList<Student>();
            while (rs.next()) {
                Student student = new Student();
                student.setStudent_id(rs.getString("student_id"));
                studentList.add(student);
            }
            if (studentList.size() > 0) {// メンバーが0人の時エラーが出るため
                System.out.println(studentList.get(0).getStudent_id() + ":" + studentList.get(0).getStudent_name());
            }
            statement.close();
            rs.close();

            for (int i = 0; i < studentList.size(); i++) {
                sql = "select * from students where id = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, studentList.get(i).getStudent_id());
                System.out.println(statement);
                rs = statement.executeQuery();
                rs.first();
                Student student = new Student();
                student.setStudent_id(rs.getString("id"));
                student.setStudent_name(rs.getString("name"));
                student.setStudent_gender(rs.getInt("gender"));
                student.setStudent_user(rs.getString("user_id"));
                studentList.set(i, student);
            }
            statement.close();
            rs.close();
            return studentList;

        } catch (SQLException e) {
            e.printStackTrace();// エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
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
     * ResultSet resultSet = statement.executeQuery();
     * List<ShiftBeans> SbList = new ArrayList<ShiftBeans>();
     * while (resultSet.next()) {
     * ShiftBeans returnSb = new ShiftBeans();
     * returnSb.setShiftId(resultSet.getInt("shift_id"));
     * returnSb.setStartDate(resultSet.getString("start_date"));
     * returnSb.setEndDate(resultSet.getString("end_date"));
     * returnSb.setWorkplaceId(resultSet.getInt("workplace_id"));
     * returnSb.setMemo(resultSet.getString("memo"));
     * SbList.add(returnSb);
     * }
     * statement.close();
     * resultSet.close();
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
            ResultSet resultSet = statement.executeQuery();
            List<ClassDef> classList = new ArrayList<ClassDef>();
            while (resultSet.next()) {
                ClassDef classdef = new ClassDef();
                // classdef.setClass_id(resultSet.getInt("id"));
                classdef.setClass_name(resultSet.getString("name"));
                classdef.setClass_year(resultSet.getInt("year"));
                // student.setStudent_gender(gender);
                classdef.setClass_user(resultSet.getString("user_id"));
                classList.add(classdef);
            }
            statement.close();
            resultSet.close();

            return classList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // }

}
