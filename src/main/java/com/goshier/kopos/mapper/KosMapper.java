package com.goshier.kopos.mapper;

import com.goshier.kopos.dtos.kos.CreateKosDto;
import com.goshier.kopos.dtos.kos.UpdateKosDto;
import com.goshier.kopos.model.Accommodation;
import com.goshier.kopos.response.KosResponse;
import org.mapstruct.*;

@Mapper
public interface KosMapper {
    public KosResponse kosToKosResponse(Accommodation accommodation);

    public Accommodation kosResponseToKos(KosResponse kos);

    public Accommodation createKosDtoToKos(CreateKosDto kos);

    public Accommodation updateKosDtoToKos(UpdateKosDto kos);

}
