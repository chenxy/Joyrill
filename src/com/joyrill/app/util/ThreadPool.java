package com.joyrill.app.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 线程池 创建线程池，销毁线程池，添加新任务
 */
public final class ThreadPool {
	private static ThreadPool instance = ThreadPool.getInstance();
	public static final int SYSTEM_BUSY_TASK_COUNT = 150;
	public static int worker_num = 5;
	private static int taskCounter = 0;

	private static List<Task> taskQueue = Collections
			.synchronizedList(new LinkedList<Task>());
	public PoolWorker[] workers;

	private ThreadPool() {
		workers = new PoolWorker[5];
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new PoolWorker(i);
		}
	}

	private ThreadPool(int pool_worker_num) {
		worker_num = pool_worker_num;
		workers = new PoolWorker[worker_num];
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new PoolWorker(i);
		}
	}

	public static synchronized ThreadPool getInstance() {
		if (instance == null)
			return new ThreadPool();
		return instance;
	}

	/**
	 * 增加新的任务 每增加一个新任务，都要唤醒任务队列
	 * 
	 * @param newTask
	 */
	public void addTask(Task newTask) {
		synchronized (taskQueue) {
			newTask.setTaskId(++taskCounter);
			taskQueue.add(newTask);
			/* 唤醒队列, 开始执行 */
			taskQueue.notifyAll();
		}
	}

	/**
	 * 批量增加新任务
	 * 
	 * @param taskes
	 */
	public void batchAddTask(Task[] taskes) {
		if (taskes == null || taskes.length == 0) {
			return;
		}
		synchronized (taskQueue) {
			for (int i = 0; i < taskes.length; i++) {
				if (taskes[i] == null) {
					continue;
				}
				taskes[i].setTaskId(++taskCounter);
				taskQueue.add(taskes[i]);
			}
			/* 唤醒队列, 开始执行 */
			taskQueue.notifyAll();
		}
		for (int i = 0; i < taskes.length; i++) {
			if (taskes[i] == null) {
				continue;
			}
		}
	}

	/**
	 * 线程池信息
	 */
	public String getInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nTask Queue Size:" + taskQueue.size());
		for (int i = 0; i < workers.length; i++) {
			sb.append("\nWorker " + i + " is "
					+ ((workers[i].isWaiting()) ? "Waiting." : "Running."));
		}
		return sb.toString();
	}

	/**
	 * 销毁线程池
	 */
	public synchronized void destroy() {
		for (int i = 0; i < worker_num; i++) {
			workers[i].stopWorker();
			workers[i] = null;
		}
		taskQueue.clear();
	}

	/**
	 * 池中工作线程
	 */
	private class PoolWorker extends Thread {
		private int index = -1;
		/* 该工作线程是否有效 */
		private boolean isRunning = true;
		/* 该工作线程是否可以执行新任务 */
		private boolean isWaiting = true;

		public PoolWorker(int index) {
			this.index = index;
			start();
		}

		public void stopWorker() {
			this.isRunning = false;
		}

		public boolean isWaiting() {
			return this.isWaiting;
		}

		/**
		 * 循环执行任务 这也许是线程池的关键所在
		 */
		public void run() {
			while (isRunning) {
				Task r = null;
				synchronized (taskQueue) {
					while (taskQueue.isEmpty()) {
						try {
							/* 任务队列为空，则等待有新任务加入从而被唤醒 */
							taskQueue.wait(20);
						} catch (InterruptedException ie) {
						}
					}
					/* 取出任务执行 */
					r = (Task) taskQueue.remove(0);
				}
				if (r != null) {
					isWaiting = false;
					try {
						r.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
					isWaiting = true;
					r = null;
				}
			}
		}
	}
}