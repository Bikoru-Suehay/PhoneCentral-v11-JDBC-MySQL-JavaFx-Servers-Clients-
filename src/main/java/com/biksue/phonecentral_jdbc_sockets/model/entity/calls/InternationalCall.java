package com.biksue.phonecentral_jdbc_sockets.model.entity.calls;

import com.biksue.phonecentral_jdbc_sockets.model.entity.abstracts.Call;

public class InternationalCall extends Call {
    public InternationalCall(Long remittentId, Long destinationId, Long lifeTime, Long date) {
        super(remittentId, destinationId, lifeTime, date);
    }

    public InternationalCall() {
        super();
    }

    @Override
    public void estimatePrice() {

    }
}
