package io.github.wikimore.common.dao;

/**
 * mysql limit分页查询参数类
 * 
 * @author Ted Wang
 */
public class Limit {
    /**
     * 偏移记录
     */
    public int offset = 0;
    /**
     * 最大记录数
     */
    public int maxRows = -1;
    /**
     * 当前页码
     */
    public int currentPageIndex;
    /**
     * 每页最大记录数
     */
    public int pageSize;

    protected Limit() {
    }

    public int getCurrentPageIndex() {
        return offset / maxRows + 1;
    }

    public int getPageSize() {
        return maxRows;
    }

    /**
     * 以mysql limit语法的角度初始化, offset从0开始
     * 
     * @param maxRows 查询结果的最大行数
     */
    public static Limit newInstanceForLimit(int maxRows) {
        return newInstanceForLimit(0, maxRows);
    }

    /**
     * 以mysql limit语法的角度初始化
     * 
     * @param offset 偏移记录,从0开始
     * @param maxRows 查询结果的最大行数
     */
    public static Limit newInstanceForLimit(int offset, int maxRows) {
        Limit limit = new Limit();
        limit.offset = offset;
        limit.maxRows = maxRows;
        limit.currentPageIndex = offset / maxRows + 1;
        limit.pageSize = maxRows;
        return limit;
    }

    /**
     * 以分页的角度初始化
     * 
     * @param currentPageIndex 当前页码(从1开始计数)
     * @param pageSize 每页显示记录数
     */
    public static Limit newInstanceForPage(int currentPageIndex, int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize can not be negative");
        }
        if (currentPageIndex < 0) {
            throw new IllegalArgumentException("currentPageIndex can not be negative,it begins with 1!");
        }
        if (currentPageIndex == 0) {
            throw new IllegalArgumentException("currentPageIndex begins with 1,not 0!");
        }
        Limit limit = new Limit();
        limit.offset = pageSize * (currentPageIndex - 1);
        limit.maxRows = pageSize;
        limit.currentPageIndex = currentPageIndex;
        limit.pageSize = pageSize;
        return limit;
    }

    /**
     * 返回在mysql sql中可直接使用的limit语句,如" limit 0,2"
     */
    @Override
    public String toString() {
        if (offset == 0 && maxRows == -1) {
            return "";
        }
        if (offset == 0) {
            return " limit " + maxRows;
        }
        return " limit " + offset + "," + maxRows;
    }
}
