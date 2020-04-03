package io.rushb.messageapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统连接配置实体
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/03 16:31
 */
@Data
@Entity
@Table(name = "sys_connection_config")
public class ConnectionEntity implements Serializable {

    private static final long serialVersionUID = 8190940645915333036L;

    /**
     * 连接配置ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connection_id")
    private Long connectionId;
    /**
     * 连接配置编码
     */
    @Column(name = "connection_code")
    private String connectionCode;
    /**
     * 连接配置名称
     */
    @Column(name = "connection_name")
    private String connectionName;
    /**
     * 协议/方式
     */
    @Column(name = "protocol")
    private String protocol;
    /**
     * 域名/IP/地址
     */
    @Column(name = "domain")
    private String domain;
    /**
     * 端口
     */
    @Column(name = "port")
    private String port;
    /**
     * 参数1
     */
    @Column(name = "arguments1")
    private String arguments1;
    /**
     * 参数2
     */
    @Column(name = "arguments2")
    private String arguments2;
    /**
     * 参数3
     */
    @Column(name = "arguments3")
    private String arguments3;
    /**
     * 参数4
     */
    @Column(name = "arguments4")
    private String arguments4;
    /**
     * 参数5
     */
    @Column(name = "arguments5")
    private String arguments5;
    /**
     * 账号
     */
    @Column(name = "username")
    private String username;
    /**
     * 密码/授权
     */
    @Column(name = "password")
    private String password;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 自定义字段1
     */
    @Column(name = "user_item1")
    private String userItem1;
    /**
     * 自定义字段2
     */
    @Column(name = "user_item1")
    private String userItem2;
    /**
     * 自定义字段3
     */
    @Column(name = "user_item3")
    private String userItem3;
    /**
     * 自定义字段4
     */
    @Column(name = "user_item4")
    private String userItem4;
    /**
     * 自定义字段5
     */
    @Column(name = "user_item5")
    private String userItem5;
    /**
     * 更新人
     */
    @Column(name = "update_by")
    private Long updateBy;
    /**
     * 更新日期
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
