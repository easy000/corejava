package com.xiong.spring.example.zhuru;

import java.util.ArrayList;
import java.util.List;

public class DbSourceConfig {

    private List<String> partDataBaseList = new ArrayList<>();

    private List<String> partTableList = new ArrayList<>();

    /**
     * 分表类型1:一级物业 2：项目
     */
    private String shardingType;

    /**
     * 所属的系统模块
     */
    private String systemModule;

    private String systemTag;

    private String systemRedisPrefix;

    public List<String> getPartDataBaseList() {
        return partDataBaseList;
    }

    public void setPartDataBaseList(List<String> partDataBaseList) {
        this.partDataBaseList = partDataBaseList;
    }

    public  List<String> getPartTableList() {
        return partTableList;
    }

    public  void setPartTableList(List<String> partTableList) {
        this.partTableList = partTableList;
    }

    public  String getShardingType() {
        return shardingType;
    }

    public  void setShardingType(String shardingType) {
        this.shardingType = shardingType;
    }

    public  String getSystemModule() {
        return systemModule;
    }

    public  void setSystemModule(String systemModule) {
        this.systemModule = systemModule;
    }

    public String getSystemTag() {
        return systemTag;
    }

    public void setSystemTag(String systemTag) {
        this.systemTag = systemTag;
    }

    public String getSystemRedisPrefix() {
        return systemRedisPrefix;
    }

    public void setSystemRedisPrefix(String systemRedisPrefix) {
        this.systemRedisPrefix = systemRedisPrefix;
    }
}