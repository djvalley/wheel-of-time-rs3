package net.wheeloftime.tools;

import java.io.File;

import net.wheeloftime.utils.Logger;

public class AccountsCounter {

	/**
	 * @author: miles M
	 */

	public static void main(String[] args) {
		File file = new File("data/accounts");
		Logger.log("AccountsCounter", file.list().length);

		File file2 = new File("c:/users/world2/.osscape");
		file2.renameTo(new File("c:/users/world2/.runerewind"));
	}
}