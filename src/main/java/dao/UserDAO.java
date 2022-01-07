
//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
//import dao.ReservingInfoDAO;
//import dao.UserInfoDAO;
//import model.ReservingInfo;
//import model.User;

import beans.User;
import utility.DriverAccessor;

public class UserDAO extends DriverAccessor {

    // 情報をデータベースに登録する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public void registUser(User user, Connection connection) throws NoSuchAlgorithmException {

        try {
            System.out.println("DAO.registUser");

            String password = user.getPassword();
            String hashedpassword = hash(password);

            // SQLコマンド
            String sql = "insert into user values(?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, user.getId());
            stmt.setString(2, hashedpassword);

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
            if (rs.getString("id") == null) {
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
    public boolean loginUser(User user, Connection connection) throws NoSuchAlgorithmException {

        try {
            System.out.println("DAO.loginUser");
            // SQLコマンド
            String sql = "select * from user where id = '" + user.getId() + "'";

            System.out.println("（DAO）取得した文字列は" + user.getId() + "です！");
            System.out.println("（DAO）取得した文字列は" + user.getPassword() + "です！");
            System.out.println("（DAO）取得した文字列は" + user.getPassword2() + "です！");

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.first();

            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
            // user.setPassword2(null);

            String password = user.getPassword();
            String hashedpassword = hash(password);

            String passA = hashedpassword;// 入力されたpass
            String passB = hash(rs.getString("password"));// 入力されたidに対応するpass

            System.out.println("A:" + passA);
            System.out.println("B:" + passB);

            boolean ans = false;

            // パスワードが正しいかどうか
            if (passA.equals(passB)) {
                ans = true;
            } else {
                ans = false;
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

    // 入力された現在のパスワードがあっているか
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public boolean checkPassword(String id, String passwordU, Connection connection) throws NoSuchAlgorithmException {

        try {
            System.out.println("DAO.checkPassword");
            // SQLコマンド
            String sql = "select * from user where id = '" + id + "'";

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.first();

            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
            // user.setPassword2(null);

            String pass = rs.getString("password");// 入力されたidに対応するpass

            passwordU = hash(passwordU);

            System.out.println("password:" + pass);
            System.out.println("passU:" + passwordU);

            boolean ans = false;

            // パスワードが正しいかどうか
            if (passwordU.equals(pass)) {
                ans = true;
            } else {
                ans = false;
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

    // 入力された現在のパスワードがあっているか
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public void updatePassword(String id, String passwordU, Connection connection) throws NoSuchAlgorithmException {
        try {
            System.out.println("DAO.updateUser");

            passwordU = hash(passwordU);
            // SQLコマンド
            // String sql = "insert into user values(?, ?)";
            String sql = "update user set password='" + passwordU + "' where id='" + id + "'";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    public String hash(String password) throws NoSuchAlgorithmException {
        // SHA-256（SHA-2）ハッシュ化制御
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] sha256_result = sha256.digest(password.getBytes());// ここでハッシュ化している
        String hashedpassword = String.format("%040x", new BigInteger(1, sha256_result));
        System.out.println("SHA-256：" + hashedpassword);
        return hashedpassword;
    }

}
