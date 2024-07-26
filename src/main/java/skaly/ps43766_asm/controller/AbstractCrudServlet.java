package skaly.ps43766_asm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import skaly.ps43766_asm.service.BaseService;

import java.io.IOException;
import java.util.List;

public abstract class AbstractCrudServlet<T, K> extends HttpServlet {

    protected abstract BaseService<T, K> getService();

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
            default:
                listEntities(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createEntity(request, response);
                break;
            case "edit":
                updateEntity(request, response);
                break;
            case "delete":
                deleteEntity(request, response);
                break;
            default:
                listEntities(request, response);
                break;
        }
    }

    protected void listEntities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<T> entities = getService().findAll();
        request.setAttribute("entities", entities);
        request.getRequestDispatcher(getListView()).forward(request, response);
    }

    protected void showEntity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        K id = parseId(request.getParameter("id"));
        T entity = getService().findById(id).orElse(null);
        request.setAttribute("entity", entity);
        request.getRequestDispatcher(getShowView()).forward(request, response);
    }

    protected void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(getCreateFormView()).forward(request, response);
    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        K id = parseId(request.getParameter("id"));
        T entity = getService().findById(id).orElse(null);
        request.setAttribute("entity", entity);
        request.getRequestDispatcher(getEditFormView()).forward(request, response);
    }

    protected void createEntity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        T entity = parseEntity(request);
        getService().save(entity);
        response.sendRedirect(getRedirectUrl("list"));
    }

    protected void updateEntity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        T entity = parseEntity(request);
        getService().update(entity);
        response.sendRedirect(getRedirectUrl("list"));
    }

    protected void deleteEntity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        K id = parseId(request.getParameter("id"));
        getService().deleteById(id);
        response.sendRedirect(getRedirectUrl("list"));
    }

    protected abstract K parseId(String id);

    protected abstract T parseEntity(HttpServletRequest request);

    protected abstract String getListView();

    protected abstract String getShowView();

    protected abstract String getCreateFormView();

    protected abstract String getEditFormView();

    protected abstract String getRedirectUrl(String action);
}
