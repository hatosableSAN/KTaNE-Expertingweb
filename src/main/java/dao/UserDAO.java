
//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.User;
import utility.DriverAccessor;

public class UserDAO extends DriverAccessor{

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
