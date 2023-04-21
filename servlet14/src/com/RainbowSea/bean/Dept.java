package com.RainbowSea.bean;

import java.io.Serializable;
import java.util.Objects;

public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deptno;
    private String dname;
    private String loc;


    public Dept() {
    }

    public Dept(String deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }


    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dept)) return false;
        Dept dept = (Dept) o;
        return Objects.equals(getDeptno(), dept.getDeptno()) && Objects.equals(getDname(), dept.getDname()) && Objects.equals(getLoc(), dept.getLoc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeptno(), getDname(), getLoc());
    }


    @Override
    public String toString() {
        return "Dept{" +
                "deptno='" + deptno + '\'' +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}

