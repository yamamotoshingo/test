package ShoppingSite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Java入門 商品購入ページ処理クラス.
 */
@WebServlet("/BuyItemServlet")
public class BuyItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BuyItemServlet() {
		super();
	}

	/**
	 * GETメソッドで呼び出された場合の処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String purchasedate = ""; //購入日
		String quantity = ""; //購入数

		ArrayList<ItemBean> itembeanList = new ArrayList<ItemBean>(); //購入した商品リストを格納する
		ArrayList<String> quantityList = new ArrayList<String>(); // 購入数リストを格納

		//チェックボックスで選択した商品IDリストを取得
		String itemIds[] = request.getParameterValues("item");
        
		for (String itemId : itemIds) {
			// ドロップダウンリストから購入数を取得
			quantity = request.getParameter(itemId + "list");
            
			if (!(quantity.equals("0"))) {
			  quantityList.add(quantity);
			  // 商品情報を取得
			  Shopping shopping = new Shopping();
			  itembeanList.add(shopping.getItem(itemId));
			}
		}
		//購入日時を取得
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		purchasedate = sdf.format(cl.getTime());

		// 商品一覧をリクエストスコープの属性にセット
		request.setAttribute("itemlist", itembeanList);
		request.setAttribute("quantitylist", quantityList);
		request.setAttribute("purchasedate", purchasedate);

		// 購入確認画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("./confirm.jsp");
		rd.forward(request, response);
	}
	
	
	/**
	 * 確認画面の確認ダイアログボックスにてキャンセルが押された場合の処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// hiddenパラーメータを取得
		String[] reitemIdList = request.getParameterValues("item_id");
		String[] requantityList = request.getParameterValues("item_quantity");
		String repurchaseDate = request.getParameter("item_purchasedate");
		
		ArrayList<ItemBean> itembeanList = new ArrayList<ItemBean>(); //購入した商品リストを格納する
		ArrayList<String> quantityList = new ArrayList<String>(); // 購入数リストを格納
		
		Shopping shopping = new Shopping();
		
		for (int i = 0; i < reitemIdList.length; i++) {
			itembeanList.add(shopping.getItem(reitemIdList[i]));
			quantityList.add(requantityList[i]);
		}
		
		request.setAttribute("itemlist", itembeanList);
		request.setAttribute("quantitylist", quantityList);
		request.setAttribute("purchasedate", repurchaseDate);
		
		RequestDispatcher rd = request.getRequestDispatcher("./confirm.jsp");
		rd.forward(request, response);
	}
}