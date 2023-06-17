package io.github.ye17186.myhelper.imail.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.ye17186.myhelper.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("tb_sys_imail_group")
public class SysImailGroupEntity extends BaseEntity {

    /**
     * 内部信ID
     */
    private Long imailId;

    /**
     * 收件组类型
     */
    private String groupType;

    /**
     * 收件组唯一标识
     */
    private String groupKey;
}
