package cn.ist.lowcoding.common.util;

import cn.ist.lowcoding.common.model.Field;
import cn.ist.lowcoding.common.model.Type;
import cn.ist.lowcoding.common.model.types.ObjectType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static JsonNode readFromResourcePath(String url) throws IOException {
        return objectMapper.readTree(new ClassPathResource(url).getInputStream());
    }

    public static String writeValueAsString(Object object) {
        try {
            if (object instanceof String) {
                return (String) object;
            }
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T readValue(InputStream inputStream, Class<T> clazz) {
        try {
            return objectMapper.readValue(inputStream, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T readValue(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T convertValue(Object fromValue, Class<T> clazz) {
        try {
            return objectMapper.convertValue(fromValue, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonNode readTree(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObjectNode contact(List<ObjectNode> objectNodes) {
        ObjectNode res = objectMapper.createObjectNode();
        for (ObjectNode objectNode : objectNodes) {
            res.setAll(objectNode);
        }
        return res;
    }

    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }

    public static byte[] writeValueAsBytes(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(object);
    }

    public static JsonNode dfsGenerateNullJsonNode(Type type) {
        JsonNodeFactory factory = JsonNodeFactory.instance;
        if (type instanceof ObjectType) {
            ObjectType objectType = (ObjectType) type;
            ObjectNode objectNode = new ObjectNode(factory);
            for (Field field: objectType.getFields()) {
                objectNode.put(field.getName(), dfsGenerateNullJsonNode(field.getType()));
            }
            return objectNode;
        } else {
            return NullNode.getInstance();
        }
    }

    public static JsonNode extractField(JsonNode jsonNode, String fieldName) {
        Optional<JsonNode> fieldNode = Optional.ofNullable(jsonNode);
        if (!StringUtils.isEmpty(fieldName)) {
            String[] paths = fieldName.split("\\.");
            for (String path: paths) {
                fieldNode = fieldNode.map(x -> x.get(path));
            }
        }
        return fieldNode.orElse(NullNode.getInstance());
    }

    public static void fullfillField(JsonNode source, JsonNode fieldNode, String fieldName) {
        String[] paths = fieldName.split("\\.");
        for (int i=0; i<paths.length-1; ++i) {
            source = source.get(paths[i]);
        }
        ((ObjectNode) source).put(paths[paths.length-1], fieldNode);
    }
}
