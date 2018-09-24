package pers.caijx.thread.td2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用await和signal方式实现数据库连接池
 * Created by caijx on 2018/9/24/024.
 */
public class MyDataSource2 {

    private LinkedList<Connection> pool = new LinkedList<>();

    private static final int INIT_CONNECTIONS = 10;

    private static final String  DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public static final String USER = "";

    public static final String PASSWORD = "";

    public static final String URL = "";

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();

    static {
        try {
            //加载数据库驱动包，动态加载类
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource2() {
        for (int i = 0; i < INIT_CONNECTIONS; i++) {
            try {
                Connection conn = DriverManager.getConnection(USER,PASSWORD,URL);
                pool.add(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnect() {
        Connection result = null;
        lock.lock();
        try {
            while (pool.size() <= 0) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!pool.isEmpty()) {
                result = pool.removeFirst();
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

    public void release(Connection conn) {
        if (conn != null) {
            lock.lock();
            try {
                pool.addLast(conn);
                c1.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
