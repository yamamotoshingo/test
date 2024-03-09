package ShoppingSite;

import java.io.Serializable;

public class HistoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itemId;
	private String itemName;
	private int quantity;
	private String purchaseDate;
	private String imagePath;

	public HistoryBean() {

		itemId = "";
		itemName = "";
		quantity = 0;
		purchaseDate = "";
	}

	/**
	 * 商品IDを返却します
	 * @return
	 */
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
