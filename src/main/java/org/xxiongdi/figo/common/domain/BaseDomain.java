package org.xxiongdi.figo.common.domain;

import java.util.Date;

/**
 * .
 * User: figo
 * Date: 12-8-9
 * Time: 下午8:56
 *
 */
public class BaseDomain {
    private String id;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
