package com.rbxu.market.lock;

import java.util.List;

public interface RecordSupport<T> {

    List<T> getByUniqueKey(String key);

    Boolean update(T t);

    Boolean create(T t);

    Boolean delete(T t);
}
