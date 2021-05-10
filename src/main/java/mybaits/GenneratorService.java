package mybaits;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.*;

/**
 * @author xiehuisheng@hotmail.com
 */
public class GenneratorService {

    public static ParamsConfig paramsConfig = new ParamsConfig();

    /**
     * 数据连接信息
     *
     * @param dbType   数据库类型
     * @param dbUrl    连接地址
     * @param username 用户名
     * @param password 密码
     * @param driver   驱动
     * @return DataSourceConfig
     */
    private static DataSourceConfig dataSourceConfig(DbType dbType, String dbUrl, String username, String password, String driver) {
        return new DataSourceConfig()
                .setDbType(dbType)
                .setUrl(dbUrl)
                .setUsername(username)
                .setPassword(password)
                .setDriverName(driver).setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        System.out.println("转换类型：" + fieldType);
                        //tinyint转换成Integer
                        if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                            return DbColumnType.INTEGER;
                        }
                        //将数据库中datetime转换成date
                        if ( fieldType.toLowerCase().contains( "datetime" ) ) {
                            return DbColumnType.DATE;
                        }
                        return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
                    }
                });
    }

    /**
     * 全局配置对象
     *
     * @return GlobalConfig
     */
    private static GlobalConfig globalConfig() {
        return new GlobalConfig()
                //作者
                .setAuthor(paramsConfig.getAuthor())
                //controller,service,mapper,serviceImpl输出路径
                .setOutputDir(paramsConfig.getOutOutDir())
                //是否覆盖已有文件
                .setFileOverride(paramsConfig.getFileOverride())
                //是否打开输出目录
                .setOpen(false)
                //时间采用java 8，（操作工具类：JavaLib => DateTimeUtils）
                .setDateType(DateType.ONLY_DATE)
                //不需要ActiveRecord特性的请改为false
                .setActiveRecord(true)
                //XML 二级缓存
                .setEnableCache(false)
                //XML是否生成ResultMap
                .setBaseResultMap(true)
                //XML是否生成columList
                .setBaseColumnList(true)
                //XML enableCache
                .setEnableCache(false)
                //是否生成 kotlin 代码
                .setKotlin(false)
                //自定义文件命名，注意 %s 会自动填充表名
                .setEntityName(paramsConfig.getFileNameEntity())
                .setMapperName(paramsConfig.getFileNameMapper())
                .setXmlName(paramsConfig.getFileNameXml())
                .setServiceName(paramsConfig.getFileNameService())
                .setServiceImplName(paramsConfig.getFileNameServiceImpl())
                .setControllerName(paramsConfig.getFileNameController())
                //主键类型
                .setIdType(paramsConfig.getEntityIdType())
                //是否支持swagger2
                .setSwagger2(paramsConfig.getSwaggerSupport());
    }

    /**
     * 策略配置对象
     *
     * @param tablePrefixes 表前缀
     * @param tableNames    表前缀
     * @param fieldPrefixes 字段前缀
     * @return StrategyConfig
     */
    private static StrategyConfig strategyConfig(String[] tablePrefixes, String[] tableNames, String[] fieldPrefixes) {
        List<TableFill> tableFills = new ArrayList<>();
        // 表的自动填充字段
        if (StrUtil.isNotEmpty(paramsConfig.getCreateFill())){
            List<String> createFillList = Arrays.asList(paramsConfig.getCreateFill().split(","));
            createFillList.forEach(s -> tableFills.add(new TableFill(s, FieldFill.INSERT)));
        }

        if (StrUtil.isNotEmpty(paramsConfig.getUpdateFill())){
            List<String> updateFillList = Arrays.asList(paramsConfig.getUpdateFill().split(","));
            updateFillList.forEach(s -> tableFills.add(new TableFill(s, FieldFill.UPDATE)));
        }

        return new StrategyConfig()
                // 全局大写命名 ORACLE 注意
                .setCapitalMode(true)
                // 是否跳过视图
                .setSkipView(false)
                //.setDbColumnUnderline(true)
                // 此处可以修改为您的表前缀(数组)
                .setTablePrefix(tablePrefixes)
                // 字段前缀
                .setFieldPrefix(fieldPrefixes)
                // 表名生成策略
                .setNaming(NamingStrategy.underline_to_camel)
                //修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames)
                // 排除生成的表
                //.setExclude(new String[]{"test"})
                // entity是否支持lombok实体
                .setEntityLombokModel(paramsConfig.getEntityLombokSupport())
                //entity是否为构建者模型
                .setChainModel(true)
                //entity是否生成字段常量（默认 false 可通过常量名获取数据库字段名  3.x支持lambda表达式
                .setEntityColumnConstant(false)
                // 逻辑删除属性名称
                .setLogicDeleteFieldName(paramsConfig.getFieldLogicDeleteName())
                // 表的自动填充字段
                .setTableFillList(tableFills)
                //是否为RestController
                .setRestControllerStyle(paramsConfig.getIsRestController())
                //实体属性上添加表字段映射
                .setEntityTableFieldAnnotationEnable(true)
                ;
    }

    /**
     * 包配置对象
     *
     * @return PackageConfig
     */
    private static PackageConfig packageConfig() {
        return new PackageConfig()
                .setParent(null)
                .setController(ObjectUtil.isEmpty(paramsConfig.getPackageNameController()) ? null : paramsConfig.getPackageNameController())
                .setEntity(ObjectUtil.isEmpty(paramsConfig.getPackageNameEntity()) ? null : paramsConfig.getPackageNameEntity())
                .setMapper(ObjectUtil.isEmpty(paramsConfig.getPackageNameMapper()) ? null : paramsConfig.getPackageNameMapper())
                .setService(ObjectUtil.isEmpty(paramsConfig.getPackageNameService()) ? null : paramsConfig.getPackageNameService())
                .setServiceImpl(ObjectUtil.isEmpty(paramsConfig.getPackageNameServiceImpl()) ? null : paramsConfig.getPackageNameServiceImpl())
                ;
    }

    /**
     * 自定义配置对象
     *
     * @param injectionConfig 表配置
     * @return InjectionConfig
     */
    private static InjectionConfig injectionConfig(InjectionConfig injectionConfig) {
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(paramsConfig.getXmlPath()) || ObjectUtil.isNotEmpty(paramsConfig.getXmlTemplate())) {
            // 自定义xml配置
            fileOutConfigList.add(new FileOutConfig(paramsConfig.getXmlTemplate()) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return paramsConfig.getXmlPath() + "/" + tableInfo.getXmlName() + StringPool.DOT_XML;
                }
            });
        }

//        if (ObjectUtil.isNotEmpty(paramsConfig.getVoTemplate()) || ObjectUtil.isNotEmpty(paramsConfig.getVoPath())) {
//            // 自定义输出VO配置
//            fileOutConfigList.add(new FileOutConfig(paramsConfig.getVoTemplate()) {
//                @Override
//                public String outputFile(TableInfo tableInfo) {
//                    return paramsConfig.getVoPath() + "/" + tableInfo.getEntityName() + "VO.java";
//                }
//            });
//        }
//
//        if (ObjectUtil.isNotEmpty(paramsConfig.getQoTemplate()) || ObjectUtil.isNotEmpty(paramsConfig.getQoPath())) {
//            // 自定义输出QO配置
//            fileOutConfigList.add(new FileOutConfig(paramsConfig.getQoTemplate()) {
//                @Override
//                public String outputFile(TableInfo tableInfo) {
//                    return paramsConfig.getQoPath() + "/" + tableInfo.getEntityName() + "QO.java";
//                }
//            });
//        }


        if (ObjectUtil.isNotEmpty(paramsConfig.getDaoTemplate()) || ObjectUtil.isNotEmpty(paramsConfig.getDaoPath())) {
            // 自定义输出DAO配置
            fileOutConfigList.add(new FileOutConfig(paramsConfig.getDaoTemplate()) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return paramsConfig.getDaoPath() + "/" + tableInfo.getEntityName() + "Dao.java";
                }
            });
        }

        if (ObjectUtil.isNotEmpty(paramsConfig.getConverterTemplate()) || ObjectUtil.isNotEmpty(paramsConfig.getConverterPath())) {
            // 自定义输出converter配置
            fileOutConfigList.add(new FileOutConfig(paramsConfig.getConverterTemplate()) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return paramsConfig.getConverterPath() + "/" + tableInfo.getEntityName() + "Converter.java";
                }
            });
        }

        if (ObjectUtil.isNotEmpty(paramsConfig.getDtoTemplate()) || ObjectUtil.isNotEmpty(paramsConfig.getDtoPath())) {
            // 自定义输出DTO配置
            fileOutConfigList.add(new FileOutConfig(paramsConfig.getDtoTemplate()) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return paramsConfig.getDtoPath() + "/" + tableInfo.getEntityName() + "DTO.java";
                }
            });
        }


        injectionConfig.setFileOutConfigList(fileOutConfigList);

        return injectionConfig;
    }

    /**
     * 执行
     *
     * @param dbType        数据库类型
     * @param dbUrl         数据库地址
     * @param username      数据库用户名
     * @param password      数据库密码
     * @param driver        数据库驱动
     * @param tablePrefixes 表前缀
     * @param tableNames    表明
     * @param fieldPrefixes 字段前缀
     */
    public static void execute(DbType dbType, String dbUrl, String username, String password, String driver,
                               String[] tablePrefixes, String[] tableNames, String[] fieldPrefixes) {
        //要生成的表数组为空直接中断
        if (ArrayUtil.isAllEmpty(tableNames)) {
            return;
        }

        //mabatis-plus全局配置
        GlobalConfig globalConfig = globalConfig();

        //数据库配置对象
        DataSourceConfig dataSourceConfig = dataSourceConfig(dbType, dbUrl, username, password, driver);

        // 策略配置
        StrategyConfig strategyConfig = strategyConfig(tablePrefixes, tableNames, fieldPrefixes);
        // 策略配置--controller父类
        if (ObjectUtil.isNotEmpty(paramsConfig.getSuperControllerClass())) {
            strategyConfig.setSuperControllerClass(paramsConfig.getSuperControllerClass());
        }

        //包信息配置
        PackageConfig packageConfig = packageConfig();

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(10);
                map.put("VOPackage", paramsConfig.getPackageNameVo());
                map.put("QOPackage", paramsConfig.getPackageNameQo());
                map.put("DAOPackage", paramsConfig.getPackageNameDao());
                map.put("CONVERTERPackage", paramsConfig.getPackageNameConverter());
                map.put("DTOPackage", paramsConfig.getPackageNameDto());
                map.put("DBType", dbType.getDb());
                map.put("FieldlogicDeleteName", paramsConfig.getFieldLogicDeleteName());
                map.put("BasePack", paramsConfig.getBasePack());
                this.setMap(map);
            }
        };
        // 自定义配置对象
        injectionConfig = injectionConfig(injectionConfig);

        //设置模版
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity(paramsConfig.getEntityTemplate());
        tc.setMapper(paramsConfig.getMapperTemplate());
        tc.setService(paramsConfig.getServiceTemplate());
        tc.setServiceImpl(paramsConfig.getServiceImplTemplate());
        tc.setController(paramsConfig.getControllerTemplate());

        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setController(null);
        //已自定义xml模版以及输出路径顾这里写null,不然默认会在mapper目录下生成一个xml
        tc.setXml(null);

        //代码生成
        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplateEngine(new VelocityTemplateEngine())
                .setTemplate(tc)
                .execute();
    }


}
