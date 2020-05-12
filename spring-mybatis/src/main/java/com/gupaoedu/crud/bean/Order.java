package com.gupaoedu.crud.bean;

import java.util.Date;

public class Order {
    private Integer id;

    private String ordername;

    private Integer orderno;

    private Integer productno;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername == null ? null : ordername.trim();
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Integer getProductno() {
        return productno;
    }

    public void setProductno(Integer productno) {
        this.productno = productno;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}