/*
   Copyright [2013] [kakaxyz.kakaxyz@gmail.com]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package jp.gr.java_conf.ka_ka_xyz.processor;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;



public class AnnotationProcessorTest extends AbsAnnotationProcessorTest {
    
    @Test
    public void testBindPersonAccessByFieldAccess() throws SQLException{
        Connection conn = getConnection();
        QueryRunner run = new QueryRunner();
        RowProcessor rp = new BasicRowProcessor(
                new AnnotationProcessor(jp.gr.java_conf.ka_ka_xyz.entity.field.Person.class));
        ResultSetHandler<List<jp.gr.java_conf.ka_ka_xyz.entity.field.Person>> prsh 
        = new BeanListHandler<jp.gr.java_conf.ka_ka_xyz.entity.field.Person>(
                jp.gr.java_conf.ka_ka_xyz.entity.field.Person.class, rp);
        String sql = "SELECT * from person ORDER BY id ASC";
        List<jp.gr.java_conf.ka_ka_xyz.entity.field.Person> persons
        = run.query(conn, sql, prsh);
        assertEquals(3, persons.size());
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.field.Person
                (1, "Howard", "Lovecraft"), persons.get(0));
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.field.Person
                (2, "August", "Derleth"), persons.get(1));
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.field.Person
                (3, "Robert", "Bloch"), persons.get(2));
        
    }
    
    @Test
    public void testBindEmployeeByFieldAccess() throws SQLException{
        Connection conn = getConnection();
        QueryRunner run = new QueryRunner();
        RowProcessor rp = new BasicRowProcessor(
                new AnnotationProcessor(jp.gr.java_conf.ka_ka_xyz.entity.field.Employee.class));
        ResultSetHandler<List<jp.gr.java_conf.ka_ka_xyz.entity.field.Employee>> ersh 
            = new BeanListHandler<jp.gr.java_conf.ka_ka_xyz.entity.field.Employee>(
                    jp.gr.java_conf.ka_ka_xyz.entity.field.Employee.class, rp);
        String sql = "SELECT * from person INNER JOIN employee ON person.id = employee.person_id ORDER BY id ASC";
        List<jp.gr.java_conf.ka_ka_xyz.entity.field.Employee> employees = run.query(conn, sql, ersh);
        assertEquals(3, employees.size());
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.field.Employee
                (1, "Howard", "Lovecraft", "EMP001", "Weird Tales Div."), employees.get(0));
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.field.Employee
                (2, "August", "Derleth", "EMP002", "Arkham House Div."), employees.get(1));
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.field.Employee
                (3, "Robert", "Bloch", "EMP003", "Weird Tales Div."), employees.get(2));
    }
    
    @Test
    public void testBindPersonByPropertyAccess() throws SQLException{
        Connection conn = getConnection();
        QueryRunner run = new QueryRunner();
        RowProcessor rp = new BasicRowProcessor(
                new AnnotationProcessor(jp.gr.java_conf.ka_ka_xyz.entity.property.Person.class));
        ResultSetHandler<List<jp.gr.java_conf.ka_ka_xyz.entity.property.Person>> prsh 
        = new BeanListHandler<jp.gr.java_conf.ka_ka_xyz.entity.property.Person>(
                jp.gr.java_conf.ka_ka_xyz.entity.property.Person.class, rp);
        String sql = "SELECT * from person ORDER BY id ASC";
        List<jp.gr.java_conf.ka_ka_xyz.entity.property.Person> persons
        = run.query(conn, sql, prsh);
        assertEquals(3, persons.size());
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.property.Person
                (1, "Howard", "Lovecraft"), persons.get(0));
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.property.Person
                (2, "August", "Derleth"), persons.get(1));
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.property.Person
                (3, "Robert", "Bloch"), persons.get(2));
        
    }
    
    @Test
    public void testBindEmployeeByPropertyAccess() throws SQLException{
        Connection conn = getConnection();
        QueryRunner run = new QueryRunner();
        RowProcessor rp = new BasicRowProcessor(
                new AnnotationProcessor(jp.gr.java_conf.ka_ka_xyz.entity.property.Employee.class));
        ResultSetHandler<List<jp.gr.java_conf.ka_ka_xyz.entity.property.Employee>> ersh 
            = new BeanListHandler<jp.gr.java_conf.ka_ka_xyz.entity.property.Employee>(
                    jp.gr.java_conf.ka_ka_xyz.entity.property.Employee.class, rp);
        String sql = "SELECT * from person INNER JOIN employee ON person.id = employee.person_id ORDER BY id ASC";
        List<jp.gr.java_conf.ka_ka_xyz.entity.property.Employee> employees = run.query(conn, sql, ersh);
        assertEquals(3, employees.size());
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.property.Employee
                (1, "Howard", "Lovecraft", "EMP001", "Weird Tales Div."), employees.get(0));
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.property.Employee
                (2, "August", "Derleth", "EMP002", "Arkham House Div."), employees.get(1));
        assertEquals(new jp.gr.java_conf.ka_ka_xyz.entity.property.Employee
                (3, "Robert", "Bloch", "EMP003", "Weird Tales Div."), employees.get(2));
    }

}
