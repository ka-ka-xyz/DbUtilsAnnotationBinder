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

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.gr.java_conf.ka_ka_xyz.annotation.Access;
import jp.gr.java_conf.ka_ka_xyz.annotation.AccessType;
import jp.gr.java_conf.ka_ka_xyz.annotation.Column;

import org.apache.commons.dbutils.BeanProcessor;

public class AnnotationProcessor extends BeanProcessor {
    
    private Map<String, String> fieldColMap = new HashMap<String, String>();
    
    public AnnotationProcessor(Class<?> clazz){
        if(clazz != null){
            Annotation  classAnnotation = clazz.getAnnotation(Access.class);
            Access access = null;
            if(classAnnotation instanceof Access){
                access = (Access)classAnnotation;
            }
            boolean isFieldAccess = ( access == null 
                    || AccessType.FIELD == access.value());

            if(isFieldAccess){
                List<Field> fields = new ArrayList<Field>();
                getFields(clazz, fields);
                for(Field field : fields){
                    registerFieldAnnotation(field);
                }
            } else {
                Method[] methods = getMethods(clazz);
                for(Method method : methods){
                    registerMethodAnnotation(method, clazz);
                }
            }
        }
    }
    
    private void getFields(Class clazz, List<Field> fields){
        if(clazz == null){
            return;
        }
        Field[] temp = clazz.getDeclaredFields();
        if(temp != null){
            for(Field field : temp){
                fields.add(field);
            }
        }
        Class superClazz = clazz.getSuperclass();
        if(superClazz != null){
            getFields(superClazz, fields);
        }
    }
    
    private Method[] getMethods(Class clazz){
        return (clazz == null) ? new Method[0] : clazz.getMethods();
    }
    
    private void registerFieldAnnotation(Field field){
        Column column = field.getAnnotation(Column.class);
        if(column != null){
            String colName = column.name();
            fieldColMap.put(field.getName(), colName);
        }
        
    }
    
    private void registerMethodAnnotation(Method method, Class<?> clazz){
        Column column = method.getAnnotation(Column.class);
        String fieldName = method.getName().substring(3);
        fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
        if(column != null){
            String colName = column.name();
            fieldColMap.put(fieldName, colName);
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