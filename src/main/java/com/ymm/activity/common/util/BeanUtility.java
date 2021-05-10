package com.ymm.activity.common.util;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author unkown
 */
public class BeanUtility {
    private static Logger logger = LoggerFactory.getLogger(BeanUtility.class);

    public static <T> T beanCopy(Object fromBean, Class<T> type) {
        if (fromBean == null) {
            return null;
        }
        T toBean = null;
        try {
            // 创建 JavaBean 对象
            toBean = type.newInstance();
            BeanUtils.copyProperties(fromBean, toBean);
        } catch (IllegalAccessException | InstantiationException e) {
            logger.error("BeanUtility.beanCopy error", e);
        }
        return toBean;
    }

    public static <T, K> List<T> convertOtherBeanList(List<K> list, Class<T> tartgetClass) {
        List<T> resultList = new ArrayList<T>();
        for (int i = 0; list != null && i < list.size(); i++) {
            resultList.add(BeanUtility.beanCopy(list.get(i), tartgetClass));
        }
        return resultList;
    }


    public static <T> Map<String, Object> objToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

}