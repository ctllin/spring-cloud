package com.ctl.sharding.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * Created with Intellij IDEA.
 * @author ctl
 * @version 2019/03/14
 */
@Data
@TableName("member")
public class Member {

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private String mobile;
}
