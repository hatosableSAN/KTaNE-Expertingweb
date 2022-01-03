//　自分が格納されているフォルダ名
package control;

import java.security.NoSuchAlgorithmException;
//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;

import beans.User;
import dao.UserDAO;

public class UserManager {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public UserManager() {
    }

    // 追加
    // 引数はUserオブジェクト
    public void registUser(User user) throws NoSuchAlgorithmException {
        System.out.println("Manager.registUser");

        // UserDAOオブジェクト生成
        UserDAO userDAO = new UserDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = userDAO.createConnection();

        // UserオブジェクトをDataBaseに登録する
        userDAO.registUser(user, this.connection);

        // DataBaseとの接続を切断する
        userDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;
    }


    // 検索
   public boolean serchUser(User user) {
    System.out.println("Manager.serchUser");

    //あるかないか（仮）
    boolean ans = false;

    // StudentDAOオブジェクト生成
    UserDAO userDAO = new UserDAO();

    // DataBaseへ接続し、コネクションオブジェクトを生成する
    this.connection = userDAO.createConnection();

    // 検索する
    ans = userDAO.serchUser(user, this.connection);

    // DataBaseとの接続を切断する
    userDAO.closeConnection(this.connection);

    // コネクションオブジェクトを破棄する
    this.connection = null;

    return ans;
    }

    
   // ログイン
   public boolean loginUser(User user) {
    System.out.println("Manager.loginUser");

    //あるかないか（仮）
    boolean ans = false;

    // StudentDAOオブジェクト生成
    UserDAO userDAO = new UserDAO();

    // DataBaseへ接続し、コネクションオブジェクトを生成する
    this.connection = userDAO.createConnection();

    // 検索する
    ans = userDAO.loginUser(user, this.connection);

    // DataBaseとの接続を切断する
    userDAO.closeConnection(this.connection);

    // コネクションオブジェクトを破棄する
    this.connection = null;

    return ans;
    }

    // パスワード変更時のパスワードチェック
   public boolean checkPassword(String id,String password) {
    System.out.println("Manager.checkPassword");

    //あっているかないか（仮）
    boolean ans = false;

    // StudentDAOオブジェクト生成
    UserDAO userDAO = new UserDAO();

    // DataBaseへ接続し、コネクションオブジェクトを生成する
    this.connection = userDAO.createConnection();

    // 検索する
    ans = userDAO.checkPassword(id,password, this.connection);

    // DataBaseとの接続を切断する
    userDAO.closeConnection(this.connection);

    // コネクションオブジェクトを破棄する
    this.connection = null;

    return ans;
    }

    // パスワード変更
   public void updatePassword(String id,String passwordU) {
    System.out.println("Manager.updatePassword");

    // StudentDAOオブジェクト生成
    UserDAO userDAO = new UserDAO();

    // DataBaseへ接続し、コネクションオブジェクトを生成する
    this.connection = userDAO.createConnection();

    // 変更する
    userDAO.updatePassword(id,passwordU, this.connection);

    // DataBaseとの接続を切断する
    userDAO.closeConnection(this.connection);

    // コネクションオブジェクトを破棄する
    this.connection = null;

    }

}