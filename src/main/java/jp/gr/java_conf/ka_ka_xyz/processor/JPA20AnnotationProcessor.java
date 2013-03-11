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

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;

import org.apache.commons.dbutils.BeanProcessor;

public class JPA20AnnotationProcessor extends BeanProcessor {
    
    private Map<String, String> fieldColMap = new HashMap<String, String>();
    
    public JPA20AnnotationProcessor(Class<?> clazz){
        if(clazz != null){
            Annotation  classAnnotation = clazz.getAnnotation(Access.class);
            Access access = null;
            if(classAnnotation instanceof Access){
                access = (Access)classAnnotation;
            }
            boolean isFieldAccess = ( access == null 
                    || AccessType.FIELD == access.value());
            Field[] fields = clazz.getDeclaredFields();
            if(isFieldAccess){
                for(Field field : fields){
                    registerFieldAnnotation(field);
                }
            } else {
                for(Field field : fields){
                    registerMethodAnnotation(field, clazz);
                }
            }
        }
    }
    
    private void registerFieldAnnotation(Field field){
        Column column = field.getAnnotation(Column.class);
        String colName = null;
        if(column != null){
            colName = column.name();
            fieldColMap.put(field.getName(), colName);
        }
        
    }
    
    private void registerMethodAnnotation(Field field, Class<?> clazz){
        try {
            String fieldName = field.getName();
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Column column = pd.getReadMethod().getAnnotation(Column.class);
            String colName;
            if(column != null){
                colName = column.name();
                fieldColMap.put(fieldName, colName);
            }
            
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return;
        }
    }
    
    @Override
    protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,
            PropertyDescriptor[] props) throws SQLException {
        int cols = rsmd.getColumnCount();
        int columnToProperty[] = new int[cols + 1];
        Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);
        for (int col = 1; col <= cols; col++) {
            String columnName = rsmd.getColumnLabel(col);
            for (int i = 0; i < props.length; i++) {
                if (equalsColumnAnnotation(columnName, props[i].getName())) {
                    columnToProperty[col] = i;
                    break;
                }
            }
        }
        return columnToProperty;
    }
    private boolean equalsColumnAnnotation(String colName, String propName) {
        String annotColName = fieldColMap.get(propName);
        return (annotColName != null && annotColName.equalsIgnoreCase(colName));
    }
}