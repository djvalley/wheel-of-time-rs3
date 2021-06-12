package net.wheeloftime.executor;

import net.wheeloftime.login.Login;
import net.wheeloftime.utils.Logger;

public final class LoginThread extends Thread {
	protected LoginThread() {
		super("Login thread");
	}

	@Override
	public final void run() {
		while (!LoginExecutorManager.workerShutdown) {
			try {
				Login.process();
				Thread.sleep(20);
			} catch (Throwable t) {
				Logger.handle(t);
			}
		}
	}

}
