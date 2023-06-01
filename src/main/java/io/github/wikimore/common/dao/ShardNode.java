package io.github.wikimore.common.dao;

/**
 * 分表信息model
 * 
 * @author Ted Wang
 */
public class ShardNode {
    private Long node;
    private String tableName;

    public Long getNode() {
        return node;
    }

    public void setNode(Long node) {
        this.node = node;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "ShardNode [node=" + node + ", tableName=" + tableName + "]";
    }
}
