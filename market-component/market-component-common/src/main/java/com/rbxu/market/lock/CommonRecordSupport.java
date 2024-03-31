package com.rbxu.market.lock;

import java.util.List;
import java.util.function.Function;

public class CommonRecordSupport<T> implements RecordSupport<T> {

    private Function<String, List<T>> query;

    private Function<T, Boolean> update;

    private Function<T, Boolean> create;

    private Function<T, Boolean> delete;

    public CommonRecordSupport(Function<String, List<T>> query,
                               Function<T, Boolean> update,
                               Function<T, Boolean> create,
                               Function<T, Boolean> delete) {
        this.query = query;
        this.update = update;
        this.create = create;
        this.delete = delete;
    }



    public List<T> getByUniqueKey(String key) {
        return query.apply(key);
    }

    public Boolean update(T t) {
        return update.apply(t);
    }

    public Boolean create(T t) {
        return create.apply(t);
    }

    public Boolean delete(T t) {
        return delete.apply(t);
    }
}
