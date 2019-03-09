package com.navatar.telemetry;

import com.referencepoint.proto.ParticleCastProto;
import com.referencepoint.proto.RxParticleCastGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class Telemetry {

    private ManagedChannel mChannel;

    private RxParticleCastGrpc.RxParticleCastStub mStub;

    public Telemetry() {
    }

    public Telemetry connect(String address, int port) {
        mChannel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        mStub = RxParticleCastGrpc.newRxStub(mChannel);
        return this;
    }


    public void disconnect() {
        mChannel.shutdown();
    }

    public Single<ParticleCastProto.CastSummary> castTelemetry(Flowable<ParticleCastProto.ParticleState> telemetry) {
        return mStub.castParticleState(telemetry);
    }

}
