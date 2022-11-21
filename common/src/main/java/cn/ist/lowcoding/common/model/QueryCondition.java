package cn.ist.lowcoding.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCondition {
    String fieldName;

    QueryType queryType;

    Object value;

    QueryCondition left;

    LogicType logicType;

    QueryCondition right;

    public QueryCondition(String fieldName, QueryType queryType, Object value) {
        this.fieldName = fieldName;
        this.queryType = queryType;
        this.value = value;
    }

    public QueryCondition(QueryCondition left, LogicType logicType, QueryCondition right) {
        this.left = left;
        this.logicType = logicType;
        this.right = right;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public boolean isRelationQuery() {
        if (this.fieldName == null) return false;
        else return this.fieldName.contains(".");
    }

    @JsonIgnore
    public String getRelationName() {
        String[] splitted = this.fieldName.split("\\.");
        Assert.isTrue(splitted.length == 2, "只允许一层关联查询");
        return splitted[0];
    }

    @JsonIgnore
    public String getRelationProperty() {
        String[] splitted = this.fieldName.split("\\.");
        Assert.isTrue(splitted.length == 2, "只允许一层关联查询");
        return splitted[1];
    }

    public enum QueryType {
        lt("<"),
        le("<="),
        eq("="),
        ge(">="),
        gt(">"),
        like("like");

        public String symbol;

        QueryType(String symbol) {
            this.symbol = symbol;
        }
    }

    public enum LogicType {
        and,
        or
    }
}
