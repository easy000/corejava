package com.easy;

/**
 * 包:       com.easy
 * 类名称:    FunctionInterFace
 * 类描述:
 * 创建人:    wangxiong
 * 创建时间:  2019/8/13 18:28
 * 修改人:    Administrator
 * 修改时间:  2019/8/13 18:28
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */
@FunctionalInterface
public interface InvocationHandler<K,O> {

    public K invoke(O obj);

}

