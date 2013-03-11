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

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public abstract class AbsAnnotationProcessorTest {
    private static final String DBURL_KEY = "dburl";
    private static final String DBUSER_KEY = "dbuser";
    private static final String DBPASSWORD_KEY = "dbpassword";
    private static final String JDBCDDRIVER_KEY = "jdbcdriver";
    
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    private static String jdbcDriver;
    
    public AbsAnnotationProcessorTest(){
        Properties props = new Properties();
        try {
            props.load(AbsAnnotationProcessorTest.class
                    .getResourceAsStream("dbcofing.properties"));
        } catch (IOException e) {
            new RuntimeException(e);
        }
        dbUrl = props.getProperty(DBURL_KEY);
        dbPassword = props.getProperty(DBPASSWORD_KEY);
        dbUser = props.getProperty(DBUSER_KEY);
        jdbcDriver = props.getProperty(JDBCDDRIVER_KEY);
    }
    
    protected Connection getConnection(){
        
        try {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager
                    .getConnection(dbUrl, dbUser, dbPassword);
            if(conn == null){
                throw new NullPointerException();
            }
            return conn;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
    
}
