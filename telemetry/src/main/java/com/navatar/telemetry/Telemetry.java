package com.navatar.telemetry;

import com.referencepoint.telemetry.proto.RxTelemetryGrpc;
import com.referencepoint.telemetry.proto.TelemetryOuterClass;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class Telemetry {

    private ManagedChannel mChannel;

    private RxTelemetryGrpc.RxTelemetryStub mStub;

    public Telemetry() {
    }

    public Telemetry connect(String address, int port) {
        mChannel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        mStub = RxTelemetryGrpc.newRxStub(mChannel);
        return this;
    }


    public void disconnect() {
        mChannel.shutdown();
    }

    public Single<TelemetryOuterClass.TelemetrySummary> castTelemetry(Flowable<TelemetryOuterClass.TelemetryData> telemetry) {
        return mStub.recordData(telemetry);
    }

}
