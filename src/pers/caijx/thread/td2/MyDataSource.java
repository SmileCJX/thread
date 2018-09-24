package pers.caijx.thread.td2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 使用wait和nofity方式实现数据库连接池
 * Created by caijx on 2018/9/24/024.
 */
public class MyDataSource {

    private LinkedList<Connection> pool = new LinkedList<>();

    private static final int INIT_CONNECTIONS = 10;
    
    private static final String  DRIVER_CLASS = "";

    public static final String USER = "";

    public static final String PASSWORD = "";

    public static final String URL = "";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource() {
        for (int i = 0; i < INIT_CONNECTIONS; i++) {
            try {
                Connection conn = DriverManager.getConnection(URL,PASSWORD,URL);
                pool.add(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnect() {
        Connection result = null;
        synchronized (pool) {
            while (pool.size() <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!pool.isEmpty()) {
                result = pool.removeFirst();
            }
        }
        return result;
    }

    public void release(Connection conn) {
        if (conn != null) {
            synchronized (pool) {
                pool.addLast(conn);
                notifyAll();
            }
        }
    }
}
