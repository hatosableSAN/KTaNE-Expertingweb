
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
import dao.*;
import beans.*;

public class SeatingService {

    // 属性
    private Connection connection = null;

    private void createConnection(SeatingDAO dao) {
        this.connection = dao.createConnection();
    }

    private void closeConnection(SeatingDAO dao) {
        dao.closeConnection(this.connection);
        this.connection = null;
    }

    // 引数を持たないコンストラクタ
    public SeatingService() {
    }

    public void registStudentSeatingArr(List<StudentSeatingArr> studentSeatingArrList) {// 座席リストを登録
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        for (int i = 0; i < studentSeatingArrList.size(); i++) {
            dao.registStudentSeatingArr(studentSeatingArrList.get(i), connection);
        }
        // dao に反映
        closeConnection(dao);
    }

    public SeatingArrangements registSeatingArrangements(SeatingArrangements SeatingArrangements) {// 座席配置を登録・登録した座席配置を返す
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        SeatingArrangements seatingArrangements = dao.registSeatingArrangements(SeatingArrangements, connection);
        // dao に反映
        closeConnection(dao);
        return seatingArrangements;
    }

    public List<SeatingArrangements> getSeatList() {
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        List<SeatingArrangements> List = dao.getSeatingList(connection);
        // dao に反映
        closeConnection(dao);
        return List;
    }

    public List<StudentSeatingArr> getStudentSeatingArrList(int seatid) {// 座席配置IDに含まれる全ての座席要素を取得
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        List<StudentSeatingArr> List=dao.getStudentSeatingArrList(seatid,connection);
        // dao に反映
        closeConnection(dao);

        return List;
    }

    public SeatingArrangements searchSeatingArrangements(int id) {// 座席配置を登録・登録した座席配置を返す
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        SeatingArrangements seatingArrangements = dao.searchSeatingArrangements(id, connection);
        // dao に反映
        closeConnection(dao);
        return seatingArrangements;
    }
    public List<SeatingArrangements> getAllMySeatingArr(String UserId) {// 自身の作成した全ての座席配置を取得
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        List<SeatingArrangements> seatingArrangementsList = dao.getAllMySeatingArr(UserId, connection);
        // dao に反映
        closeConnection(dao);
        return seatingArrangementsList;
    }

    public List<SeatingArrangements> getAllOtherSeatingArr(String UserId) {// 自身の作成した”以外”全ての座席配置を取得
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        List<SeatingArrangements> seatingArrangementsList = dao.getAllOtherSeatingArr(UserId, connection);
        // dao に反映
        closeConnection(dao);
        return seatingArrangementsList;
    }
}
