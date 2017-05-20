package net.hw.shop.util;

import com.ibatis.common.resources.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.IOException;
import java.sql.Connection;

/**
 * Created by howard on 2017/5/11.
 */
public class SQLFileExecutor {

    public static boolean execute(String sqlFile) {
        Connection conn = ConnectionManager.getConnection();
        ScriptRunner runner = new ScriptRunner(conn);
        try {
            runner.runScript(Resources.getResourceAsReader(sqlFile));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        if (execute("simonshop_final.sql")) {
            System.out.println("SQL脚本文件执行成功！");
        } else {
            System.out.println("SQL脚本文件执行失败！");
        }
    }
}
