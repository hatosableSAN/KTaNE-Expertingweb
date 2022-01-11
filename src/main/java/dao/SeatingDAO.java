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
    public boolean registStudentSeatingArr(StudentSeatingArr StudentSeatingArr, Connection connection) {
        // 座席を1つ登録する
        try {
            // SQLコマンド
            String sql = "insert into students_seating_arrangements (seating_arrangements_id,student_id,seat) values(?, ?, ?)";
            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);
            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setInt(1, StudentSeatingArr.getSeatingArrangementId());
            stmt.setString(2, StudentSeatingArr.getStudentId());
            stmt.setInt(3, StudentSeatingArr.getSeat());
            System.out.println(stmt);
            stmt.executeUpdate();
            stmt.close();
            return true;

        } catch (

        SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return false;
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
            if (SeatingArrangements.getEndDate().equals("") || SeatingArrangements.getEndDate() == null) {
                SeatingArrangements.setEndDate(null);
            }
            stmt.setString(4, SeatingArrangements.getEndDate());
            if (SeatingArrangements.getName().equals("") || SeatingArrangements.getName() == null) {
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
            return null;// SeatingArrangements;
        } finally {
        }
    }

    public List<StudentSeatingArr> findStudentSeatingArrList(SeatingArrangements SeatingArrangements,
            Connection connection) {
        // 特定座席配置Idの全座席情報を取得して返す
        String sql = "select * from students_seating_arrangements where seating_arrangements_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, SeatingArrangements.getId());
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            List<StudentSeatingArr> StudentSeatingArrList = new ArrayList<StudentSeatingArr>();
            while (rs.next()) {
                StudentSeatingArr StudentSeatingArr = new StudentSeatingArr();
                StudentSeatingArr.setId(rs.getInt("id"));
                StudentSeatingArr.setSeatingArrangementId(rs.getInt("seating_arrangements_id"));
                StudentSeatingArr.setStudentId(rs.getString("student_id"));
                StudentSeatingArr.setSeat(rs.getInt("seat"));
                StudentSeatingArrList.add(StudentSeatingArr);
            }

            statement.close();
            rs.close();

            return StudentSeatingArrList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SeatingArrangements findSeatingArrangements(SeatingArrangements SeatingArrangements, Connection connection) {
        // 特定Idの座席配置情報を取得して返す
        String sql = "select * from seating_arrangements where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, SeatingArrangements.getId());
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            // SeatingArrangements getSeatingArrangements = new SeatingArrangements();
            rs.first();
            SeatingArrangements getSeatingArrangements = new SeatingArrangements();
            getSeatingArrangements.setId(rs.getInt("id"));
            getSeatingArrangements.setClassId(rs.getInt("class_id"));
            getSeatingArrangements.setCreatedDate(rs.getString("created_date"));
            getSeatingArrangements.setStartDate(rs.getString("start_date"));
            getSeatingArrangements.setEndDate(rs.getString("end_date"));
            getSeatingArrangements.setName(rs.getString("name"));
            getSeatingArrangements.setUserId(rs.getString("user_id"));

            statement.close();
            rs.close();

            return getSeatingArrangements;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SeatingArrangements> findSeatingArrangements(ClassDef classDef, Connection connection) {
        // 特定クラスの全座席情報を取得して返す
        String sql = "select * from seating_arrangements where class_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, classDef.getClass_id());
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            List<SeatingArrangements> SeatingArrangementsList = new ArrayList<SeatingArrangements>();
            while (rs.next()) {
                SeatingArrangements getSeatingArrangements = new SeatingArrangements();
                getSeatingArrangements.setId(rs.getInt("id"));
                getSeatingArrangements.setClassId(rs.getInt("class_id"));
                getSeatingArrangements.setCreatedDate(rs.getString("created_date"));
                getSeatingArrangements.setStartDate(rs.getString("start_date"));
                getSeatingArrangements.setEndDate(rs.getString("end_date"));
                getSeatingArrangements.setName(rs.getString("name"));
                getSeatingArrangements.setUserId(rs.getString("user_id"));
                SeatingArrangementsList.add(getSeatingArrangements);
            }
            statement.close();
            rs.close();

            return SeatingArrangementsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

    public List<StudentSeatingArr> getStudentSeatingArrList(int id, Connection connection) {
        String sql = "select * from students_seating_arrangements where seating_arrangements_id = ? ORDER BY seat ASC";
        try {

            // SQLコマンド

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);// プレースホルダー代入
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
                returnSb.setSeatingArrangementId(rs.getInt("seating_arrangements_id"));
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
            SeatingArrangements SeatArr = new SeatingArrangements();
            // SQLコマンド
            // String sql = "select * from classes where id = '" + classdef.getClass_name()
            // + "'";
            String sql = "select * from seating_arrangements where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            rs.first();
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

    public List<SeatingArrangements> getsearchSeatingArr(boolean isMine, String user_id, String index, String word,
            Connection connection) {
        // 特定userIdの作成した座席配置情報を検索し取得。isMine 自分のクラスならtrue 他人ならfalse index クラス、開始期間、終了期間
        String sql;
        System.out.println(isMine + ":" + index + ":" + word);
        List<SeatingArrangements> searchSeatingList = new ArrayList<SeatingArrangements>();
        try {
            if (index.equals("class")) {// クラス名の時
                // クラス名からクラスIDを取得
                sql = "select * from classes where name LIKE ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + word + "%");
                System.out.println(statement);
                ResultSet rs = statement.executeQuery();
                List<ClassDef> searchClassList = new ArrayList<ClassDef>();
                while (rs.next()) {
                    ClassDef searchClass = new ClassDef();
                    ClassDef classdef = new ClassDef();
                    classdef.setClass_id(rs.getInt("id"));
                    classdef.setClass_name(rs.getString("name"));
                    classdef.setClass_year(rs.getInt("year"));
                    classdef.setClass_user(rs.getString("user_id"));
                    searchClassList.add(classdef);
                }
                statement.close();
                rs.close();
                // 取得したクラスIDから座席配置を検索
                for (int i = 0; i < searchClassList.size(); i++) {
                    if (isMine) {
                        sql = "select * from seating_arrangements where class_id = ? AND user_id = ?";
                    } else {
                        sql = "select * from seating_arrangements where class_id = ? AND user_id != ? ";
                    }
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, searchClassList.get(i).getClass_id());
                    statement.setString(2, user_id);
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        SeatingArrangements SeatingArrangement = new SeatingArrangements();
                        SeatingArrangement.setId(rs.getInt("id"));
                        SeatingArrangement.setClassId(rs.getInt("class_id"));
                        SeatingArrangement.setCreatedDate(rs.getString("created_date"));
                        SeatingArrangement.setStartDate(rs.getString("start_date"));
                        SeatingArrangement.setEndDate(rs.getString("end_date"));
                        SeatingArrangement.setName(rs.getString("name"));
                        SeatingArrangement.setUserId(rs.getString("user_id"));
                        searchSeatingList.add(SeatingArrangement);
                    }
                }
                statement.close();
                rs.close();
            } else if (index.equals("name")) {// 座席配置名の時
                if (isMine) {
                    sql = "select * from seating_arrangements where user_id = ? AND name LIKE ?";
                } else {
                    sql = "select * from seating_arrangements where user_id != ? AND name LIKE ?";
                }
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user_id);
                statement.setString(2, "%" + word + "%");
                System.out.println(statement);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    SeatingArrangements SeatingArrangement = new SeatingArrangements();
                    SeatingArrangement.setId(rs.getInt("id"));
                    SeatingArrangement.setClassId(rs.getInt("class_id"));
                    SeatingArrangement.setCreatedDate(rs.getString("created_date"));
                    SeatingArrangement.setStartDate(rs.getString("start_date"));
                    SeatingArrangement.setEndDate(rs.getString("end_date"));
                    SeatingArrangement.setName(rs.getString("name"));
                    SeatingArrangement.setUserId(rs.getString("user_id"));
                    searchSeatingList.add(SeatingArrangement);
                }
                statement.close();
                rs.close();
            } else if (index.equals("startdate")) {// 未完成 開始期間検索
                if (isMine) {
                    sql = "select * from seating_arrangements where user_id = ? AND DATE_FORMAT(start_date, '%Y/%m/%d') = DATE_FORMAT(?, '%Y/%m/%d')";
                } else {
                    sql = "select * from seating_arrangements where user_id != ? AND DATE_FORMAT(start_date, '%Y/%m/%d') = DATE_FORMAT(?, '%Y/%m/%d')";
                }
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user_id);
                statement.setString(2, word);
                System.out.println(statement);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    SeatingArrangements SeatingArrangement = new SeatingArrangements();
                    SeatingArrangement.setId(rs.getInt("id"));
                    SeatingArrangement.setClassId(rs.getInt("class_id"));
                    SeatingArrangement.setCreatedDate(rs.getString("created_date"));
                    SeatingArrangement.setStartDate(rs.getString("start_date"));
                    SeatingArrangement.setEndDate(rs.getString("end_date"));
                    SeatingArrangement.setName(rs.getString("name"));
                    SeatingArrangement.setUserId(rs.getString("user_id"));
                    searchSeatingList.add(SeatingArrangement);
                }
                statement.close();
                rs.close();
            } else if (index.equals("enddate")) {// 未完成 終了期間検索
                if (isMine) {
                    sql = "select * from seating_arrangements where user_id = ? AND DATE_FORMAT(end_date, '%Y/%m/%d') = DATE_FORMAT(?, '%Y/%m/%d')";
                } else {
                    sql = "select * from seating_arrangements where user_id != ? AND DATE_FORMAT(end_date, '%Y/%m/%d') = DATE_FORMAT(?, '%Y/%m/%d')";
                }
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user_id);
                statement.setString(2, word);
                System.out.println(statement);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    SeatingArrangements SeatingArrangement = new SeatingArrangements();
                    SeatingArrangement.setId(rs.getInt("id"));
                    SeatingArrangement.setClassId(rs.getInt("class_id"));
                    SeatingArrangement.setCreatedDate(rs.getString("created_date"));
                    SeatingArrangement.setStartDate(rs.getString("start_date"));
                    SeatingArrangement.setEndDate(rs.getString("end_date"));
                    SeatingArrangement.setName(rs.getString("name"));
                    SeatingArrangement.setUserId(rs.getString("user_id"));
                    searchSeatingList.add(SeatingArrangement);
                }
                statement.close();
                rs.close();
            }
            return searchSeatingList;
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

    public SeatingArrangements updateSeatingArr(SeatingArrangements seatingArrangements, Connection connection) {
        // idをもとに1つの座席配置情報（開始日時、終了日時、座席配置名）を更新し、更新した結果を返す
        String sql = "UPDATE seating_arrangements SET start_date= ? ,end_date=? ,name=? WHERE id= ? LIMIT 1";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, seatingArrangements.getStartDate());
            if (seatingArrangements.getEndDate().equals("")) {
                seatingArrangements.setEndDate(null);
            }
            statement.setString(2, seatingArrangements.getEndDate());
            if (seatingArrangements.getName().equals("")) {
                seatingArrangements.setName(null);
            }
            statement.setString(3, seatingArrangements.getName());
            statement.setInt(4, seatingArrangements.getId());
            System.out.println(statement);
            statement.executeUpdate();
            // 更新した結果を取得
            sql = "select * from seating_arrangements WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, seatingArrangements.getId());
            ResultSet rs = statement.executeQuery();
            SeatingArrangements SeatingArrangement = new SeatingArrangements();
            rs.first();
            SeatingArrangement.setId(rs.getInt("id"));
            SeatingArrangement.setClassId(rs.getInt("class_id"));
            SeatingArrangement.setCreatedDate(rs.getString("created_date"));
            SeatingArrangement.setStartDate(rs.getString("start_date"));
            SeatingArrangement.setEndDate(rs.getString("end_date"));
            SeatingArrangement.setName(rs.getString("name"));
            SeatingArrangement.setUserId(rs.getString("user_id"));
            statement.close();
            rs.close();

            return SeatingArrangement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteStudentSeatingArr(int SeatingArrangementsId, int studentSeatingArrListsize,
            Connection connection) {
        // 座席から特定の座席配置IDのものを削除
        try {
            // SQLコマンド
            String sql = "delete FROM students_seating_arrangements where seating_arrangements_id=? LIMIT ?";
            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);
            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setInt(1, SeatingArrangementsId);
            stmt.setInt(2, studentSeatingArrListsize);
            System.out.println(stmt);
            stmt.executeUpdate();
            stmt.close();
            return true;

        } catch (

        SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return false;
        } finally {
        }
    }

    public boolean deleteSeatingArrangement(int SeatingArrangementsId, Connection connection) {
        // 座席から特定の座席配置IDのものを削除
        try {
            // SQLコマンド
            String sql = "delete FROM seating_arrangements where id=? LIMIT 1";
            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);
            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setInt(1, SeatingArrangementsId);
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
