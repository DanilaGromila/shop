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
        System.out.println();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        UserRepository userRepository = new UserRepository(sessionFactory);
        OrderRepository orderRepository = new OrderRepository(sessionFactory);
        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository(sessionFactory);
        ItemRepository itemRepository = new ItemRepository(sessionFactory);
        FeedbackRepository feedbackRepository = new FeedbackRepository(sessionFactory);
        RoleRepository roleRepository = new RoleRepository(sessionFactory);
        AuthorityRepository authorityRepository = new AuthorityRepository(sessionFactory);
        PaymentRepository paymentRepository = new PaymentRepository(sessionFactory);

        PaymentService paymentService = new PaymentService(paymentRepository);
        ItemService itemService = new ItemService(itemRepository);
        FeedbackService feedbackService = new FeedbackService(feedbackRepository, itemService);
        OrderDetailsService orderDetailsService = new OrderDetailsService(orderDetailsRepository);
        AuthorityService authorityService = new AuthorityService(authorityRepository);
        RoleService roleService = new RoleService(roleRepository);
        OrderService orderService = new OrderService(orderRepository);
        UserService userService = new UserService(userRepository);

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