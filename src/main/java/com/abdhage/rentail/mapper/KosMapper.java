package com.abdhage.rentail.mapper;

import com.abdhage.rentail.dtos.kos.CreateKosDto;
import com.abdhage.rentail.dtos.kos.UpdateKosDto;
import com.abdhage.rentail.model.Accommodation;
import com.abdhage.rentail.response.KosResponse;
import org.mapstruct.*;

@Mapper
public interface KosMapper {
    public KosResponse kosToKosResponse(Accommodation accommodation);

    public Accommodation kosResponseToKos(KosResponse kos);

    public Accommodation createKosDtoToKos(CreateKosDto kos);

    public Accommodation updateKosDtoToKos(UpdateKosDto kos);

}
