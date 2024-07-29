package com.hansom.mp.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author anonymous
 * @since 2024-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String menu;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
