package dao;

import beans.*;
import utility.DriverAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.SeatingArrangements;

public class SeatingDAO extends DriverAccessor {
    public void registStudentSeatingArr(StudentSeatingArr StudentSeatingArr, Connection connection) {
        // 座席を1つ登録する
        try {
            // SQLコマンド
            String sql = "insert into students_seating_arrangements (seating_arrangement_id,student_id,seat) values(?, ?, ?)";
            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);
            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setInt(1, StudentSeatingArr.getSeatingArrangementId());
            stmt.setString(2, StudentSeatingArr.getStudentId());
            stmt.setInt(3, StudentSeatingArr.getSeat());
            System.out.println(stmt);
            stmt.executeUpdate();
            stmt.close();

        } catch (

        SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }

    public SeatingArrangements registSeatingArrangements(SeatingArrangements SeatingArrangements,
            Connection connection) {
        // 座席配置を1つ登録する
        try {
            // SQLコマンド
            String sql = "insert into seating_arrangements (class_id,created_date,start_date,end_date,name,user_id) values(?, ?, ?,?,?,?)";
            // SQLコマンドの実行 登録した時のAUTO＿INCREMENTの番号（ID)を取得
            PreparedStatement stmt = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setInt(1, SeatingArrangements.getClassId());
            stmt.setString(2, SeatingArrangements.getCreatedDate());
            stmt.setString(3, SeatingArrangements.getStartDate());
            if (SeatingArrangements.getEndDate().equals("")) {
                SeatingArrangements.setEndDate(null);
            }
            stmt.setString(4, SeatingArrangements.getEndDate());
            if (SeatingArrangements.getName().equals("")) {
                SeatingArrangements.setName(null);
            }
            stmt.setString(5, SeatingArrangements.getName());
            stmt.setString(6, SeatingArrangements.getUserId());
            System.out.println(stmt);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int autoIncrementKey = 0;
            if (rs.next()) {
                autoIncrementKey = rs.getInt(1);
            }
            SeatingArrangements.setId(autoIncrementKey);
            stmt.close();
            rs.close();

            // // 登録した時のAUTO＿INCREMENTの番号（ID)を取得
            // sql = "select last_insert_id() from seating_arrangements";
            // stmt = connection.prepareStatement(sql);
            // ResultSet rs = stmt.executeQuery(sql);
            // System.out.println(stmt);
            // rs.first();
            // SeatingArrangements.setId(rs.getInt("last_insert_id()"));
            // stmt.executeUpdate();
            // stmt.close();

            return SeatingArrangements;

        } catch (

        SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return SeatingArrangements;
        } finally {
        }
    }

    public List<SeatingArrangements> getSeatingList(Connection connection) {
        String sql = "select * from seating_arrangements";
        try {

            // SQLコマンド


            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<SeatingArrangements> List = new ArrayList<SeatingArrangements>();

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            // Statement stmt = connection.createStatement();
            // ResultSet rs = stmt.executeQuery(sql);
            // System.out.println("取得した文字列は" + rs.getString("taikai_name") + "です！");



            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する

            //
            // classdef.setClass_id(rs.getString("id"));
            
             while (rs.next()) {
             SeatingArrangements returnSb = new SeatingArrangements();
             returnSb.setId(rs.getInt("id"));
             returnSb.setClassId(rs.getInt("class_id"));
             returnSb.setCreatedDate(rs.getString("created_date"));
             returnSb.setStartDate(rs.getString("start_date"));
             returnSb.setEndDate(rs.getString("end_date"));
             returnSb.setName(rs.getString("name"));
             returnSb.setUserId(rs.getString("user_id"));
             System.out.println(returnSb.getClassId());
             List.add(returnSb);
             System.out.println("リスト追加したよ");
             }
             statement.close();
             rs.close();

            // Studentオブジェクトを返す
            return List;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;

        } finally {
        }
    }

    public List<StudentSeatingArr> getStudentSeatingArrList(int id,Connection connection) {
        String sql = "select * from students_seating_arrangements where seating_arrangement_id = ? ORDER BY seat ASC";
        try {

            // SQLコマンド

            
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);//プレースホルダー代入
            ResultSet rs = statement.executeQuery();
            List<StudentSeatingArr> List = new ArrayList<StudentSeatingArr>();

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            // Statement stmt = connection.createStatement();
            // ResultSet rs = stmt.executeQuery(sql);
            // System.out.println("取得した文字列は" + rs.getString("taikai_name") + "です！");



            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する

            //
            // classdef.setClass_id(rs.getString("id"));
            
             while (rs.next()) {
             StudentSeatingArr returnSb = new StudentSeatingArr();
             returnSb.setId(rs.getInt("id"));
             returnSb.setSeatingArrangementId(rs.getInt("seating_arrangement_id"));
             returnSb.setStudentId(rs.getString("student_id"));
             returnSb.setSeat(rs.getInt("seat"));
             List.add(returnSb);
             System.out.println("リスト追加したよ");
             }
             statement.close();
             rs.close();

            // Studentオブジェクトを返す
            return List;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;

        } finally {
        }
    }

    public SeatingArrangements searchSeatingArrangements(int id, Connection connection) {
        try {
            SeatingArrangements SeatArr=new SeatingArrangements();
            // SQLコマンド
            // String sql = "select * from classes where id = '" + classdef.getClass_name()
            // + "'";
            String sql = "select * from seating_arrangements where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
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
            SeatArr.setClassId(rs.getInt("class_id"));
            SeatArr.setCreatedDate(rs.getString("created_date"));
            SeatArr.setStartDate(rs.getString("start_date"));
            SeatArr.setEndDate(rs.getString("end_date"));
            SeatArr.setName(rs.getString("name"));
            SeatArr.setUserId(rs.getString("user_id"));
            // 終了処理
            statement.close();
            rs.close();

            // Studentオブジェクトを返す
            return SeatArr;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;

        } finally {
        }
    }

    public List<SeatingArrangements> getAllMySeatingArr(String user_id, Connection connection) {
        // 特定userIdの作成した全座席配置情報を取得
        String sql = "select * from seating_arrangements where user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user_id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            List<SeatingArrangements> SeatingArrangementsList = new ArrayList<SeatingArrangements>();
            while (rs.next()) {
                SeatingArrangements SeatingArrangements = new SeatingArrangements();
                SeatingArrangements.setId(rs.getInt("id"));
                SeatingArrangements.setClassId(rs.getInt("class_id"));
                SeatingArrangements.setCreatedDate(rs.getString("created_date"));
                SeatingArrangements.setStartDate(rs.getString("start_date"));
                SeatingArrangements.setEndDate(rs.getString("end_date"));
                SeatingArrangements.setName(rs.getString("name"));
                SeatingArrangements.setUserId(rs.getString("user_id"));
                SeatingArrangementsList.add(SeatingArrangements);
            }
            statement.close();
            rs.close();

            return SeatingArrangementsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SeatingArrangements> getAllOtherSeatingArr(String user_id, Connection connection) {
        // 特定userIdの"以外"の作成した全座席配置情報を取得
        String sql = "select * from seating_arrangements where user_id != ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user_id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            List<SeatingArrangements> SeatingArrangementsList = new ArrayList<SeatingArrangements>();
            while (rs.next()) {
                SeatingArrangements SeatingArrangements = new SeatingArrangements();
                SeatingArrangements.setId(rs.getInt("id"));
                SeatingArrangements.setClassId(rs.getInt("class_id"));
                SeatingArrangements.setCreatedDate(rs.getString("created_date"));
                SeatingArrangements.setStartDate(rs.getString("start_date"));
                SeatingArrangements.setEndDate(rs.getString("end_date"));
                SeatingArrangements.setName(rs.getString("name"));
                SeatingArrangements.setUserId(rs.getString("user_id"));
                SeatingArrangementsList.add(SeatingArrangements);
            }
            statement.close();
            rs.close();

            return SeatingArrangementsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
