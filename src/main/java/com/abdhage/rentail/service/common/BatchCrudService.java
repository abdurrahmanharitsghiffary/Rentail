package com.abdhage.rentail.service.common;

import java.util.List;

public interface BatchCrudService<R, CDTO, UDTO, ID> extends BaseCrudService<R, CDTO, UDTO, ID> {
    public void destroyMany(List<ID> ids);

    public void updateMany(List<ID> ids, UDTO dto);
}
