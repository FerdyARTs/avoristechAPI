package com.plexus.fernando.avoristechAPI.persitence.validator;

import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaDto;

public interface ReservaValidator {

    boolean validarPayload(ReservaDto reservaDto);
}
