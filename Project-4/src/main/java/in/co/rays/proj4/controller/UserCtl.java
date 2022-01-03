package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * User functionality Controller. Performs operation for add, update, delete
 * and get User.
 * 
 * @author Aditya Pandit
 *
 */
@ WebServlet(name="UserCtl",urlPatterns={"/ctl/UserCtl"})
public class UserCtl extends BaseCtl {

    private static final long serialVersionUID = 1L;

    private static Logger log = Logger.getLogger(UserCtl.class);

    @Override
    protected void preload(HttpServletRequest request) {
        RoleModel rm = new RoleModel();
        try {
            List l = rm.list();
            request.setAttribute("roleList", l);
        } catch (ApplicationException e) {
            log.error(e);
        }

    }

    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("UserCtl Method validate Started");

        System.out.println("Validate Started");
        boolean pass = true;
        long id= DataUtility.getLong(request.getParameter("id"));
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");

        if (DataValidator.isNull(request.getParameter("firstName"))) {
        	System.out.println("Data 1");
            request.setAttribute("firstName",
                    PropertyReader.getValue("error.require", "First Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("firstName"))) {
        	System.out.println("Data 2");
			request.setAttribute("firstName", "First Name must contain only Characters ");
			pass = false;
			}

        if (DataValidator.isNull(request.getParameter("lastName"))) {
        	System.out.println("Data 3");
            request.setAttribute("lastName",
                    PropertyReader.getValue("error.require", "Last Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("lastName"))) {
        	System.out.println("Data 4");
        	request.setAttribute("lastName", "Last Name must contain only Characters ");
			pass = false;
			}

        if (DataValidator.isNull(login)) {
        	System.out.println("Data 5");
            request.setAttribute("login",
                    PropertyReader.getValue("error.require", "Login Id"));
            pass = false;
        } else if (!DataValidator.isEmail(login)) {
    	System.out.println("Data 6");
            request.setAttribute("login",
                    PropertyReader.getValue("error.email", "Login ID "));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("password"))) {
        	System.out.println("Data 7");
			request.setAttribute("password", PropertyReader.getValue("error.require", "New Password"));
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			System.out.println("Data 8");
			request.setAttribute("password", "Password must contain alphanumeric characters");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			System.out.println("Data 9");
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}  /*
			 * else if (!DataValidator.isPassword("confirmPassword")) {
			 * request.setAttribute("confirmPassword",
			 * "Confirm Password must contain alphanumeric characters"); pass = false; }
			 */

        if (DataValidator.isNull(request.getParameter("gender"))) {
        	System.out.println("Data 10");
            request.setAttribute("gender",
                    PropertyReader.getValue("error.require", "Gender"));
            pass = false;
        }
        if (DataValidator.isNull(dob)) {
        	System.out.println("Data 11");
            request.setAttribute("dob",
                    PropertyReader.getValue("error.require", "Date Of Birth"));
            pass = false;
        } else if (!DataValidator.isDate(dob)) {
        	System.out.println("Data 12");
        	request.setAttribute("dob",
                    PropertyReader.getValue("error.date", "Date Of Birth"));
            pass = false;
        }
        else if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
        	System.out.println("Data 13");
			request.setAttribute("confirmPassword", "Confirm  Password  should  be matched.");

			pass = false;
		}
        if (DataValidator.isNull(request.getParameter("roleId"))) {
        	System.out.println("Data 14");
			request.setAttribute("roleId", PropertyReader.getValue("error.require", "Role Id"));
			pass = false;
		}

        System.out.println("validate two ");
        log.debug("UserCtl Method validate Ended");

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        log.debug("UserCtl Method populatebean Started");

        UserBean ub = new UserBean();

        ub.setID(DataUtility.getLong(request.getParameter("id")));

        ub.setRoleID(DataUtility.getLong(request.getParameter("roleId")));

        ub.setFirstName(DataUtility.getString(request
                .getParameter("firstName")));

        ub.setLastName(DataUtility.getString(request.getParameter("lastName")));

        ub.setLogin(DataUtility.getString(request.getParameter("login")));

        ub.setPassword(DataUtility.getString(request.getParameter("password")));

        ub.setConfirmpassword(DataUtility.getString(request
                .getParameter("confirmPassword")));

        ub.setGender(DataUtility.getString(request.getParameter("gender")));

        ub.setDob(DataUtility.getDate(request.getParameter("dob")));

        populateDTO(ub, request);

        log.debug("UserCtl Method populateub Ended");

        return ub;
    }

    /**
     * Contains DIsplay logics
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("UserCtl Method doGet Started");
        System.out.println("UserCtl Method doGet Started");

        String op = DataUtility.getString(request.getParameter("operation"));
        System.out.println("do get option -->" + op);
        // get model
        UserModel um = new UserModel();
        int id = (int) DataUtility.getLong(request.getParameter("id"));
        if (id > 0 || op != null) {
            System.out.println("check one "  + id);
            UserBean ub = null;
            try {
                ub = um.findByPK(id);
                ServletUtility.setBean(ub, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("UserCtl Method doGet Ended");
    }

    /**
     * Contains Submit logics
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("UserCtl Method doPost Started");
        System.out.println(" doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));
        System.out.println("option selected -->" + op);
        // get model
        UserModel um = new UserModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        
        
   /*     if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
            UserBean ub = (UserBean) populateBean(request);

            try {
                if (id > 0) {
                	System.out.println("Update  123");
                    um.update(ub);
                } else {
                    long pk = um.add(ub);
                  
                }
                ServletUtility.setBean(ub, request);
                ServletUtility.setSuccessMessage("Data is successfully saved",
                        request);*/
        if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op))  {

            UserBean ub = (UserBean) populateBean(request);
           // UserModel um1 = new UserModel();
            try {
                if (id > 0) {
                	um.update(ub);
                    ServletUtility.setBean(ub, request);
                    ServletUtility.setSuccessMessage("Data is successfully updated",
                            request);
                } else {
                    long pk = um.add(ub);
                    ub.setID(pk);
                    ServletUtility.setBean(ub, request);
                    ServletUtility.setSuccessMessage("Data is successfully saved",
                            request);
                }
               

        
            } catch (ApplicationException e) {
            	System.out.println("this obe");
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(ub, request);
                ServletUtility.setErrorMessage("Login id already exists",
                        request);
            }
        } 
        
  
        
        else if (OP_DELETE.equalsIgnoreCase(op)) {

            UserBean ub = (UserBean) populateBean(request);
            try {
                um.delete(ub);
                ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
            return;
        }
        ServletUtility.forward(getView(), request, response);

        log.debug("UserCtl Method doPostEnded");
    }

    @Override
    protected String getView() {
        return ORSView.USER_VIEW;
    }

}
