package com.myland.platform.entity;

import java.io.Serializable;
import java.util.Date;

import com.myland.framework.base.BaseBean;



/**
 * 行政区划表
 *
 * @author myland
 * @email feiying
 * @date 2019-01-21 11:24:34
 */
public class AreaDO extends BaseBean {
    private static final long serialVersionUID = 1L;

        //ID 主键。行政区划代码
        private Integer id;
        //PID 上级行政区划代码
        private Integer pid;
        //PIDS 各个上级行政区划ID串，以英文逗号连接
        private String pids;
        //行政区划名称
        private String name;
        //级别
        private Integer lev;
    
        /**
         * 设置：ID 主键。行政区划代码
         */
        public void setId(Integer id) {
            this.id = id;
        }
     /**
         * 获取：ID 主键。行政区划代码
         */
        public Integer getId() {
            return id;
        }
        /**
         * 设置：PID 上级行政区划代码
         */
        public void setPid(Integer pid) {
            this.pid = pid;
        }
     /**
         * 获取：PID 上级行政区划代码
         */
        public Integer getPid() {
            return pid;
        }
        /**
         * 设置：PIDS 各个上级行政区划ID串，以英文逗号连接
         */
        public void setPids(String pids) {
            this.pids = pids;
        }
     /**
         * 获取：PIDS 各个上级行政区划ID串，以英文逗号连接
         */
        public String getPids() {
            return pids;
        }
        /**
         * 设置：行政区划名称
         */
        public void setName(String name) {
            this.name = name;
        }
     /**
         * 获取：行政区划名称
         */
        public String getName() {
            return name;
        }
        /**
         * 设置：级别
         */
        public void setLev(Integer lev) {
            this.lev = lev;
        }
     /**
         * 获取：级别
         */
        public Integer getLev() {
            return lev;
        }
    }
