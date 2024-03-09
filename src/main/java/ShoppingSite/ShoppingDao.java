package ShoppingSite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Java入門 ショッピング風DAOクラス.
 */
public class ShoppingDao {

	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	/**
	 * データベースの商品と在庫を取得します.
	 * @return	商品情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectItem() throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
					"root",
					"abcd3425");
			// SQL文を生成
			ps = con.prepareStatement(
					"select item.item_id, item.image_path, item.item_name, item.price, stock.quantity from item inner join stock on item.item_id = stock.item_id");

			// SQLを実行
			rs = ps.executeQuery();

		} catch (ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	/**
	 * 商品IDを基にデータベースの商品情報と在庫を取得します.
	 * @return	商品情報（ResultSet）
	 * @param itemId 商品ID
	 * @throws SQLException
	 */
	public ResultSet selectItem(String itemId) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
					"root",
					"abcd3425");
			// SQL文を生成
			ps = con.prepareStatement(
					"select item.item_name, item.price, item.image_path, stock.quantity from item inner join stock on item.item_id = stock.item_id where item.item_id = ?");
			// SQL文に商品IDを設定
			ps.setString(1, itemId);
			// SQLを実行
			rs = ps.executeQuery();

		} catch (ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	/**
	 * ユーザの購入履歴を取得します.
	 * @return	購入履歴（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectHistory(String id) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
					"root",
					"abcd3425");
			// SQL文を生成
			ps = con.prepareStatement(
					"select history.item_id, item.item_name, item.image_path, history.quantity, history.purchase_date from history inner join item on history.id = ? and history.item_id = item.item_id");
			ps.setString(1, id);

			// SQLを実行
			rs = ps.executeQuery();

		} catch (ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	/**
	 * 商品IDを基にデータベースの在庫を更新（マイナス）します.
	 * @param itemId 	商品ID
	 * @param quantity	数量
	 * @throws SQLException
	 */
	public void updateItem(String[] itemIdList, int[] quantityList, String purchaseDate) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
					"root",
					"abcd3425");
			for (int i = 0; i < itemIdList.length; i++) {
				// SQL文を生成
				// update文を追加
				ps = con.prepareStatement(
						"update stock set quantity = quantity - ?, update_date = ? where item_id = ?");
				// SQL文に数量を設定
				ps.setInt(1, quantityList[i]);
				//SQL文に更新日を設定
				ps.setString(2, purchaseDate);
				// SQL文に商品IDを設定
				ps.setString(3, itemIdList[i]);
				// SQLを実行
				ps.executeUpdate();
			}

		} catch (ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}

	/**
	 * 購入履歴テーブルを更新します.
	 * @param id		ユーザID
	 * @param itemId 	商品ID
	 * @param quantity	数量
	 * @throws SQLException
	 */
	public void updateHistory(String id, String[] itemIdList, int[] quantityList, String purchaseDate)
			throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
					"root",
					"abcd3425");

			for (int i = 0; i < itemIdList.length; i++) {
				// SQL文を生成
				// insert文を追加
				ps = con.prepareStatement(
						"insert into history (id, item_id, quantity, purchase_date) values (?, ?, ?, ?)");

				ps.setString(1, id);
				ps.setString(2, itemIdList[i]);
				ps.setInt(3, quantityList[i]);
				ps.setString(4, purchaseDate);

				ps.executeUpdate();
			}

		} catch (ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}

	/**
	 * コネクションをクローズします.
	 */
	public void close() {

		try {

			// データベースとの接続を解除する
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}

		} catch (SQLException se) {

			// データベースとの接続解除に失敗した場合
			se.printStackTrace();
		}
	}
}