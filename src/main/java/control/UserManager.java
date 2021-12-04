//　自分が格納されているフォルダ名
package control;

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
    public void registUser(User user) {

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

    // 検索する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public User searchUser(User user, Connection connection) {

        try {

            // SQLコマンド
            String sql = "select * from user where id = '" + user.getId() + "'";

            // SQLのコマンドを実行する
            // 実行結果はrsに格納される
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.first();

            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
            user.setPassword2(rs.getString("password2"));

            //パスワードが正しいかどうか
            if(user.getPassword().equals(rs.getString("password2"))){
            }
            else{
                user=null;
            }

            // 終了処理
            stmt.close();
            rs.close();

            // Studentオブジェクトを返す
            return user;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;

        } finally {
        }
    }

}