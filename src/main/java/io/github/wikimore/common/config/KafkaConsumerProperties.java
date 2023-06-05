package io.github.wikimore.common.config;

/**
 * @author Ted Wang
 */
public class KafkaConsumerProperties {
  private String groupId;
  private String autoOffsetReset = "latest";

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getAutoOffsetReset() {
    return autoOffsetReset;
  }

  public void setAutoOffsetReset(String autoOffsetReset) {
    this.autoOffsetReset = autoOffsetReset;
  }
}
