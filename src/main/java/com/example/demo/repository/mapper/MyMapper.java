package com.example.demo.repository.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-13 23:04
 **/
public interface MyMapper<T> extends Mapper<T>, SelectByIdsMapper<T>, MySqlMapper<T>, ConditionMapper<T> {
}
