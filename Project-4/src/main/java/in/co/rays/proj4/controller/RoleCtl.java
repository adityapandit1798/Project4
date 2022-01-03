package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Role functionality Controller. Performs operation for add, update, delete
 * and get Role.
 * 
 * @author Aditya Pandit
 *
 */
@ WebServlet("/ctl/RoleCtl")
public class RoleCtl extends BaseCtl {

    private static final long serialVersionUID = 1L;

    private static Logger log = Logger.getLogger(RoleCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("RoleCtl Method validate Started");

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Name must contain only  Characters ");
			pass = false;
			}

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description",
                    PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("description"))) {
			request.setAttribute("description", "Description Name must contain only Characters ");
			pass = false;
			}

        log.debug("RoleCtl Method validate Ended");

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        log.debug("RoleCtl Method populatebean Started");

        RoleBean rb = new RoleBean();

        rb.setID(DataUtility.getLong(request.getParameter("id")));

        rb.setName(DataUtility.getString(request.getParameter("name")));
        rb.setDescription(DataUtility.getString(request
                .getParameter("description")));

        populateDTO(rb, request);

        log.debug("RoleCtl Method populaterb Ended");

        return rb;
    }

    /**
     * Contains Display logics
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("RoleCtl Method doGet Started");

        System.out.println("In do get");

        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        RoleModel rm = new RoleModel();

        int id = (int) DataUtility.getLong(request.getParameter("id"));
        if (id > 0 || op != null) {
            RoleBean rb;
            try {
                rb = rm.findByPK(id);
                ServletUtility.setBean(rb, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
        log.debug("RoleCtl Method doGetEnded");
    }

    /**
     * Contains Submit logics
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("RoleCtl Method doGet Started");

        System.out.println("In do get");

        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        RoleModel rm = new RoleModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

            RoleBean rb = (RoleBean) populateBean(request);

            try {
                if (id > 0) {
                    rm.update(rb);

                    ServletUtility.setBean(rb, request);
                    ServletUtility.setSuccessMessage("Data is successfully updated",
                            request);

                } else {
                    long pk = rm.add(rb);
                    rb.setID(pk);

                    ServletUtility.setBean(rb, request);
                    ServletUtility.setSuccessMessage("Data is successfully saved",
                            request);

                }

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(rb, request);
                ServletUtility.setErrorMessage("Role already exists", request);
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            RoleBean rb = (RoleBean) populateBean(request);
            try {
                rm.delete(rb);
                ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
            return;

        }

        ServletUtility.forward(getView(), request, response);

        log.debug("RoleCtl Method doPOst Ended");
    }

    @Override
    protected String getView() {
        return ORSView.ROLE_VIEW;
    }

}