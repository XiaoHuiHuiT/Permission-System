package com.jonathanlee.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonathanlee.domain.AjaxRes;
import com.jonathanlee.domain.Employee;
import com.jonathanlee.domain.PageListRes;
import com.jonathanlee.domain.QueryVo;
import com.jonathanlee.service.EmployeeService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 处理所有员工请求的处理器
 */

@Controller
public class EmployeeController {

    // 注入业务层
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee")
    @RequiresPermissions("employee:index")
    public String employee() {
        return "employee";
    }

    @RequestMapping("/employeeList")
    @ResponseBody
    public PageListRes employeeList(QueryVo queryVo) {
        // 调用业务层查询员工
        PageListRes pageListRes = employeeService.getEmployee(queryVo);
        return pageListRes;
    }

    // 接收员工添加的表单
    @RequestMapping("/saveEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes saveEmployee(Employee employee) {
        System.out.println(employee);
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 调用业务层，保存用户
            employee.setState(true);
            employeeService.saveEmployee(employee);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxRes.setMsg("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    // 编辑员工
    @RequestMapping("/updateEmployee")
    @ResponseBody
    @RequiresPermissions("employee:edit")
    public AjaxRes updateEmployee(Employee employee) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 调用业务层 更新员工
            employeeService.updateEmployee(employee);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("更新失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    // 接收离职操作请求
    @RequestMapping("/updateState")
    @ResponseBody
    @RequiresPermissions("employee:delete")
    public AjaxRes updateState(Long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 调用业务层 设置员工离职状态
            employeeService.updateState(id);
            ajaxRes.setMsg("离职成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            System.out.println(e);
            ajaxRes.setMsg("离职失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    // 处理授权异常
    @ExceptionHandler(AuthorizationException.class)
    public void handlerShiroException(HandlerMethod method, HttpServletResponse response) throws Exception {// 发生异常的方法
//        跳转到一个界面   界面提示没有权限
//        判断当前的请求是不是json请求  如果是 返回json给客户端（浏览器让他自己来做跳转）
//          获取方法上的注解
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if (methodAnnotation != null) {
//            Ajax
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("你没有权限操作");

            // 转成json字符串
            String s = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(s);

        } else {
            response.sendRedirect("nopermission.jsp");
        }
    }

    // 导出Excel
    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletResponse response) {
        try {
            System.out.println("--------downloadExcel---------");

            // 1.从数据库当中取列表数据
            QueryVo queryVo = new QueryVo();
            queryVo.setPage(1);
            queryVo.setRows(10);

            PageListRes plr = employeeService.getEmployee(queryVo);

            List<Employee> employees = (List<Employee>) plr.getRows();

            // 2.创建Excel 写到excel当中
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("员工数据");

            /*---------------------------------设置行的头部--------------------------------------*/
//        创建一行
            HSSFRow row = sheet.createRow(0);
//        设置行的每一列数据
            row.createCell(0).setCellValue("编号");
            row.createCell(1).setCellValue("用户名");
            row.createCell(2).setCellValue("入职日期");
            row.createCell(3).setCellValue("电话");
            row.createCell(4).setCellValue("邮件");
            /*-----------------------------------------------------------------------------------*/

            /*---------------------------------2.设置子行数据--------------------------------------*/
            HSSFRow employeeRow = null;
//        取出每一个员工来设置数据
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
//            创建Excel一行
                employeeRow = sheet.createRow(i + 1);

                employeeRow.createCell(0).setCellValue(employee.getId());
                employeeRow.createCell(1).setCellValue(employee.getUsername());
                if (employee.getInputtime() != null) {
                    // 设置入职日期
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String format = sdf.format(employee.getInputtime());
                    employeeRow.createCell(2).setCellValue(format);
                } else {
                    // 设置为空
                    employeeRow.createCell(2).setCellValue("");
                }
                employeeRow.createCell(3).setCellValue(employee.getTel());
                employeeRow.createCell(4).setCellValue(employee.getEmail());
            }
            /*-----------------------------------------------------------------------------------*/

            /*---------------------------------3.响应给浏览器--------------------------------------*/

            String fileName = new String("员工数据.xls".getBytes("utf-8"), "iso8859-1");

//            设置相应头
            response.setHeader("content-Disposition", "attachment;filename=" + fileName);
            wb.write(response.getOutputStream());
            /*-----------------------------------------------------------------------------------*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    下载Excel模板
    @RequestMapping("/downloadExcelTpl")
    @ResponseBody
    public void downloadExcelTpl(HttpServletRequest request, HttpServletResponse response) {
        FileInputStream is = null;
        try {
            /*---------------------------------3.响应给浏览器--------------------------------------*/

            String fileName = new String("EmployeeTpl.xls".getBytes("utf-8"), "iso8859-1");

//            设置相应头
            response.setHeader("content-Disposition", "attachment;filename=" + fileName);

//            获取文件路径
            String realPath = request.getSession().getServletContext().getRealPath("static/ExcelTml.xls");
            is = new FileInputStream(realPath);

//            读入的文件响应给浏览器
            IOUtils.copy(is, response.getOutputStream());
            /*-----------------------------------------------------------------------------------*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 导入Excel模板   配置文件上传解析器    mvc配置当中
    @RequestMapping("/uploadExcelFile")
    @ResponseBody
    public AjaxRes uploadExcelFile(MultipartFile excel) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("导入成功");

            HSSFWorkbook wb = new HSSFWorkbook(excel.getInputStream());
            HSSFSheet sheet = wb.getSheetAt(0);

//            获取最大的行号
            int lastRowNum = sheet.getLastRowNum();
            Row employeeRow = null;
            for (int i = 1; i <= lastRowNum; i++) {
                // 从第一行开始取
                employeeRow = sheet.getRow(i);

                Employee employee = new Employee();
//                用户名
                employee.setUsername((String) getCellValue(employeeRow.getCell(1)));
//                入职日期
                employee.setInputtime((Date) getCellValue(employeeRow.getCell(2)));
//                电话
//                Double phone = (Double)getCellValue(employeeRow.getCell(3));
                Cell cellCode = employeeRow.getCell(3);
                cellCode.setCellType(CellType.STRING);

                employee.setTel((String) getCellValue(employeeRow.getCell(3)));
//                邮箱
                employee.setEmail((String) getCellValue(employeeRow.getCell(4)));
                employeeService.saveEmployee(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("导入失败");
        }
        return ajaxRes;
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
        }
        return cell;
    }

}