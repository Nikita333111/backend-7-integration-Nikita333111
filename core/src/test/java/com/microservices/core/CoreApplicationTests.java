package com.microservices.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.core.entity.Order;
import com.microservices.core.messages.ProducerService;
import com.microservices.core.messages.ReceiverService;
import com.microservices.core.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CoreApplicationTests {

	@Test
	void contextLoads() {
	}

	private ObjectMapper objectMapper;
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private ProducerService producerService;

	@InjectMocks
	private ReceiverService receiverService;

	@Captor
	private ArgumentCaptor<Order> orderCaptor;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		objectMapper = new ObjectMapper(); // Создаем реальный экземпляр ObjectMapper
		receiverService = new ReceiverService(objectMapper, orderRepository, producerService); // Внедряем его вручную
	}

	@Test
	public void createOrderReceiveTest() throws JsonProcessingException {
		String orderJson = """
                {
                    "email": "test@mail.com",
                    "amount": 100.0
                }
                """;

		Order order = Order.builder()
				.email("test@mail.com")
				.amount(100.0)
				.paid(false)
				.build();

		//when(objectMapper.readValue(orderJson, Order.class)).thenReturn(order);
		when(orderRepository.save(any())).thenReturn(order);

		receiverService.createOrder(orderJson);

		verify(orderRepository).save(orderCaptor.capture());
		Order actualOrder = orderCaptor.getValue();

		// Проверяем, что поля захваченного объекта соответствуют ожидаемым значениям
		assertEquals(order.getAmount(), actualOrder.getAmount());
		assertEquals(order.getEmail(), actualOrder.getEmail());

		verify(producerService).sendSuccessCreationNotification(order);
	}

}
