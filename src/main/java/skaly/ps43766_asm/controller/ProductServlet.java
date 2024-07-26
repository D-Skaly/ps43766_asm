package skaly.ps43766_asm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import skaly.ps43766_asm.entity.Product;
import skaly.ps43766_asm.service.BaseService;
import skaly.ps43766_asm.service.ProductService;
import skaly.ps43766_asm.repository.ProductDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "productServlet", value = "/products")
public class ProductServlet extends AbstractCrudServlet<Product, Integer> {

    private ProductService productService;

    @Override
    public void init() {
        productService = new ProductService();
    }

    @Override
    protected BaseService<Product, Integer> getService() {
        return productService;
    }

    @Override
    protected Integer parseId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected Product parseEntity(HttpServletRequest request) {
        Product product = new Product();
        // Parse and set product properties from request parameters
//        product.setName(request.getParameter("name"));
//        product.setPrice(Double.parseDouble(request.getParameter("price")));
//        product.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        // Add more fields as needed
        return product;
    }

    @Override
    protected String getListView() {
        return "views/products/list.jsp";
    }

    @Override
    protected String getShowView() {
        return "views/products/show.jsp";
    }

    @Override
    protected String getCreateFormView() {
        return "views/products/create.jsp";
    }

    @Override
    protected String getEditFormView() {
        return "views/products/edit.jsp";
    }

    @Override
    protected String getRedirectUrl(String action) {
        return "products?action=" + action;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "show":
                showEntity(request, response);
                break;
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "findByCategory":
                findByCategory(request, response);
                break;
            default:
                listEntities(request, response);
                break;
        }
    }

    private void findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
        List<Product> products = productService.findByCategory(categoryId);
        request.setAttribute("products", products);
        request.getRequestDispatcher("views/products/list.jsp").forward(request, response);
    }
}
