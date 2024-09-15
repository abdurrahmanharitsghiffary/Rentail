package com.abdhage.rentail.service.common;

import java.util.List;

public interface AuthorizedBatchCrudService<R, CDTO, UDTO, ID, UID> extends AuthorizedCrudService<R, CDTO, UDTO, ID, UID> {
    public void destroyMany(List<ID> ids, UID userId);

    public void updateMany(List<ID> ids, UDTO dto, UID userId);
}
