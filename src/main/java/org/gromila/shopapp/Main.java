package org.gromila.shopapp;

import org.gromila.shopapp.dto.FeedbackCreateDto;
import org.gromila.shopapp.dto.ItemCreateDto;
import org.gromila.shopapp.mapper.*;
import org.gromila.shopapp.repository.*;
import org.gromila.shopapp.service.*;
import org.gromila.shopapp.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        UserRepository userRepository = new UserRepository(sessionFactory);
        OrderRepository orderRepository = new OrderRepository(sessionFactory);
        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository(sessionFactory);
        ItemRepository itemRepository = new ItemRepository(sessionFactory);
        FeedbackRepository feedbackRepository = new FeedbackRepository(sessionFactory);
        RoleRepository roleRepository = new RoleRepository(sessionFactory);
        AuthorityRepository authorityRepository = new AuthorityRepository(sessionFactory);
        PaymentRepository paymentRepository = new PaymentRepository(sessionFactory);

        PaymentMapper paymentMapper = new PaymentMapper();
        FeedbackMapper feedbackMapper = new FeedbackMapper();
        ItemMapper itemMapper = new ItemMapper(feedbackMapper);
        OrderDetailsMapper orderDetailsMapper = new OrderDetailsMapper();
        OrderMapper orderMapper = new OrderMapper(orderDetailsMapper, paymentMapper);
        AuthorityMapper authorityMapper = new AuthorityMapper();
        RoleMapper roleMapper = new RoleMapper();
        UserMapper userMapper = new UserMapper();

        PaymentService paymentService = new PaymentService(paymentRepository, paymentMapper);
        ItemService itemService = new ItemService(itemRepository, itemMapper);
        FeedbackService feedbackService = new FeedbackService(feedbackRepository, feedbackMapper, itemService);
        OrderDetailsService orderDetailsService = new OrderDetailsService(orderDetailsRepository, orderDetailsMapper);
        AuthorityService authorityService = new AuthorityService(authorityRepository, authorityMapper);
        RoleService roleService = new RoleService(roleRepository, roleMapper);
        OrderService orderService = new OrderService(orderRepository, orderMapper);
        UserService userService = new UserService(userRepository, userMapper);

        ItemCreateDto itemDto = new ItemCreateDto();
        itemDto.setName("test item");
        Long testItemId = itemService.create(itemDto);


        FeedbackCreateDto feedback1 = new FeedbackCreateDto("cool", 5);
        feedbackService.create(testItemId, feedback1);

        System.out.println(itemService.findById(testItemId));

        FeedbackCreateDto feedback2 = new FeedbackCreateDto("bad", 2);
        feedbackService.create(testItemId, feedback2);

        System.out.println(itemService.findById(testItemId));

    }
}