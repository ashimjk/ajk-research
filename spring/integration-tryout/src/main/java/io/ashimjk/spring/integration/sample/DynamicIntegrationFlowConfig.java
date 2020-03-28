package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class DynamicIntegrationFlowConfig {

    //@Autowired
    //private AbstractServerConnectionFactory server1;
    //
    //@Autowired
    //private IntegrationFlowContext flowContext;
    //
    //@Test
    //public void testTcpGateways() {
    //    TestingUtilities.waitListening(this.server1, null);
    //
    //    IntegrationFlow flow = f -> f
    //            .handle(Tcp.outboundGateway(Tcp.netClient("localhost", this.server1.getPort())
    //                    .serializer(TcpCodecs.crlf())
    //                    .deserializer(TcpCodecs.lengthHeader1())
    //                    .id("client1"))
    //                    .remoteTimeout(m -> 5000))
    //            .transform(Transformers.objectToString());
    //
    //    IntegrationFlowRegistration theFlow = this.flowContext.registration(flow).register();
    //    assertThat(theFlow.getMessagingTemplate().convertSendAndReceive("foo", String.class), equalTo("FOO"));
    //}

}
