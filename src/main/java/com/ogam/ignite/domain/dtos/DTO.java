package com.ogam.ignite.domain.dtos;

import com.ogam.ignite.domain.requests.Request;

public interface DTO <T>{

    T transformRequestToDTO(Request request);
}
