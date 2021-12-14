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
        // 座席を1つ登録する
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

}
