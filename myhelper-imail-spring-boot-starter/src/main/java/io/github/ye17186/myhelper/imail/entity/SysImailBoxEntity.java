package io.github.ye17186.myhelper.imail.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.ye17186.myhelper.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@TableName("tb_sys_imail_box")
public class SysImailBoxEntity extends BaseEntity {

    /**
     * 站内信ID
     */
    private Long imailId;

    /**
     * 收件人唯一标识
     */
    private String loginKey;

    /**
     * 阅读状态。Y=已读；N=未读
     */
    private String readStatus;

    /**
     * 阅读时间
     */
    private LocalDateTime readTime;

    /**
     * 是否从信箱删除
     */
    private Boolean boxDeleted;

    /**
     * 信箱删除时间
     */
    private LocalDateTime boxDeletedTime;

}
