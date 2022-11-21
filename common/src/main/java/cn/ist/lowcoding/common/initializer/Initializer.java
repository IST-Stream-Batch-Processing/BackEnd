package cn.ist.lowcoding.common.initializer;

import java.sql.SQLException;

/**
 * 实现了该 interface 的类都会在应用启动时运行 run 方法，各个 Initializer 不得有相互依赖关系
 */
public interface Initializer {
    /**
     * 在启动时运行的方法
     */
    void run() throws ClassNotFoundException, SQLException;

    int getOrder();
}
