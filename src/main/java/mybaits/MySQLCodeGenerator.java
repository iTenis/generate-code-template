package mybaits;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * MySQL 数据库代码生成类
 *
 * @author xiehuisheng@hotmail.com
 * @since 2021-05-09 10:33
 */
public class MySQLCodeGenerator {

    /**
     * 若配置了是否覆盖已有文件为true每次生成代码会覆盖原有代码 请谨慎操作！
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // 表前缀[生成的实体类自动不含表前缀]
        String[] tablePrefixes = {
                "tbl_"
        };
        // 表名，为空，不生成表
        String[] tableNames = {
                "message_record"
        };
        // 字段前缀
        String[] fieldPrefixes = {

        };
        GenneratorService.paramsConfig.setBasePack("com.ymm.user.right.server.module.message");
        GenneratorService.execute(
                DbType.MYSQL,
                "jdbc:mysql://wnojrdevmysql.service.dev.consul:3306/user_right?characterEncoding=utf8",
                "userrightadmin",
                "zq",
                "com.mysql.cj.jdbc.Driver",
                tablePrefixes,
                tableNames,
                fieldPrefixes);
    }
}
