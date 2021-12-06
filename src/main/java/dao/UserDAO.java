
//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.User;

public class UserDAO {

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

    // 情報をデータベースに登録する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public void registUser(User user, Connection connection) {

        try {
                System.out.println("DAO.registUser");
            // SQLコマンド
            String sql = "insert into user values(?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    // ログインする
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public boolean serchUser(User user, Connection connection) {

        try {
            System.out.println("DAO.serchUser");


            // SQLコマンド
            String sql = "select * from user where id = '" + user.getId() + "'";

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.first();

            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
            if(rs.getString("id")==null){
                return false;
            }

            // 終了処理
            stmt.close();
            rs.close();

            // Userデータがあるどうかを返す
            return true;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return false;

        } finally {
        }
    }


    // ログインする
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public boolean loginUser(User user, Connection connection) {

        try {
            System.out.println("DAO.loginUser");
            // SQLコマンド
            String sql = "select * from user where id = '" + user.getId() + "'";

            System.out.println("（DAO）取得した文字列は" + user.getId() + "です！");
            System.out.println("（DAO）取得した文字列は" + user.getPassword()+ "です！");
            System.out.println("（DAO）取得した文字列は" + user.getPassword2() + "です！");

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.first();

            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
            //user.setPassword2(null);

            String passA = user.getPassword();//入力されたpass
            String passB = rs.getString("password");//入力されたidに対応するpass

            System.out.println("A:"+passA);
            System.out.println("B:"+passB);
            
            boolean ans = false;

            //パスワードが正しいかどうか
            if(passA.equals(passB)){
                ans=true;
            }
            else{
                ans=false;
            }

            // 終了処理
            stmt.close();
            rs.close();

            // パスワードがあっているかどうかを返す
            return ans;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return false;

        } finally {
        }
    }


}
