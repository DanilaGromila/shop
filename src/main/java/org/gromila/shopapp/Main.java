package org.gromila.shopapp;

import org.gromila.shopapp.dto.UserCreateDto;
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
        FeedbackService feedbackService = new FeedbackService(feedbackRepository, feedbackMapper);
        ItemService itemService = new ItemService(itemRepository, itemMapper);
        OrderDetailsService orderDetailsService = new OrderDetailsService(orderDetailsRepository, orderDetailsMapper);
        AuthorityService authorityService = new AuthorityService(authorityRepository, authorityMapper);
        RoleService roleService = new RoleService(roleRepository, roleMapper);
        OrderService orderService = new OrderService(orderRepository, orderMapper);
        UserService userService = new UserService(userRepository, userMapper);


        UserCreateDto createDto = new UserCreateDto("Andrey", "Yureiy", "Psix", "asdfa");
        userService.create(createDto);

        createDto = new UserCreateDto("Daniul", "Mel", "gromila", "kjfa;dl");
        userService.create(createDto);

        createDto = new UserCreateDto("Slava", "Yankovic", "Kommandos", "jfkdl;as");
        userService.create(createDto);

        orderService.create(6L);
        userService.addRole(6L, 7L);

        Long admin = roleService.create("admin");
        Long readusers = authorityService.create("readusers");

        roleService.addAuthority(admin, readusers);

        System.out.println(userService.findById(6L));
    }
}