package by.tms.abayonlinestore.listener;

import by.tms.abayonlinestore.entity.Cart;

import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class Listener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("cart", new Cart());
    }
}
