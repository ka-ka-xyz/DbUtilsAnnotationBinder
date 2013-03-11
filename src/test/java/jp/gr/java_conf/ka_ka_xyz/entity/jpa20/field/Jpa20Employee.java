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

package jp.gr.java_conf.ka_ka_xyz.entity.jpa20.field;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;


@Access(AccessType.FIELD)
public class Jpa20Employee extends Jpa20Person{
    
    public Jpa20Employee(){}
    public Jpa20Employee(int id, String firstName, String lastName, String empId, String divisionName){
        super.setId(id);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.empId = empId;
        this.divisionName = divisionName;
    }
    
    @Column(name="employee_id")
    private String empId;
    @Column(name="division")
    private String divisionName;
    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    public String getDivisionName() {
        return divisionName;
    }
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((divisionName == null) ? 0 : divisionName.hashCode());
        result = prime * result + ((empId == null) ? 0 : empId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Jpa20Employee other = (Jpa20Employee) obj;
        if (divisionName == null) {
            if (other.divisionName != null)
                return false;
        } else if (!divisionName.equals(other.divisionName))
            return false;
        if (empId == null) {
            if (other.empId != null)
                return false;
        } else if (!empId.equals(other.empId))
            return false;
        return true;
    }
    
    
}
