package io.github.wikimore.common.dao;

/**
 * domain model基类
 * 
 * @author Ted Wang
 */
public class BaseModel {
    /**
     * 分页参数
     */
    private transient Limit limit;
    /**
     * 分表参数
     */
    private transient Shard shard;

    public BaseModel() {
    }

    /**
     * @param shard the shard to set
     */
    public void setShard(Shard shard) {
        this.shard = shard;
    }

    /**
     * @return the limit
     */
    public Limit getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    /**
     * @return the shard
     */
    public Shard getShard() {
        return shard;
    }
}
