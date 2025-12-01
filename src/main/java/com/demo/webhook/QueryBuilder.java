package com.demo.webhook;

import org.springframework.stereotype.Component;

@Component
public class QueryBuilder {

    public String getFinalQuery() {

        return "SELECT d.DEPARTMENT_NAME, t.TOTAL_SALARY AS SALARY, " +
               "CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS EMPLOYEE_NAME, " +
               "TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE " +
               "FROM ( " +
               " SELECT emp.EMP_ID, emp.DEPARTMENT, SUM(p.AMOUNT) AS TOTAL_SALARY, " +
               " ROW_NUMBER() OVER (PARTITION BY emp.DEPARTMENT ORDER BY SUM(p.AMOUNT) DESC) AS RN " +
               " FROM EMPLOYEE emp " +
               " JOIN PAYMENTS p ON emp.EMP_ID = p.EMP_ID " +
               " WHERE DAY(p.PAYMENT_TIME) <> 1 " +
               " GROUP BY emp.EMP_ID, emp.DEPARTMENT " +
               ") t " +
               "JOIN EMPLOYEE e ON t.EMP_ID = e.EMP_ID " +
               "JOIN DEPARTMENT d ON t.DEPARTMENT = d.DEPARTMENT_ID " +
               "WHERE t.RN = 1";
    }
}
