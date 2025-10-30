package io.github.ye17186.myhelper.core.web.context.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ye17186
 */
@Setter
@Getter
public class DefaultContextUser extends MhContextUser {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 所属部门ID
     */
    private Long departmentId;

    /**
     * 所属部门名称
     */
    private String departmentTitle;

    /**
     * 拥有的角色码集合
     */
    private List<String> roles;

    /**
     * 拥有的权限码集合
     */
    private List<String> permissions;

    /**
     * 最近登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 是否为默认角色（系统自带的超管）
     */
    private boolean isDefaultRole;

    /**
     * 登录后的个人主页
     */
    private String homePath;

    /**
     * 具备权限的部门集合
     */
    private List<Long> permissionDepartmentIds;
}
