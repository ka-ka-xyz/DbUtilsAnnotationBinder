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

import jp.gr.java_conf.ka_ka_xyz.entity.jpa20.property.Jpa20Employee;
import jp.gr.java_conf.ka_ka_xyz.entity.jpa20.property.Jpa20Person;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;



public class JPA20AnnotationProcessorPropertyAccessTest extends AbsAnnotationProcessorTest {
    
    @Test
    public void testBindPersonByPropertyAccess() throws SQLException{
        Connection conn = getConnection();
        QueryRunner run = new QueryRunner();
        RowProcessor rp = new BasicRowProcessor(new JPA20AnnotationProcessor(
                Jpa20Person.class));
        ResultSetHandler<List<Jpa20Person>> prsh 
        = new BeanListHandler<Jpa20Person>(
                Jpa20Person.class, rp);
        String sql = "SELECT * from person ORDER BY id ASC";
        List<Jpa20Person> persons 
        = run.query(conn, sql, prsh);
        assertEquals(3, persons.size());
        assertEquals(new Jpa20Person
                (1, "Howard", "Lovecraft"), persons.get(0));
        assertEquals(new Jpa20Person
                (2, "August", "Derleth"), persons.get(1));
        assertEquals(new Jpa20Person
                (3, "Robert", "Bloch"), persons.get(2));
        
    }
    
    @Test
    public void testBindEmployeeByPropertyAccess() throws SQLException{
        Connection conn = getConnection();
        QueryRunner run = new QueryRunner();
        RowProcessor rp = new BasicRowProcessor(
                new JPA20AnnotationProcessor(Jpa20Employee.class));
        ResultSetHandler<List<Jpa20Employee>> ersh 
            = new BeanListHandler<Jpa20Employee>(
                    Jpa20Employee.class, rp);
        String sql = "SELECT * from person INNER JOIN employee ON person.id = employee.person_id ORDER BY id ASC";
        List<Jpa20Employee> employees = run.query(conn, sql, ersh);
        assertEquals(3, employees.size());
        assertEquals(new Jpa20Employee
                (1, "Howard", "Lovecraft", "EMP001", "Weird Tales Div."), employees.get(0));
        assertEquals(new Jpa20Employee
                (2, "August", "Derleth", "EMP002", "Arkham House Div."), employees.get(1));
        assertEquals(new Jpa20Employee
                (3, "Robert", "Bloch", "EMP003", "Weird Tales Div."), employees.get(2));
    }

}
