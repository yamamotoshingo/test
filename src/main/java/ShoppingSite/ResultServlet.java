package ShoppingSite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Java入門 購入結果ページ処理クラス.
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ResultServlet() {
		super();
	}

	/**
	 * POSTメソッドで呼び出された場合の処理.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 購入確認画面にセットしていた値を取得（hiddenパラメータ）
		String id = ((LoginUserBean) request.getSession().getAttribute("user_db")).getId(); // ユーザーID
		// hiddenパラーメータを取得
		String[] itemIdList = request.getParameterValues("item_id");
		String[] getquantityList = request.getParameterValues("item_quantity");
		int[] quantityList = Arrays.stream(getquantityList).mapToInt(Integer::parseInt).toArray(); // Stream APIを用いてString型配列をInt型配列に変換
		String purchaseDate = request.getParameter("item_purchasedate");

		ShoppingDao dao = new ShoppingDao();

		try {
			// 商品IDと購入数を基にデータベースを更新
			// 対象の商品在庫をマイナスする
			dao.updateItem(itemIdList, quantityList, purchaseDate);
			dao.updateHistory(id, itemIdList, quantityList, purchaseDate);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();
		}

		// 購入完了画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("./result.jsp");
		rd.forward(request, response);
	}
}