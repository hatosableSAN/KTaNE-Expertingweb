
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

    public SeatingArrangements getSeatingArrangements(SeatingArrangements SeatingArrangements) {
        // 座席配置IDから座席配置を返す
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        SeatingArrangements seatingArrangements = dao.findSeatingArrangements(SeatingArrangements, connection);
        // dao に反映
        closeConnection(dao);
        return seatingArrangements;
    }

    public List<StudentSeatingArr> getStudentSeatingArrList(SeatingArrangements SeatingArrangements) {
        // 座席配置IDから座席のリストを返す
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        List<StudentSeatingArr> StudentSeatingArrList = dao.findStudentSeatingArrList(SeatingArrangements, connection);
        // dao に反映
        closeConnection(dao);
        return StudentSeatingArrList;
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

    public SeatingArrangements updateSeatingArrangements(SeatingArrangements seatingArrangements) {
        // 座席配置情報を更新・取得
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        SeatingArrangements SeatingArrangements = dao.updateSeatingArr(seatingArrangements, connection);
        // dao に反映
        closeConnection(dao);
        return SeatingArrangements;
    }

    public void updateStudentSeatingArr(int SeatingArrangementsId, List<StudentSeatingArr> studentSeatingArrList) {
        // 座席リストを更新（更新する座席配置IDの座席を削除した後、登録）
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        // 削除
        dao.deleteStudentSeatingArr(SeatingArrangementsId, studentSeatingArrList.size(), connection);
        // 登録
        for (int i = 0; i < studentSeatingArrList.size(); i++) {
            dao.registStudentSeatingArr(studentSeatingArrList.get(i), connection);
        }
        // dao に反映
        closeConnection(dao);
    }

    public boolean deleteseating(int SeatingArrangementsId, int maxsize) {
        // 座席配置削除
        boolean result = false;
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        // 座席削除
        result = dao.deleteStudentSeatingArr(SeatingArrangementsId, maxsize, connection);
        // 座席配置削除
        if (result) {
            result = dao.deleteSeatingArrangement(SeatingArrangementsId, connection);
        }
        // dao に反映
        closeConnection(dao);
        return result;
    }
}
