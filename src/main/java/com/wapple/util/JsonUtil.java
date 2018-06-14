package com.wapple.util;


import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;

/**
 * Created by gxt
 */
@SuppressWarnings("unchecked")
public class JsonUtil {
	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		// 对象的所有字段全部列入
		objectMapper.setSerializationInclusion(Inclusion.ALWAYS);

		// 取消默认转换timestamps形式
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

		// 忽略空Bean转json的错误
		objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

		// 所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
		objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));

		// 忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 将对象转化为json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> String objToString(T obj) {
		if (obj == null) {
			return null;
		}
		try {
			return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.warn("Parse Object to String error", e);
			return null;
		}
	}

	/**
	 * javabean转化为json字符串 格式化
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> String obj2StringFormat(T obj) {
		if (obj == null) {
			return null;
		}
		try {
			return obj instanceof String ? (String) obj
					: objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			log.warn("Parse Object to String error", e);
			return null;
		}
	}

	/**
	 * 字符串转化为java对象 单个对象
	 * 
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> T stringToObj(String str, Class<T> clazz) {
		if (StringUtils.isEmpty(str) || clazz == null) {
			return null;
		}

		try {
			return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
		} catch (Exception e) {
			log.warn("Parse String to Object error", e);
			return null;
		}
	}

	
	public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
		if (StringUtils.isEmpty(str) || typeReference == null) {
			return null;
		}
		try {
			return (T) (typeReference.getType().equals(String.class) ? str
					: objectMapper.readValue(str, typeReference));
		} catch (Exception e) {
			log.warn("Parse String to Object error", e);
			return null;
		}
	}

	/**
	 * 字符串转化为泛型集合
	 * 
	 * @param str
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static <T> T stringToList(String str, Class<?> collectionClass, Class<?>... elementClasses) {
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
		try {
			return objectMapper.readValue(str, javaType);
		} catch (Exception e) {
			log.warn("Parse String to Object error", e);
			return null;
		}
	}

}
