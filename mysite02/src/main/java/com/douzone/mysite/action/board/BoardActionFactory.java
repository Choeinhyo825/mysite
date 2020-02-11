package com.douzone.mysite.action.board;

import com.douzone.mysite.action.main.MainAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch (actionName) {
		case "list":
			return new ListAction();
		case "view":
			return new ViewAction();
		case "writeForm":
			return new WriteFormAction();
		case "write":
			return new WriteAction();
		case "modifyForm":
			return new ModifyFormAction();
		case "modify":
			return new ModifyAction();
		case "deleteForm":
			return new DeleteFormAction();
		case "delete":
			return new DeleteAction();
		default:
			return new MainAction();
		}
	}

}
