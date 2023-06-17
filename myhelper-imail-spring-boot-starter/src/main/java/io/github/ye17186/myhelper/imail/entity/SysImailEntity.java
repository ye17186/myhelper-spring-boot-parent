package io.github.ye17186.myhelper.imail.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.ye17186.myhelper.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@TableName("tb_sys_imail")
public class SysImailEntity extends BaseEntity {

    /**
     * 站内信分类
     *
     */
    private String classify;

    /**
     * 站内信标题
     */
    private String title;

    /**
     * 站内信描述
     */
    private String description;

    /**
     * 站内信内容
     */
    private String content;

    /**
     * 详情链接
     */
    private String link;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;
}
