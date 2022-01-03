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
//import utility.DriverAccessor;

import beans.Student;

public class StudentDAO extends DriverAccessor {
/*
    // 属性

    // データベースの接続先アドレスを静的変数として記述
    private final static String DRIVER_URL = "jdbc:mysql://localhost:3306/Schoolapp_db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";

    // データベース接続ドライバの名前を静的変数として記述
    // Mysql5.系
    // private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    // Mysql8.系
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    // データベースのユーザー名 （デフォルトではroot）
    private final static String USER_NAME = "root";

    // データベースのユーザーのパスワード (デフォルトでは設定なし)
    private final static String PASSWORD = "";

    // データベースとの接続を行う
    // データベースの接続情報を持ったConnectionオブジェクトを返す
    public Connection createConnection() {
        try {
            Class.forName(DRIVER_NAME);
            Connection con = DriverManager.getConnection(DRIVER_URL, USER_NAME, PASSWORD);
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Can't Find JDBC Driver.\n");
        } catch (SQLException e) {
            System.out.println("Connect Error.\n");
        }
        return null;
    }

    // Connectionオブジェクトを使って、データベースとの接続を切断する
    // 引数Connectionオブジェクト
    public void closeConnection(Connection con) {

        try {
            con.close();
        } catch (Exception ex) {
        }
    }
*/
    // 情報をデータベースに登録する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public void registStudent(Student student, Connection connection) {

        try {

            // SQLコマンド
            String sql = "insert into students (id,gender,name,user_id) values(?, ?, ?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);//nullになる…

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, student.getStudent_id());
            //int stu_gender = Integer.parseInt(student.getStudent_gender());
            stmt.setInt(2, student.getStudent_gender());
            stmt.setString(3, student.getStudent_name());
            stmt.setString(4, student.getStudent_user());
            //stmt.setString(5, result.getTaikai_kekka());


            stmt.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    // 検索する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public Student searchStudent(Student student, Connection connection) {

        try {

            // SQLコマンド
            //if(!student.getStudent_id().isEmpty()){
                String sql = "select * from students where id = '" + student.getStudent_id()+ "' or name = '"+ student.getStudent_name()+"'";
            //}
            //String sql = "select * from students where id = '" + student.getStudent_id()+ "'";

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println("取得した文字列は" + rs.getString("taikai_name") + "です！");

            rs.first();

            /*if(rs.getString("taikai_name") == null) {
            	result.setPlayer_name("0");
            	result.setTaikai_name("0");
                result.setTaikai_level("0");
                result.setTaikai_kekka("0");

            }else {
            	result.setTaikai_name(rs.getString("taikai_name"));
                result.setTaikai_level(rs.getString("taikai_level"));
                result.setTaikai_kekka(rs.getString("taikai_kekka"));
            }*/

            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
            
            //
            student.setStudent_id(rs.getString("id"));
            student.setStudent_name(rs.getString("name"));
            //student.setStudent_gender(rs.getString("gender"));
            /*String gender = String.valueOf(rs.getInt("gender"));
                switch(gender){
                 case "1": gender="男";
                          break;
                 case "2": gender="女";
                          break;
                 case "3": gender="その他";
                          break;
                }*/
                //gender、intにしました
            student.setStudent_gender(rs.getInt("gender"));
            student.setStudent_user(rs.getString("user_id"));

            // 終了処理
            stmt.close();
            rs.close();

            // Studentオブジェクトを返す
            return student;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;

        } finally {
        }
    }

    public boolean checkStudent(String stu_id, Connection connection) {

        try {

            // SQLコマンド
            //if(!student.getStudent_id().isEmpty()){
                String sql = "select * from students where id = '" + stu_id +"'";
            //}
            //String sql = "select * from students where id = '" + student.getStudent_id()+ "'";

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println("取得した文字列は" + rs.getString("taikai_name") + "です！");

            rs.first();

            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
            
            if(rs.getString("id") != null){
                return false;//使ってはいけない。もう使用されているから
            }
            //student.setStudent_id(rs.getString("id"));
            //student.setStudent_name(rs.getString("name"));
            //student.setStudent_gender(rs.getString("gender"));
            //student.setStudent_gender(rs.getInt("gender"));
            //student.setStudent_user(rs.getString("user_id"));

            // 終了処理
            stmt.close();
            rs.close();
            return true;//まだ使用されていないので使える

            // Studentオブジェクトを返す
            //return student;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return false;//エラー

        } finally {
        }
    }

    public boolean deleteStudent(Student student, Connection connection) {

        try {

            // SQLコマンド
            System.out.println(student.getStudent_id());
            String sql = "delete from students where id = '"+student.getStudent_id()+"'" ;

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);//nullになる…

            

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            //stmt.setString(1, student.getStudent_id());
            //int stu_gender = Integer.parseInt(student.getStudent_gender());
            //stmt.setInt(2, student.getStudent_gender());
            //stmt.setString(3, student.getStudent_name());
            //stmt.setString(4, student.getStudent_user());
            //stmt.setString(5, result.getTaikai_kekka());
            System.out.println("delete complete");
            //boolean result = true;


            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return false;//失敗したらbooleanでfalseを返す

        } finally {
        }
    }

    public boolean updateStudent(Student student, Connection connection) {

        try {

            // SQLコマンド
            System.out.println(student.getStudent_id());
            System.out.println("in updateDAO");
            String sql = "update students set name=?,gender=? where id = '"+student.getStudent_id()+"'";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);//nullになる…

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, student.getStudent_name());
            //int stu_gender = Integer.parseInt(student.getStudent_gender());
            stmt.setInt(2, student.getStudent_gender());
            //stmt.setString(3, student.getStudent_name());
            //stmt.setString(4, student.getStudent_user());
            //stmt.setString(5, result.getTaikai_kekka());
            System.out.println("update complete");


            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            System.out.println("update error");
            return false;

        } finally {
        }
    }


/*public class StudentDAO extends DriverAccessor {
    //こっちは引数があるから、検索に引っかかったものを探す
    public List<Student> findAll(Student student, Connection connection) {
        String sql = "SELECT * FROM students WHERE student_id IN(Select workplace_id FROM workplace WHERE user_id = ?) ORDER BY start_date ASC, end_date ASC";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Account.getLoginId());
            ResultSet resultSet = statement.executeQuery();
            List<ShiftBeans> SbList = new ArrayList<ShiftBeans>();
            while (resultSet.next()) {
                ShiftBeans returnSb = new ShiftBeans();
                returnSb.setShiftId(resultSet.getInt("shift_id"));
                returnSb.setStartDate(resultSet.getString("start_date"));
                returnSb.setEndDate(resultSet.getString("end_date"));
                returnSb.setWorkplaceId(resultSet.getInt("workplace_id"));
                returnSb.setMemo(resultSet.getString("memo"));
                SbList.add(returnSb);
            }
            statement.close();
            resultSet.close();

            return SbList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/


//public class SampleDao extends DriverAccessor {
    //こっちは引数が無いから、全部取ってくる
    public List<Student> findAll(Connection connection) {
        String findAll = "select * from students";
        try {
            PreparedStatement statement = connection.prepareStatement(findAll);//null
            ResultSet resultSet = statement.executeQuery();
            List<Student> studentList = new ArrayList<Student>();
            while(resultSet.next()) {
                Student student = new Student();
                student.setStudent_id(resultSet.getString("id"));
                student.setStudent_name(resultSet.getString("name"));
                /*String gender = String.valueOf(resultSet.getInt("gender"));
                switch(gender){
                 case "1": gender="男";
                          break;
                 case "2": gender="女";
                          break;
                 case "3": gender="その他";
                          break;
                }*/
                student.setStudent_gender(resultSet.getInt("gender"));
                student.setStudent_user(resultSet.getString("user_id"));
                studentList.add(student);
            }
            statement.close();
            resultSet.close();

            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

 //}

 /*public List<Student> findAllNumber(String student_number[], Connection connection) { //番号が一致する児童を…listも送るか
    //for(int i=0;i<student_number.length;i++){
     //String sql = "SELECT * FROM students WHERE student_id ="+student_number[i]+"'"; //"IN(Select workplace_id FROM workplace WHERE user_id = ?) ORDER BY start_date ASC, end_date ASC";
        try {
            List<Student> studentlist = new ArrayList<Student>();
          for(int i=0;i<student_number.length;i++){
            String sql = "SELECT * FROM students WHERE student_id ="+student_number[i]+"'"; //"IN(Select workplace_id FROM workplace WHERE user_id = ?) ORDER BY start_date ASC, end_date ASC";
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setString(1, Account.getLoginId());
            ResultSet resultSet = statement.executeQuery();
            //List<Student> studentlist = new ArrayList<Student>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudent_id(resultSet.getString("id"));
                student.setStudent_name(resultSet.getString("name"));
                String gender = String.valueOf(resultSet.getInt("gender"));
                switch(gender){
                 case "0": gender="男";
                           break;
                 case "1": gender="女";
                           break;
                 case "2": gender="その他";
                           break;
                }//switch
                student.setStudent_gender(gender);
                student.setStudent_user(resultSet.getString("user_id"));
                //returnSb.setMemo(resultSet.getString("memo"));
                studentlist.add(student);
            }//while
            statement.close();
            resultSet.close();
            //PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setString(1, Account.getLoginId());
            //ResultSet resultSet = statement.executeQuery();
          }//for
            //statement.close();
            //resultSet.close();

            return studentlist;
        } catch (SQLException e) {//try
            e.printStackTrace();
            return null;
        }//catch
    //}//for

        /*String sql = "SELECT * FROM students WHERE student_id ="+student_number+"'"; //"IN(Select workplace_id FROM workplace WHERE user_id = ?) ORDER BY start_date ASC, end_date ASC";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Account.getLoginId());
            ResultSet resultSet = statement.executeQuery();
            List<Student> studentlist = new ArrayList<Student>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudent_id(resultSet.getString("id"));
                student.setStudent_name(resultSet.getString("name"));
                student.setStudent_gender(resultSet.getInt("gender"));
                student.setStudent_user(resultSet.getString("user_id"));
                //returnSb.setMemo(resultSet.getString("memo"));
                studentlist.add(student);
            }
            statement.close();
            resultSet.close();

            return studentlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }*/
  //}//findallnum

}
