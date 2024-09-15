package com.abdhage.rentail.service.common;


import java.util.List;

public interface AuthorizedCrudService<R, CDTO, UDTO, ID, UID> {
    public List<R> findAll();

    public R create(CDTO dto, UID userId);

    public R update(ID id, UID userId, UDTO dto);

    public void destroy(ID id, UID userId);

    public R findOne(ID id);
}
