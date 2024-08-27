package com.microservices.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.order.dto.OrderReq;
import com.microservices.order.messages.OrderProcessingConfig;
import com.microservices.order.messages.OrderProducer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private RabbitTemplate rabbitTemplate;
	@Mock
	private ObjectMapper objectMapper;
	@InjectMocks
	private OrderProducer orderProducer;
	@Captor
	private ArgumentCaptor<String> messageCaptor;

	@Test
	public void testProcessOrderCreation() throws JsonProcessingException {
		OrderReq orderReq = OrderReq.builder()
				.email("test@mail.com")
				.amount(100.0)
				.build();

		String orderJson = """
                {
                    "email": "test@mail.com",
                    "amount": 100.0
                }
                """;
		when(objectMapper.writeValueAsString(orderReq)).thenReturn(orderJson);

		orderProducer.processOrderCreation(orderReq);

		verify(rabbitTemplate).convertAndSend(
				eq(OrderProcessingConfig.ORDER_DIRECT_EXCHANGE),
				eq(OrderProcessingConfig.ROUTING_KEY_ORDER_CREATE),
				messageCaptor.capture()
		);

		assertEquals(orderJson, messageCaptor.getValue());
	}

}
