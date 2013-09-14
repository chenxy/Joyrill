package com.joyrill.app.util;

/**
* 所有任务接口
* 其他任务必须继承访类
*/
public abstract class Task implements Runnable {

    private long taskId;

    public Task() {
    }

    /**
    * 任务执行入口
    */
    public void run() {
        /**
        * 相关执行代码
        * 
        * beginTransaction();
        * 
        * 执行过程中可能产生新的任务 subtask = taskCore();
        * 
        * commitTransaction();
        * 
        * 增加新产生的任务 ThreadPool.getInstance().batchAddTask(taskCore());
        */
    }
    /**
    * 所有任务的核心 所以特别的业务逻辑执行之处
    * 
    * @throws Exception
    */
    public abstract Task[] taskCore() throws Exception;

    public long getTaskId() {
        return taskId;
    }
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

}