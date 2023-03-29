package com.example.eemart.serviceImpl;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.model.Order;
import com.example.eemart.model.OrderItem;
import com.example.eemart.pojos.CartItemDto;
import com.example.eemart.pojos.CartItemsResponseDto;
import com.example.eemart.pojos.StripeResponse;
import com.example.eemart.repository.OrderItemRepository;
import com.example.eemart.repository.OrderRepository;
import com.example.eemart.service.AuthenticationService;
import com.example.eemart.service.CartService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.example.eemart.config.MessageStrings.STRIPE_API_KEY;

@Service
public class StripeService {
//    public ResponseEntity<String> createSession(String successUrl, String priceId, Long quantity) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(STRIPE_API_KEY, "");
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("success_url", successUrl);
//        map.add("line_items[0][price]", priceId);
//        map.add("line_items[0][quantity]", String.valueOf(quantity));
//        map.add("mode", "payment");
//
//        String url = "https://api.stripe.com/v1/checkout/sessions";
//
//        return restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                new HttpEntity<>(map, headers),
//                String.class
//        );
//
//    }

//    @Value("${STRIPE_SECRET_KEY}")
//    private String apiKey;

//    public String createCheckoutSession(String priceId, String successUrl, String cancelUrl)
//            throws StripeException {
//        Stripe.apiKey = apiKey;
//        SessionCreateParams params =
//                SessionCreateParams.builder()
//                        .setMode(SessionCreateParams.Mode.PAYMENT)
//                        .setSuccessUrl(successUrl)
//                        .setCancelUrl(cancelUrl)
//                        .addLineItem(
//                                SessionCreateParams.LineItem.builder()
//                                        .setQuantity(1L)
//                                        .setPrice(priceId)
//                                        .build())
//                        .build();
//        Session session = Session.create(params);
//        return session.getId();
//    }

    @Autowired
    CartService cartService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    public StripeResponse createSession (String token) throws StripeException {
        Stripe.apiKey = "sk_test_51Mo6F7CL9lo23pthsfZQIxzd4O1ieYHYykS51ihXP0NHtBGeXWVp4LONRK9EXh77ZJDmoEvs2j9OvF77GzR14CdS009fp06GzE";

        List<Object> lineItems = new ArrayList<>();
        List<CartItemDto> cartItems = cartService.getCartItems(token).getCartItems();
        for (CartItemDto cart : cartItems){
            Map<String, Object> lineItem = new HashMap<>();
            //lineItem.put("price", cart.getProduct().getPrice());
            lineItem.put("price", createStripePrice((int) cart.getProduct().getPrice(), cart.getProduct().getName()));
            lineItem.put("quantity", cart.getQuantity());
            lineItems.add(lineItem);
        }
        Map<String, Object> params = new HashMap<>();
        params.put(
                "success_url",
                "https://localhost:3000/dashboard"
        );
        params.put("line_items", lineItems);
        params.put("mode", "payment");
        Session session = Session.create(params);
        return new StripeResponse(session.getUrl(), session.getId());
    }
    public String createStripePrice (Integer amount, String productName) throws StripeException {
        Stripe.apiKey = "sk_test_51Mo6F7CL9lo23pthsfZQIxzd4O1ieYHYykS51ihXP0NHtBGeXWVp4LONRK9EXh77ZJDmoEvs2j9OvF77GzR14CdS009fp06GzE";

        Map<String, Object> params = new HashMap<>();
        params.put("unit_amount", amount * 100);
        params.put("currency", "ngn");
        params.put("product", createStripeProduct(productName));

        Price price = Price.create(params);
        return price.getId();
    }
    public String createStripeProduct(String productName) throws StripeException {
        Stripe.apiKey = "sk_test_51Mo6F7CL9lo23pthsfZQIxzd4O1ieYHYykS51ihXP0NHtBGeXWVp4LONRK9EXh77ZJDmoEvs2j9OvF77GzR14CdS009fp06GzE";

        Map<String, Object> params = new HashMap<>();
        params.put("name", productName);

        Product product = Product.create(params);
        return  product.getId();
    }
    public ApiResponse<?> placeOrder(String token, String sessionId) {
        // first let get cart items for the user
        CartItemsResponseDto userCart = cartService.getCartItems(token);

        List<CartItemDto> listOfItemsInUserCart = userCart.getCartItems();

        // create the order and save it
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setSessionId(sessionId);
        newOrder.setUser(authenticationService.getUserByToken(token));
        newOrder.setTotalPrice(userCart.getTotalCost());
        orderRepository.save(newOrder);

        for (CartItemDto eachItem : listOfItemsInUserCart) {
            // create orderItem and save each one
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(eachItem.getProduct().getPrice());
            orderItem.setProduct(eachItem.getProduct());
            orderItem.setQuantity(eachItem.getQuantity());
            orderItem.setOrder(newOrder);
            // add to order item list
            orderItemRepository.save(orderItem);
        }
        cartService.deleteUserCartItems(authenticationService.getUserByToken(token));
        return new ApiResponse<>(HttpStatus.OK,true, "Order placed Successfully", null);
    }
}
