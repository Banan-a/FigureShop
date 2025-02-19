package product.controller;

import category.daos.CategoryDao;
import constants.Message;
import constants.Router;
import constants.StatusCode;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import product.daos.ProductDao;
import utils.GetParam;
import utils.Helper;
import product.models.Product;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import category.models.Category;

/**
 *
 * @author locnh
 */
@WebServlet(name = "ProductDetailController", urlPatterns = {"/" + Router.PRODUCT_DETAIL_CONTROLLER})
public class ProductDetailController extends HttpServlet {

    protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        ProductDao productDao = new ProductDao();
        CategoryDao categoryDAO = new CategoryDao();

        // get productId
        String productId = GetParam.getStringParam(request, "id", "Product", 0, 40, null);
        if (productId == null) {
            return false;
        }

        // find product
        Product product = productDao.getProductById(productId);
        if (product == null) {
            return false;
        }

        // find category
        Category category = categoryDAO.getCategoryByID(product.getCategoryId());

        // set attribute
        request.setAttribute("category", category);
        request.setAttribute("product", product);
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!getHandler(request, response)) {
                // forward on 404
                Helper.setAttribute(request, StatusCode.NOT_FOUND.getValue(),
                        Message.NOT_FOUND_ERROR_TITLE.getContent(),
                        Message.NOT_FOUND_ERROR_MESSAGE.getContent());
                request.getRequestDispatcher(Router.ERROR).forward(request, response);
                return;
            }
            // forward on 200
            request.getRequestDispatcher(Router.PRODUCT_DETAIL_PAGE).forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // forward on 500
            Helper.setAttribute(request, StatusCode.INTERNAL_SERVER_ERROR.getValue(),
                    Message.INTERNAL_ERROR_TITLE.getContent(),
                    Message.INTERNAL_ERROR_MESSAGE.getContent());
            request.getRequestDispatcher(Router.ERROR).forward(request, response);
        }
    }

    protected boolean postHandler(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        ProductDao productDao = new ProductDao();
        CategoryDao categoryDao = new CategoryDao();

        // get productId
        String productId = GetParam.getStringParam(request, "id", "Product", 0, 40, null);
        Integer quantity = GetParam.getIntParams(request, "quantity", "Quantity", 1, Integer.MAX_VALUE, 1);

        // find product
        Product product = productDao.getProductById(productId);
        if (product == null) {
            return false;
        }

        Category category = categoryDao.getCategoryByID(product.getCategoryId());

        // get products in cart
        HttpSession session = request.getSession();
        ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");
        if (products == null) {
            products = new ArrayList<Product>();
        }

        // set quantity for product in cart
        for (Product pro : products) {
            // return if product is already in cart
            if (pro.getId().equals(product.getId())) {
                quantity += pro.getQuantity();
                pro.setQuantity(quantity);
                request.setAttribute("id", productId);
                return true;
            }
        }
        product.setQuantity(quantity);
        product.setCategoryId(category.getName());

        // send product to session
        products.add(product);
        request.setAttribute("id", productId);
        session.setAttribute("products", products);
        return true;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!postHandler(request, response)) {
                // forward on 404
                Helper.setAttribute(request, StatusCode.NOT_FOUND.getValue(),
                        Message.NOT_FOUND_ERROR_TITLE.getContent(),
                        Message.NOT_FOUND_ERROR_MESSAGE.getContent());
                request.getRequestDispatcher(Router.ERROR).forward(request, response);
                return;
            }
            // forward on 200
            response.sendRedirect(Router.PRODUCT_DETAIL_CONTROLLER + "?id=" + request.getAttribute("id"));
        } catch (Exception e) {
            System.out.println(e);
            // forward on 500
            Helper.setAttribute(request, StatusCode.INTERNAL_SERVER_ERROR.getValue(),
                    Message.INTERNAL_ERROR_TITLE.getContent(),
                    Message.INTERNAL_ERROR_MESSAGE.getContent());
            request.getRequestDispatcher(Router.ERROR).forward(request, response);
        }
    }

}
