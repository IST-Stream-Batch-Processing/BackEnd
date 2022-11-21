package cn.ist.lowcoding.framework.pojo.request;

public class QueryCondition {
    String fieldName;

    queryType queryType;

    String value;

    QueryCondition left;

    LogicType logicType;

    QueryCondition right;

    public enum queryType{
        lt,le,eq,ge,gt,like
    }

    public enum LogicType {
        and, or
    }

    public QueryCondition() {

    }

    public QueryCondition(String fieldName, queryType type, String value) {
        this.fieldName = fieldName;
        this.queryType = type;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public QueryCondition.queryType getQueryType() {
        return queryType;
    }

    public String getValue() {
        return value;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setQueryType(QueryCondition.queryType queryType) {
        this.queryType = queryType;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public QueryCondition getLeft() {
        return left;
    }

    public void setLeft(QueryCondition left) {
        this.left = left;
    }

    public LogicType getLogicType() {
        return logicType;
    }

    public void setLogicType(LogicType logicType) {
        this.logicType = logicType;
    }

    public QueryCondition getRight() {
        return right;
    }

    public void setRight(QueryCondition right) {
        this.right = right;
    }

    public QueryCondition and(QueryCondition condition) {
        QueryCondition res = new QueryCondition();
        res.left = this;
        res.logicType = LogicType.and;
        res.right = condition;
        return res;
    }

    public QueryCondition or(QueryCondition condition) {
        QueryCondition res = new QueryCondition();
        res.left = this;
        res.logicType = LogicType.or;
        res.right = condition;
        return res;
    }
}
