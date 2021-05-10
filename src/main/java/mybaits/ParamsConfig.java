package mybaits;


import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ParamsConfig {

    private String basePack = "com.ymm.activity.module";

    /**
     * 作者
     */
    private String author = "xiehuisheng@hotmail.com";

    /**
     * controller,service,mapper,serviceImpl输出路径
     */
    private String outOutDir = "./src/main/java/";

    /**
     * xml文件输出路径
     */
    private String xmlPath = "./src/main/resources/mapper";

    /**
     * dto输出路径
     */
    private String dtoPath;

    public String getDtoPath() {
        return  outOutDir + basePack.replace(".","/") +"/dto";
    }

    /**
     * qo输出路径
     */
    private String qoPath;

    public String getQoPath() {
        return outOutDir + basePack.replace(".","/") +"/qo";
    }

    /**
     * dao输出路径
     */
    private String daoPath;

    public String getDaoPath() {
        return outOutDir + basePack.replace(".","/") +"/dao";
    }

    /**
     * converter输出路径
     */
    private String converterPath = outOutDir + basePack.replace(".","/") +"/converter";

    public String getConverterPath() {
        return outOutDir + basePack.replace(".","/") +"/converter";
    }

    /**
     * vo输出路径
     */
    private String voPath = outOutDir + basePack.replace(".","/") +"/vo";

    public String getVoPath() {
        return outOutDir + basePack.replace(".","/") +"/vo";
    }
    /**
     * controller包名
     */
    private String packageNameController;

    public String getPackageNameController() {
        return basePack + ".controller";
    }

    /**
     * 包名：entity
     */
    private String packageNameEntity = basePack + ".entity";

    public String getPackageNameEntity() {
        return basePack + ".entity";
    }

    /**
     * 包名：mapper
     */
    private String packageNameMapper = basePack + ".mapper";

    public String getPackageNameMapper() {
        return basePack + ".mapper";
    }

    /**
     * 包名：service
     */
    private String packageNameService = basePack + ".service";

    public String getPackageNameService() {
        return basePack + ".service";
    }

    /**
     * 包名：service.impl
     */
    private String packageNameServiceImpl = basePack + ".service.impl";

    public String getPackageNameServiceImpl() {
        return basePack + ".service.impl";
    }

    /**
     * 包名：dto
     */
    private String packageNameDto = basePack + ".dto";

    public String getPackageNameDto() {
        return basePack + ".dto";
    }

    /**
     * 包名：converter
     */
    private String packageNameConverter = basePack + ".converter";

    public String getPackageNameConverter() {
        return basePack + ".converter";
    }

    /**
     * 包名：dao
     */
    private String packageNameDao = basePack + ".dao";

    public String getPackageNameDao() {
        return basePack + ".dao";
    }

    /**
     * 包名：qo
     */
    private String packageNameQo = basePack + ".qo";

    public String getPackageNameQo() {
        return basePack + ".qo";
    }

    /**
     * 包名：vo
     */
    private String packageNameVo = basePack + ".vo";

    public String getPackageNameVo() {
        return basePack + ".vo";
    }

    /////////////////////////////////////////////类名相关///////////////////////////////////////////////////////////
    /**
     * Entity文件名称
     */
    private String fileNameEntity = "%s";

    /**
     * mapper文件名称
     */
    private String fileNameMapper = "%sMapper";

    /**
     * 数据库xml文件名称
     */
    private String fileNameXml = "%sMapper";

    /**
     * Service文件名称
     */
    private String fileNameService = "%sService";

    /**
     * Service实现类文件名称
     */
    private String fileNameServiceImpl = "%sServiceImpl";

    /**
     * Controller文件名称
     */
    private String fileNameController = "%sController";

    /**
     * controller模版地址
     */
    private String controllerTemplate = "/templates/itennis/myController.java.vm";


    /**
     * service模版地址
     */
    private String serviceTemplate = "/templates/itennis/myService.java.vm";

    /**
     * serviceImpl模版地址
     */
    private String serviceImplTemplate = "/templates/itennis/myServiceImpl.java.vm";

    /**
     * mapper模版地址
     */
    private String mapperTemplate = "/templates/itennis/myMapper.java.vm";


    /**
     * xml模版地址
     */
    private String xmlTemplate = "/templates/itennis/myMapper.xml.vm";

    /**
     * entity模版地址
     */
    private String entityTemplate = "/templates/itennis/entity.java.vm";

    /**
     * VO模版地址
     */
    private String voTemplate = "/templates/itennis/entity.vo.java.vm";


    /**
     * QO模版地址
     */
    private String qoTemplate = "/templates/itennis/entity.qo.java.vm";

    /**
     * DAO模版地址
     */
    private String daoTemplate = "/templates/itennis/entity.dao.java.vm";

    /**
     * DTO模版地址
     */
    private String dtoTemplate = "/templates/itennis/entity.dto.java.vm";

    /**
     * CONVERTER模版地址
     */
    private String converterTemplate = "/templates/itennis/entity.converter.java.vm";

    /**
     * controller公共父类[空串无效]
     */
    private String superControllerClass = "";

    /**
     * 逻辑删除字段[空串无效]
     */
    private String fieldLogicDeleteName = "is_valid";

    /**
     * 添加自动填充[空串无效]
     */
    private String createFill = "CREATE_AT,CREATE_BY,IS_VALID";

    /**
     * 编辑自动填充[空串无效]
     */
    private String updateFill = "UPDATE_AT,UPDATE_BY";

    /**
     * 是否支持Swagger
     */
    private Boolean swaggerSupport = false;


    /**
     * 是否覆盖已有文件
     */
    private Boolean fileOverride = true;

    /**
     * entity是否支持lombok
     */
    private Boolean entityLombokSupport = true;


    /**
     * 是否为RestController
     */
    private Boolean isRestController = true;

    /**
     * entity主键类型
     */
    private IdType entityIdType = IdType.ASSIGN_UUID;
}
