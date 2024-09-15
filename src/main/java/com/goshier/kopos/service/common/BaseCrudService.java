package com.goshier.kopos.service.common;

import java.util.List;

public interface BaseCrudService<R, CDTO, UDTO, ID> {
    public List<R> findAll();

    public R create(CDTO dto);

    public R update(ID id, UDTO dto);

    public void destroy(ID id);

    public R findOne(ID id);
}
