package io.github.wikimore.common.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.zip.CRC32;


/**
 * 数据库路由器
 * <p>
 * 先判断是否有独立的分表
 * <p>
 * 再走统一逻辑
 *
 * @author Ted Wang
 */
public class DbRouter implements Router, InitializingBean {
  private String database;
  private String tableName;
  private Boolean conditionRoute = false;

  @Autowired(required = false)
  @Qualifier("dbDataSource")
  private DataSource dataSource;

  private Map<Object, String> singleTableUids;
  private TreeMap<Long, String> nodes;

  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public void setConditionRoute(Boolean conditionRoute) {
    this.conditionRoute = conditionRoute;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Shard route(Object key) {
    String tableSuffix = getTableSuffix(key);
    return new Shard(tableSuffix);
  }

  protected String getTableSuffix(Object key) {
    String tableSuffix = null;
    if (conditionRoute) {
      tableSuffix = singleTableUids.get(key);
    }
    if (tableSuffix != null) {
      return tableSuffix;
    } else {
      long num = getNode(key.toString());
      Entry<Long, String> node = nodes.ceilingEntry(num);
      if (node == null) {
        node = nodes.firstEntry();
      }
      tableSuffix = node.getValue();
    }
    return tableSuffix;
  }

  private long getNode(String key) {
    CRC32 crc32 = new CRC32();
    try {
      crc32.update(key.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      throw new UnsupportedCharsetException("Unsupported UTF-8 encoding.");
    }
    return crc32.getValue();
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    nodes = new TreeMap<Long, String>();
    // 创建与MySQL数据库的连接类的实例
    Connection connection = dataSource.getConnection();
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      String sql = "select node, table_name from " + database + "." + tableName + "_node order by node";
      statement = connection.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        nodes.put(resultSet.getLong(1), resultSet.getString(2).replaceAll(tableName, ""));
      }
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
      if (statement != null) {
        statement.close();
      }
      if (connection != null) {
        connection.close();
      }
    }
    if (conditionRoute) {
      singleTableUids = new HashMap<Object, String>();
      try {
        connection = dataSource.getConnection();
        String sql = "select key, node from " + database + "." + tableName + "_custom_node";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        Object key = null;
        String node = null;
        while (resultSet.next()) {
          key = resultSet.getObject(1);
          node = resultSet.getString(2);
          singleTableUids.put(key, node);
        }
      } finally {
        if (resultSet != null) {
          resultSet.close();
        }
        if (statement != null) {
          statement.close();
        }
        if (connection != null) {
          connection.close();
        }
      }
    }
  }

}
