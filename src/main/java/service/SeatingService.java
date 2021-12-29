
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

    public List<SeatingArrangements> getSeatingArrangements(ClassDef classDef) {
        // クラスから登録されている座席配置を返す
        // DAOオブジェクト生成
        SeatingDAO dao = new SeatingDAO();
        createConnection(dao);
        List<SeatingArrangements> seatingArrangementsList = dao.findSeatingArrangements(classDef, connection);
        // dao に反映
        closeConnection(dao);
        return seatingArrangementsList;
    }

}
