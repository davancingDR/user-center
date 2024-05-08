package com.rqh.user.base;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class BaseQueryPage implements Serializable {

    private static final long serialVersionUID = -2159963963668504703L;

    @Setter
    @Min(value = 1, message = "页数不能为空")
    private Integer pageNum = 1;

    @Setter
    @Min(value = 1, message = "每页条数不能为空")
    private Integer pageSize = 10;

    @Getter
    private Integer startIndex;

    public Integer getPageSize() {
        return pageSize == null || pageSize < 1 ? 10 : pageSize;
    }

    public Integer getPageNum() {
        return pageNum == null || pageNum < 1 ? 1 : pageNum;
    }

    public <T> Page<T> buildPage() {
        return new Page<>(this.pageNum, this.pageSize);
    }

    public void setStartIndex(){
        this.startIndex = (getPageNum() - 1) * getPageSize();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
