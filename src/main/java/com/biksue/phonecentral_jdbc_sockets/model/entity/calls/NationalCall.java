package com.biksue.phonecentral_jdbc_sockets.model.entity.calls;

import com.biksue.phonecentral_jdbc_sockets.model.entity.abstracts.Call;

public class NationalCall extends Call {

    public NationalCall(Long remittentId, Long destinationId, Long lifeTime, Long date) {
        super(remittentId, destinationId, lifeTime, date);
    }

    public NationalCall() {
        super();
    }

    @Override
    public void estimatePrice() {

    }
}
