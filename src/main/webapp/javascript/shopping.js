// 商品一覧画面でチェックボックスにチェックがあり、かつリストボックスの数量が0出なかったら購入ボタンを有効にする
function onPurchaseButton() {
    var elements = document.getElementsByName('item');
    var button = document.getElementById('button');
	for (var i = 0; i < elements.length; i++) {
		if (elements.item(i).checked && document.getElementsByName(elements.item(i).value + "list")[0].selectedIndex != 0) {
			button.removeAttribute('disabled');
			button.style.opacity = "1";
			button.classList.add("hoverbutton");
			break;
		} else {
		    button.setAttribute('disabled', true);
		    button.style.opacity = "0.5";
		    button.classList.remove("hoverbutton");
		}
	}
}

//商品一覧画面で数量を選択した際にチェックボックスにチェックがついていたらチェックを付け、購入ボタンを有効にする
//商品一覧画面で数量を選択した際にチェックボックスにチェックがついていなかったら、チェックを付け、購入ボタンを有効にする
//商品一覧画面で購入数0を選択した際に対応する商品のチェックを外し、他の商品にチェックかつ数量が0でないものがあれば、購入ボタンは有効にする、なければ無効にする
function onSelectChange(itemId) {
	var selectElement = document.getElementsByName(itemId + "list");
	var elements = document.getElementsByClassName("list");
	var button = document.getElementById('button');
	if (selectElement.item(0).selectedIndex != 0 && document.getElementById((selectElement.item(0).getAttribute('name')).slice(0, -4)).checked == false) {
		document.getElementById((selectElement.item(i).getAttribute('name')).slice(0, -4)).checked = true;
		button.removeAttribute('disabled');
		button.classList.add("hoverbutton");
		button.style.opacity = "1";
	} else if (selectElement.item(0).selectedIndex != 0 && document.getElementById((selectElement.item(0).getAttribute('name')).slice(0, -4)).checked == true) {
		button.removeAttribute('disabled');
		button.style.opacity = "1";
		button.classList.add("hoverbutton");
	} else {
		document.getElementById((selectElement.item(0).getAttribute('name')).slice(0, -4)).checked = false;
		for (var i = 0; i < elements.length; i++) {
			if (elements.item(i).selectedIndex != 0 && document.getElementById((elements.item(i).getAttribute('name')).slice(0, -4)).checked == false) {
		        document.getElementById((elements.item(i).getAttribute('name')).slice(0, -4)).checked = true;
		        button.removeAttribute('disabled');
		        button.style.opacity = "1";
		        button.classList.add("hoverbutton");
		        break;
	        } else if (elements.item(i).selectedIndex != 0 && document.getElementById((elements.item(i).getAttribute('name')).slice(0, -4)).checked == true) {
		        button.removeAttribute('disabled');
		        button.style.opacity = "1";
		        button.classList.add("hoverbutton");
		        break;
	        } else {
		         button.setAttribute('disabled', true);
		         button.style.opacity = "0.5"
                 button.classList.remove("hoverbutton");
		    }
	    }
	}
}

//商品一覧画面で取り消しボタンが押された場合はチェックボックス、リストボックス、購入ボタンをリセットする
function onDeleteCheck() {
	var items = document.getElementsByName('item');
	var lists = document.getElementsByClassName('list');
	var button = document.getElementById('button');
	for (var i = 0; i < items.length; i++) {
		items.item(i).checked = false;
	}
	for (var i = 0; i < lists.length; i++) {
		lists.item(i).selectedIndex = 0;
	}
	button.setAttribute('disabled', true);
	button.style.opacity = "0.5";
	button.classList.remove("hoverbutton");
}

//購入確認画面の確認ダイアログボックス
function purchaseConfirm() {
	if (window.confirm( "本当にこの内容で宜しいですか？") == false) {
		var element = document.getElementById("back");
		element.action = "./BuyItemServlet";
	}	 
}