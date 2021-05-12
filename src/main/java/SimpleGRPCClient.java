import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import mtech.account_api.AccountServiceGrpc;

import javax.net.ssl.SSLException;
import java.io.File;

public class SimpleGRPCClient {

    public static void main(String[] args) throws InterruptedException, SSLException {

        Metadata header=new Metadata();
        Metadata.Key<String> key =
                Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
        String token = "add your token here";
        header.put(key, token);

        ManagedChannel channel =
                ManagedChannelBuilder.forAddress("accounts-v1.grpc.qa-v2.mrbuilder.io", 443)
                .build();


        AccountServiceGrpc.AccountServiceBlockingStub stub =
                AccountServiceGrpc.newBlockingStub(channel);

        stub = io.grpc.stub.MetadataUtils.attachHeaders(stub, header);

       var list = stub.getInvoicingMethodList(Empty.newBuilder().build());

        System.out.println(list);

        channel.shutdown();
    }
}
